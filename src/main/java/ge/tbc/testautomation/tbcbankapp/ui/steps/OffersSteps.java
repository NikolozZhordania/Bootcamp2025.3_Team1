package ge.tbc.testautomation.tbcbankapp.ui.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbc.testautomation.tbcbankapp.ui.pages.OffersPage;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static ge.tbc.testautomation.tbcbankapp.ui.data.Constants.URLs.OFFERS_PAGE_URL;
import static org.testng.Assert.assertTrue;

public class OffersSteps {
    private final Page page;
    private final OffersPage offersPage;

    public OffersSteps(Page page) {
        this.page = page;
        this.offersPage = new OffersPage(page);
    }

    @Step("Wait for Offers page to load")
    public OffersSteps waitForOffersPageToLoad() {
        offersPage.offersHeader.first().waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(10000));
        return this;
    }

    @Step("Verify page header is visible")
    public OffersSteps verifyPageHeaderIsVisible() {
        assertTrue(offersPage.offersHeader.first().isVisible(),
                "Page header is not visible");
        return this;
    }

    @Step("click Read More button to navigate to all offers")
    public OffersSteps goToAllOffers(){
        offersPage.readMoreBtn.waitFor();
        offersPage.readMoreBtn.click();
        return this;
    }

    @Step("Verify Locations page URL")
    public OffersSteps verifyOffersPageURL() {
        String actualUrl = page.url();
        assertTrue(actualUrl.equals(OFFERS_PAGE_URL),
                "Expected URL: " + OFFERS_PAGE_URL + ", but got: " + actualUrl);
        return this;
    }

    @Step("assert all offers page loaded")
    public OffersSteps assertAllOffersPageLoaded(){
        assertThat(offersPage.offerCards.first()).isVisible();
        return this;
    }

    @Step("Manipulate browser time: Move forward by {days} day(s)")
    public OffersSteps shiftBrowserTime(int days) {
        System.out.println("[INFO] Attempting to shift browser time by " + days + " day(s)...");

        long offsetMillis = (long) days * 24 * 60 * 60 * 1000;

        String script = String.format(
                "(() => {" +
                        "  const offset = %d;" +
                        "  const OriginalDate = window.Date;" +
                        "  function MockDate(...args) {" +
                        "    if (args.length === 0) return new OriginalDate(OriginalDate.now() + offset);" +
                        "    return new OriginalDate(...args);" +
                        "  }" +
                        "  MockDate.prototype = OriginalDate.prototype;" +
                        "  MockDate.now = () => OriginalDate.now() + offset;" +
                        "  MockDate.UTC = OriginalDate.UTC;" +
                        "  MockDate.parse = OriginalDate.parse;" +
                        "  window.Date = MockDate;" +
                        "})();", offsetMillis);

        page.evaluate(script);
        System.out.println("[SUCCESS] Browser clock hijacked. New 'now' is " + days + " day(s) in the future.");
        return this;
    }

    @Step("Capture and print countdown for offer: {offerTitle}")
    public String getOfferCountdownText(String offerTitle) {
        System.out.println("[DEBUG] Searching for card where h3 contains: " + offerTitle);

        Locator targetCard = offersPage.offerCards
                .filter(new Locator.FilterOptions().setHas(
                        offersPage.offerTitle.filter(new Locator.FilterOptions().setHasText(offerTitle))
                ));

        targetCard.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        targetCard.first().scrollIntoViewIfNeeded();

        String countdownValue = targetCard.locator(offersPage.countdownText).first().innerText();

        System.out.println("[DATA] Success! Countdown for '" + offerTitle + "' is: " + countdownValue);
        return countdownValue;
    }

    @Step("Verify the countdown state changed")
    public void verifyCountdownDecreased(String beforeTime, String afterTime) {
        System.out.println("[VERIFY] Comparing states -> Before: " + beforeTime + " | After: " + afterTime);
        Assert.assertNotEquals(beforeTime, afterTime,
                "The countdown text should have updated after advancing time!");
    }
}