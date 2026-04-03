module.exports = async (page, scenario) => {
    await page.addStyleTag({
        content: `
            app-cookie-consent,
            [class*="cookie"],
            [id*="cookie"] {
                display: none !important;
                visibility: hidden !important;
                pointer-events: none !important;
            }
        `
    });
};
