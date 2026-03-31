package ge.tbc.testautomation.tbcbankapp.ui.tests;

import ge.tbc.testautomation.tbcbankapp.ui.base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.tbcbankapp.ui.utils.FakerHelper.randomString;

@Epic("TBC Bank Web Application")
@Feature("Locations & ATMs")
@Test(description = "SCRUM-T9: ATM Search with Oversized Input and UI Stability Validation")
public class OversizedInputFilterTest extends BaseTest {

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
    @Description("Verify that the dropdown navigation menu is visible and the Locations option is accessible.")
    @Test(description = "SCRUM-T9 Step 2: Navigation menu access",
            priority = 2,
            dependsOnMethods = "homepageAccess")
    public void navMenuAccess() {
        homeSteps
                .verifyDropDownMenuVisibility()
                .verifyLocationsOptionVisibility();
    }

    @Story("Locations Page Access")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Open the Locations page and validate the page header and URL are displayed correctly.")
    @Test(description = "SCRUM-T9 Step 3: Locations page selection",
            priority = 3,
            dependsOnMethods = "navMenuAccess")
    public void locationsPageSelection() {
        homeSteps
                .openLocationsPage();

        locationSteps
                .waitForLocationsPageToLoad()
                .verifyLocationsPageURL()
                .verifyPageHeaderIsVisible();
    }

    @Story("ATM Service Point Selection")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Select the ATM service point option and verify the ATM title appears.")
    @Test(description = "SCRUM-T9 Step 4: ATM service point selection",
            priority = 4,
            dependsOnMethods = "locationsPageSelection")
    public void servicePointSelection() {
        locationSteps
                .waitForATMServiceButton()
                .clickATMServiceButton()
                .waitForATMTitleToAppear();
    }

    @Story("Oversized Input Entry")
    @Severity(SeverityLevel.NORMAL)
    @Description("Enter a randomly generated 500-character string into the location input field.")
    @Test(description = "SCRUM-T9 Step 5: Oversized input entry",
            priority = 5,
            dependsOnMethods = "servicePointSelection")
    public void oversizedInputEntry() {
        locationSteps
                .waitForLocationInput()
                .typeInLocationInput(randomString(500))
                .waitForATMListToUpdate();
    }

    @Story("ATM List Response to Oversized Input")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the ATM list returns zero results and the page remains stable after oversized input.")
    @Test(description = "SCRUM-T9 Step 6: ATM list zero results validation",
            priority = 6,
            dependsOnMethods = "oversizedInputEntry")
    public void oversizedInputResultValidation() {
        locationSteps
                .logATMListCount()
                .verifyAtmListCount();
    }

    @Story("Page Stability After Oversized Input")
    @Severity(SeverityLevel.NORMAL)
    @Description("Clear the input field and verify the default ATM list is restored and filter buttons remain functional.")
    @Test(description = "SCRUM-T9 Step 7: Page stability validation after oversized input",
            priority = 7,
            dependsOnMethods = "oversizedInputResultValidation")
    public void pageStabilityAfterOversizedInput() {
        locationSteps
                .waitForLocationInput()
                .typeInLocationInput("")
                .waitForATMListToUpdate()
                .verifyATMListIsNotEmpty();
    }
}