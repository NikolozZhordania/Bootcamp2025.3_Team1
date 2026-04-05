package ge.tbc.testautomation.tbcbankapp.ui.tests;

import ge.tbc.testautomation.tbcbankapp.ui.base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("TBC Bank Web Application")
@Feature("Locations & ATMs")
public class RandomizedLocationTests extends BaseTest {

    @BeforeClass
    public void logging() {
        System.out.println("District: " + currentDistrict.name());
        System.out.println("Lat: " + currentLocation[0] + " | Lng: " + currentLocation[1]);
    }

    @Story("Homepage Access")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Open the homepage and verify that the main page elements and navigation menu are visible.")
    @Test(description = "SCRUM-T14 Step 1: Homepage access", priority = 1)
    public void homepageAccess() {
        homeSteps
                .verifyHomepageLoaded()
                .verifyMenuVisibility()
                .openNavigationMenu();
    }

    @Story("Navigation Menu Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the dropdown navigation menu is visible and the Locations option is accessible.")
    @Test(description = "SCRUM-T14 Step 2: Navigation menu access",
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
    @Test(description = "SCRUM-T14 Step 3: Locations page selection",
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

    @Story("ATM Locations")
    @Severity(SeverityLevel.NORMAL)
    @Description("Print first 10 ATM location titles from the list.")
    @Test(description = "SCRUM-T14 Step 4: 10 Closest ATMs by Randomized Location",
            priority = 4,
            dependsOnMethods = "locationsPageSelection")
    public void randomizedTenClosestAtms() {
        System.out.println("\n10 Closest ATMs ");
        locationSteps
                .clickAtmOptions()
                .printTenLocationTitles();
    }

    @Story("Branch Locations")
    @Severity(SeverityLevel.NORMAL)
    @Description("Print first 10 branch location titles from the list.")
    @Test(description = "SCRUM-T14 Step 5: 10 Closest Branches by Randomized Location",
            priority = 5,
            dependsOnMethods = "locationsPageSelection")
    public void randomizedTenClosestBranches() {
        System.out.println("\n10 Closest Branches ");
        locationSteps
                .clickBranchOptions()
                .printTenLocationTitles();
    }

    @Story("Money Input Locations")
    @Severity(SeverityLevel.NORMAL)
    @Description("Print first 10 money input location titles from the list.")
    @Test(description = "SCRUM-T14 Step 6: 10 Closest Money Input Devices by Randomized Location",
            priority = 6,
            dependsOnMethods = "locationsPageSelection")
    public void randomizedTenClosestMoneyInputDevice() {
        System.out.println("\n10 Closest Money Input Devices ");
        locationSteps
                .clickMoneyInputOptions()
                .printTenLocationTitles();
    }
}