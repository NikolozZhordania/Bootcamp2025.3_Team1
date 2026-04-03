module.exports = {
    openNavigationMenu: async (page) => {
        const link = page.locator("header a").filter({ hasText: 'ჩემთვის' }).first();
        await link.waitFor({ timeout: 10000 });
        const box = await link.boundingBox();
        await page.mouse.move(0, 0);
        await page.mouse.move(box.x + box.width / 2, box.y + box.height / 2, { steps: 30 });
        await page.waitForTimeout(3000);
    },

    navigateToLocations: async (page) => {
        await page.locator("header a").filter({ hasText: 'ჩემთვის' }).first().hover();
        await page.waitForTimeout(1500);
        await page.getByText('მისამართები').first().click();
        await page.waitForLoadState('networkidle');
        await page.waitForTimeout(1500);
    }
};