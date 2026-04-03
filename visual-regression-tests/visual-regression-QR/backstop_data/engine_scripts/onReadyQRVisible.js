const qrCodePage = require('./pages/qrCodePage');

module.exports = async (page, scenario) => {
    await page.waitForLoadState('networkidle');
    try {
        await page.locator("app-cookie-consent button").filter({ hasText: 'თანხმობა' }).first().click();
        await page.waitForTimeout(1000);
    } catch (e) {}
    await qrCodePage.navigateToTbcCards(page);
    await qrCodePage.clickTakeCardButton(page);

    await page.waitForSelector('canvas.tbcx-pw-app-download-banner-popup__qr', { timeout: 15000 });

    await page.waitForFunction(() => {
        const canvas = document.querySelector('canvas.tbcx-pw-app-download-banner-popup__qr');
        if (!canvas || canvas.width === 0 || canvas.height === 0) return false;
        const ctx = canvas.getContext('2d');
        const data = ctx.getImageData(0, 0, canvas.width, canvas.height).data;
        for (let i = 0; i < data.length; i += 4) {
            if (data[i] < 50 && data[i + 1] < 50 && data[i + 2] < 50) return true;
        }
        return false;
    }, { timeout: 15000, polling: 500 });
};