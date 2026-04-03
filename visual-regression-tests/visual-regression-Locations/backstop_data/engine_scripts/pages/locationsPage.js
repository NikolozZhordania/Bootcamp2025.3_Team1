module.exports = {
    waitForPage: async (page) => {
        await page.waitForLoadState('networkidle');
        await page.waitForTimeout(3000);
    },

    scrollToTop: async (page) => {
        await page.evaluate(() => window.scrollTo(0, 0));
        await page.waitForTimeout(500);
    }
};