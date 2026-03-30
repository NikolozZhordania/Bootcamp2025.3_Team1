package ge.tbc.testautomation.tbcbankapp.ui.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import ge.tbc.testautomation.tbcbankapp.ui.pages.BasePage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BaseSteps {

    private final Page page;
    private final BasePage basePage;

    public BaseSteps(Page page) {
        this.page = page;
        this.basePage = new BasePage(page);
    }

    public BaseSteps waitForPageToLoad() {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        return this;
    }

    public BaseSteps rejectCookieIfExists() {
        if (basePage.denyCookieBtn.isVisible()) {
            basePage.denyCookieBtn.click();
        }
        return this;
    }

    public BaseSteps assertCookieRejected() {
        assertThat(basePage.denyCookieBtn).isHidden();
        return this;
    }
}