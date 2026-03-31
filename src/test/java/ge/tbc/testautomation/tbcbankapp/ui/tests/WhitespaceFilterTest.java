package ge.tbc.testautomation.tbcbankapp.ui.tests;

import ge.tbc.testautomation.tbcbankapp.ui.base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("TBC Bank Web Application")
@Feature("Locations & ATMs")
@Test(description = "SCRUM-T8: ATM Search with Whitespace-Only Input and Empty Result Validation")
public class WhitespaceFilterTest extends BaseTest {

    @Story("Homepage Access")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Open the homepage and verify that the main page elements and navigation menu are visible.")
    @Test(description = "SCRUM-T8 Step 1: Homepage access", priority = 1)
    public void homepageAccess() {
        homeSteps
                .verifyHomepageLoaded()
                .verifyMenuVisibility()
                .openNavigationMenu();
    }

    @Story("Navigation Menu Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the dropdown navigation menu is visible and the Locations option is accessible.")
    @Test(description = "SCRUM-T8 Step 2: Navigation menu access",
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
    @Test(description = "SCRUM-T8 Step 3: Locations page selection",
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
    @Test(description = "SCRUM-T8 Step 4: ATM service point selection",
            priority = 4,
            dependsOnMethods = "locationsPageSelection")
    public void servicePointSelection() {
        locationSteps
                .waitForATMServiceButton()
                .clickATMServiceButton()
                .waitForATMTitleToAppear();
    }

    @Story("Whitespace Input Entry")
    @Severity(SeverityLevel.NORMAL)
    @Description("Enter whitespace-only characters into the location input field.")
    @Test(description = "SCRUM-T8 Step 5: Whitespace input entry",
            priority = 5,
            dependsOnMethods = "servicePointSelection")
    public void whitespaceInputEntry() {
        locationSteps
                .waitForLocationInput()
                .typeInLocationInput("   ")
                .waitForATMListToUpdate();
    }

    @Story("ATM List Response Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the ATM list remains unchanged after whitespace input, with no crash or errors.")
    @Test(description = "SCRUM-T8 Step 6: ATM list response to whitespace input validation",
            priority = 6,
            dependsOnMethods = "whitespaceInputEntry")
    public void whitespaceInputResultValidation() {
        locationSteps
                .logATMListCount()
                .verifyATMListIsNotEmpty();
    }
}