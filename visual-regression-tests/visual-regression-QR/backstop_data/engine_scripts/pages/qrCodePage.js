module.exports = {
    navigateToTbcCards: async (page) => {
        await page.locator("header a").filter({ hasText: 'ჩემთვის' }).first().hover();
        await page.waitForTimeout(1500);
        await page.locator("span.tbcx-pw-mega-menu-sub-item__title")
            .filter({ hasText: 'თიბისი ბარათი' }).first().click();
        await page.waitForLoadState('networkidle');
    },

    clickTakeCardButton: async (page) => {
        await page.getByRole('button', { name: 'აიღე ბარათი' }).first().click();
        await page.waitForTimeout(1500);
    },

    readAndNavigateToQRUrl: async (page) => {
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

        const qrUrl = await page.evaluate(async () => {
            const canvas = document.querySelector('canvas.tbcx-pw-app-download-banner-popup__qr');
            const ctx = canvas.getContext('2d');
            const imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
            const script = document.createElement('script');
            script.src = 'https://cdn.jsdelivr.net/npm/jsqr@1.4.0/dist/jsQR.js';
            document.head.appendChild(script);
            await new Promise(resolve => script.onload = resolve);
            const code = window.jsQR(imageData.data, canvas.width, canvas.height);
            return code ? code.data : null;
        });

        if (!qrUrl) throw new Error('QR code could not be decoded — URL was null');

        await page.setExtraHTTPHeaders({
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36'
        });

        await page.addInitScript(() => {
            Object.defineProperty(navigator, 'userAgent', {
                get: () => 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36'
            });
            Object.defineProperty(navigator, 'webdriver', {
                get: () => false
            });
        });

        await page.goto(qrUrl, { waitUntil: 'networkidle', timeout: 60000 });
        await page.waitForTimeout(3000);
    }
};