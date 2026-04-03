const homePage = require('./pages/homePage');
const locationsPage = require('./pages/locationsPage');

module.exports = async (page, scenario) => {
    await page.waitForLoadState('networkidle');
    await page.waitForTimeout(3000);
    await page.evaluate(() => window.scrollTo(0, 0));
    await page.waitForTimeout(500);
    await homePage.navigateToLocations(page);
    await page.waitForTimeout(5000);
    await locationsPage.scrollToTop(page);
};