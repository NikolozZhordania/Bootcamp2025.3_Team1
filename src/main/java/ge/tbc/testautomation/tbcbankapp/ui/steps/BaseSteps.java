package ge.tbc.testautomation.tbcbankapp.ui.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import ge.tbc.testautomation.tbcbankapp.ui.pages.CommonPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BaseSteps {

    private final Page page;
    private final CommonPage commonPage;

    public BaseSteps(Page page) {
        this.page = page;
        this.commonPage = new CommonPage(page);
    }

    public BaseSteps waitForPageToLoad() {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        return this;
    }

    public BaseSteps rejectCookieIfExists() {
        if (commonPage.denyCookieBtn.isVisible()) {
            commonPage.denyCookieBtn.click();
        }
        return this;
    }

    public BaseSteps assertCookieRejected() {
        assertThat(commonPage.denyCookieBtn).isHidden();
        return this;
    }
}