package ge.tbc.testautomation.tbcbankapp.ui.tests;

import ge.tbc.testautomation.tbcbankapp.ui.base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("TBC Bank Web Application")
@Feature("Locations & ATMs")
public class CalendarTest extends BaseTest {

    @Story("Homepage Access")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Open the homepage and verify that the main page elements and navigation menu are visible.")
    @Test(description = "DEV-T1 Step 1: Homepage access", priority = 1)
    public void homepageAccess() {
        homeSteps
                .openHomepage()
                .verifyHomepageLoaded()
                .verifyMenuVisibility()
                .openNavigationMenu();
    }

    @Story("Navigation Menu Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the dropdown navigation menu is visible and the Locations option is accessible.")
    @Test(description = "DEV-T1 Step 2: Navigation menu access",
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
    @Test(description = "DEV-T1 Step 3: Locations page selection",
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

    @Story("Branch Working Hours")
    @Severity(SeverityLevel.NORMAL)
    @Description("Print all branches by working hours schedule.")
    @Test(description = "Branches calendar",
            priority = 4,
            dependsOnMethods = "locationsPageSelection")
    public void branchesCalendar() {
        locationSteps
                .clickBranchOptions();
    }

    @Story("Branch Working Hours")
    @Severity(SeverityLevel.NORMAL)
    @Description("Print all branches by working hours schedule.")
    @Test(description = "Branches calendar results",
            priority = 5,
            dependsOnMethods = "branchesCalendar")
    public void branchesCalendarResults() {
        locationSteps
                .printMondayToFridayBranches("Branches")
                .printSundayBranches("Branches")
                .printSaturdayBranches("Branches")
                .printAllWeekBranches("Branches")
                .printMondayToFridayAndSaturdayBranches("Branches");
    }

    @Story("Money Input Working Hours")
    @Severity(SeverityLevel.NORMAL)
    @Description("Print all money input devices by working hours schedule.")
    @Test(description = "Money input calendar",
            priority = 6,
            dependsOnMethods = "locationsPageSelection")
    public void moneyInputCalendar() {
        locationSteps
                .clickMoneyInputOptions();
    }

    @Story("Money Input Working Hours")
    @Severity(SeverityLevel.NORMAL)
    @Description("Print all money input devices by working hours schedule.")
    @Test(description = "Money input calendar results",
            priority = 7,
            dependsOnMethods = "moneyInputCalendar")
    public void moneyInputCalendarResults() {
        locationSteps
                .printMondayToFridayBranches("Money Input Devices")
                .printSundayBranches("Money Input Devices")
                .printSaturdayBranches("Money Input Devices")
                .printAllWeekBranches("Money Input Devices")
                .printMondayToFridayAndSaturdayBranches("Money Input Devices");
    }
}