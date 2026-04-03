package ge.tbc.testautomation.tbcbankapp.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class OffersPage extends CommonPage {
    public final Locator
            offersHeader,
            readMoreBtn,
            offerCards,
            offerTitle,
            countdownText;

    public OffersPage(Page page) {
        super(page);
        this.offersHeader = page.locator("tbcx-pw-section-title h2");
        this.readMoreBtn = page.getByRole(AriaRole.BUTTON)
                .filter(new Locator.FilterOptions().setHasText("ვრცლად"))
                .first();
        this.offerCards = page.locator("app-marketing-list tbcx-pw-card");
        this.offerTitle = page.locator("h3");
        this.countdownText = page.locator(".tbcx-pw-card__text-with-icon-info");
    }
}