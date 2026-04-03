package ge.tbc.testautomation.tbcbankapp.ui.tests;

import ge.tbc.testautomation.tbcbankapp.ui.base.BaseTest;
import ge.tbc.testautomation.tbcbankapp.ui.data.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class OfferTimeTravelTest extends BaseTest {
    @Story("Homepage Access")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Open the homepage and verify that the main page elements and navigation menu are visible.")
    @Test(description = "SCRUM-T9 Step 1: Homepage access", priority = 1)
    public void homepageAccess() {
        homeSteps
                .verifyHomepageLoaded()
                .verifyMenuVisibility()
                .openNavigationMenu();
    }

    @Story("Navigation Menu Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the dropdown navigation menu is visible and the Offers option is accessible.")
    @Test(description = "SCRUM-T9 Step 2: Navigation menu access",
            priority = 2,
            dependsOnMethods = "homepageAccess")
    public void navMenuAccess() {
        homeSteps
                .verifyDropDownMenuVisibility()
                .verifyOffersOptionVisibility();
    }

    @Story("Offers Page Access")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Open the Offers page and validate the page header and URL are displayed correctly.")
    @Test(description = "SCRUM-T9 Step 3: Navigate to offers page",
            priority = 3,
            dependsOnMethods = "navMenuAccess")
    public void goToOffersPage() {
        homeSteps
                .openOffersPage();

        offersSteps
                .waitForOffersPageToLoad()
                .verifyOffersPageURL()
                .verifyPageHeaderIsVisible();
    }

    @Story("All Offers Page Access")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Navigate to all offers page and assert the offer containers are displayed correctly.")
    @Test(description = "SCRUM-T9 Step 4: Navigate To All Offers",
            priority = 4,
            dependsOnMethods = "goToOffersPage")
    public void goToAllOffers(){
        offersSteps
                .goToAllOffers()
                .assertAllOffersPageLoaded();
    }

    @Story("Time Travel Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Move time forward by 1 day and verify the countdown decreases.")
    @Test(description = "SCRUM-T9 Step 5: Time travel and countdown validation",
            priority = 5,
            dependsOnMethods = "goToAllOffers")
    public void validateTimeTravelCountdown() {


        String beforeTime = offersSteps.getOfferCountdownText(Constants.OffersData.TARGET_OFFER);

        offersSteps.shiftBrowserTime(Constants.OffersData.DAYS_INTO_FUTURE);   //uvargisi chans magram ar moashorot usafrtxoebistvisaa

        page.reload();
        offersSteps.shiftBrowserTime(Constants.OffersData.DAYS_INTO_FUTURE);

        String afterTime = offersSteps.getOfferCountdownText(Constants.OffersData.TARGET_OFFER);

        offersSteps.verifyCountdownDecreased(beforeTime, afterTime);
    }
}
