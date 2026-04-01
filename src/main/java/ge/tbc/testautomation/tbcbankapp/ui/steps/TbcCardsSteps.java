package ge.tbc.testautomation.tbcbankapp.ui.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.tbcbankapp.ui.pages.TbcCardsPage;
import ge.tbc.testautomation.tbcbankapp.ui.utils.TbcCardUtils;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TbcCardsSteps {
    private final Page page;
    private final TbcCardsPage tbcCardsPage;
    private String qrUrl;

    public TbcCardsSteps(Page page) {
        this.page = page;
        this.tbcCardsPage = new TbcCardsPage(page);
    }

    @Step("Wait for 'აიღე ბარათი' button to be visible")
    public TbcCardsSteps waitForButtonToBeVisible() {
        assertThat(tbcCardsPage.takeCardButton).isVisible();
        return this;
    }

    @Step("Click 'აიღე ბარათი' button")
    public TbcCardsSteps clickTakeCardButton() {
        tbcCardsPage.takeCardButton.click();
        return this;
    }

    @Step("Wait for QR code to be visible")
    public TbcCardsSteps waitForQRToBeVisible() {
        assertThat(tbcCardsPage.qrCanvas).isVisible();
        return this;
    }

    @Step("Read QR code and navigate to URL")
    public TbcCardsSteps navigateToQRUrl() throws Exception {
        qrUrl = TbcCardUtils.readQRCode(tbcCardsPage.qrCanvas);
        page.navigate(qrUrl);
        return this;
    }

    @Step("Assert navigated to QR URL: tbconline.ge/tbcrd")
    public TbcCardsSteps assertNavigatedToQRUrl() {
        assertThat(page).hasURL(java.util.regex.Pattern.compile(".*tbconline\\.ge/tbcrd.*"));
        return this;
    }
}