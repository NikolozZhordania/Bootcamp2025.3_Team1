package ge.tbc.testautomation.tbcbankapp.api.tests;

import ge.tbc.testautomation.tbcbankapp.api.base.BaseTest;
import ge.tbc.testautomation.tbcbankapp.api.steps.OffersSteps;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Feature("Offers API")
public class OffersDataIntegrityTest extends BaseTest {

    @Story("Offers Structure")
    @Description("Verify that the /offer endpoint returns a valid, non-empty list of offer objects.")
    @Test(description = "SCRUM-T16 Step 1: Offers API Response Structure Validation")
    public void offersStructureTest() {
        offersSteps
                .fetchOffers()
                .validateOffersNotEmpty()
                .validateOfferFields();
    }

    @Story("Offer Date Integrity")
    @Description("Verify that for every offer, the endDate is chronologically after the startDate.")
    @Test(description = "SCRUM-T16 Step 2: Offers Logical Date Range Validation")
    public void endDateAfterStartDateTest() {
        offersSteps
                .fetchOffers()
                .validateOffersNotEmpty()
                .validateEndDateAfterStartDate();
    }

    @Story("Offer Date Integrity")
    @Description("Verify that no offers returned in the active list have an endDate in the past.")
    @Test(description = "SCRUM-T16 Step 3: Active Offer Expiration Check")
    public void noExpiredOffersInActiveListTest() {
        offersSteps
                .fetchOffers()
                .validateOffersNotEmpty()
                .validateNoOffersExpired();
    }

    @Story("Offer Countdown Integrity")
    @Description("Verify that the calculated remaining days for all offers is a non-negative value.")
    @Test(description = "SCRUM-T16 Step 4: Countdown Value Integrity Check")
    public void remainingDaysArePositiveTest() {
        offersSteps
                .fetchOffers()
                .validateOffersNotEmpty()
                .validateRemainingDaysArePositive();
    }
}