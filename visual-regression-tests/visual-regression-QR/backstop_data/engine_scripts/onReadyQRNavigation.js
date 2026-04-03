const qrCodePage = require('./pages/qrCodePage');

module.exports = async (page, scenario) => {
    await page.waitForLoadState('networkidle');
    try {
        await page.locator("app-cookie-consent button").filter({ hasText: 'თანხმობა' }).first().click();
        await page.waitForTimeout(1000);
    } catch (e) {}
    await qrCodePage.navigateToTbcCards(page);
    await qrCodePage.clickTakeCardButton(page);
    await qrCodePage.readAndNavigateToQRUrl(page);

};