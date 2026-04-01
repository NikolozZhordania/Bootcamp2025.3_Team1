package ge.tbc.testautomation.tbcbankapp.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class TbcCardsPage {
    public Locator takeCardButton;
    public Locator qrCanvas;

    public TbcCardsPage(Page page) {
        this.takeCardButton = page.getByRole(AriaRole.BUTTON)
                .filter(new Locator.FilterOptions().setHasText("აიღე ბარათი"))
                .first();

        this.qrCanvas = page.locator("canvas.tbcx-pw-app-download-banner-popup__qr");
    }
}