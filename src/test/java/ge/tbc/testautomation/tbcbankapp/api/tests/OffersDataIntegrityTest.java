package ge.tbc.testautomation.tbcbankapp.api.tests;

import ge.tbc.testautomation.tbcbankapp.api.steps.OffersSteps;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Feature("Offers API")
public class OffersDataIntegrityTest {

    @Story("Offers Structure")
    @Description("POST /offer returns 200 with a non-empty list of valid offer objects")
    @Test(description = "POST /offer returns 200 with valid structure")
    public void offersStructureTest() {
        new OffersSteps()
                .fetchOffers()
                .validateOffersNotEmpty()
                .validateOfferFields();
    }

    @Story("Offer Date Integrity")
    @Description("Every offer's endDate is strictly after its startDate")
    @Test(description = "endDate is always after startDate for every active offer")
    public void endDateAfterStartDateTest() {
        new OffersSteps()
                .fetchOffers()
                .validateOffersNotEmpty()
                .validateEndDateAfterStartDate();
    }

    @Story("Offer Date Integrity")
    @Description("No offer in the active list has an endDate that has already passed in Tbilisi time")
    @Test(description = "Active offers must not have expired endDates")
    public void noExpiredOffersInActiveListTest() {
        new OffersSteps()
                .fetchOffers()
                .validateOffersNotEmpty()
                .validateNoOffersExpired();
    }

    @Story("Offer Countdown Integrity")
    @Description("Remaining days calculated from endDate must be >= 0 for all active offers in Tbilisi timezone")
    @Test(description = "Remaining days derived from endDate are non-negative for all active offers")
    public void remainingDaysArePositiveTest() {
        new OffersSteps()
                .fetchOffers()
                .validateOffersNotEmpty()
                .validateRemainingDaysArePositive();
    }
}
