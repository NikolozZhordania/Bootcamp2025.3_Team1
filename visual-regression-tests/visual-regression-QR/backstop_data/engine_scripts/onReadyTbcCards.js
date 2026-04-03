const qrCodePage = require('./pages/qrCodePage');

module.exports = async (page, scenario) => {
    await page.waitForLoadState('networkidle');
    try {
        await page.locator("app-cookie-consent button").filter({ hasText: 'თანხმობა' }).first().click();
        await page.waitForTimeout(1000);
    } catch (e) {}
    await qrCodePage.navigateToTbcCards(page);
    await page.evaluate(() => window.scrollTo(0, 0));

    await page.waitForLoadState('networkidle');
    await page.waitForSelector('tbcx-pw-cards-comparison-table', { timeout: 15000 }).catch(() => {});
    await page.waitForTimeout(3000);
};