package ge.tbc.testautomation.tbcbankapp.ui.tests;

import ge.tbc.testautomation.tbcbankapp.ui.base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import static ge.tbc.testautomation.tbcbankapp.ui.data.Constants.LocationData.CITY_NAME;
import static ge.tbc.testautomation.tbcbankapp.ui.data.Constants.LocationData.*;

@Epic("TBC Bank Web Application")
@Feature("Locations & ATMs")
public class LocationBranch extends BaseTest {

    @Story("Homepage Navigation")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that the homepage is loaded successfully and the main navigation menu is visible and accessible.")
    @Test(description = "SCRUM-T15   Step 1: Homepage access", priority = 1)
    public void homepageAccess() {
        homeSteps
                .verifyHomepageLoaded()
                .verifyMenuVisibility()
                .openNavigationMenu();
    }

    @Story("Navigation Menu Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the navigation dropdown menu is displayed correctly and the Locations option is visible.")
    @Test(description = "SCRUM-T15 Step 2: Navigation menu access",
            priority = 2,
            dependsOnMethods = "homepageAccess")
    public void navMenuAccess() {
        homeSteps
                .verifyDropDownMenuVisibility()
                .verifyLocationsOptionVisibility();
    }

    @Story("Locations Page Access")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user can navigate to the Locations page and that the page loads with the correct URL and header.")
    @Test(description = "SCRUM-T15 Step 3: Locations Page Navigation",
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

    @Story("Branch Search and Selection")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that the user can filter bank service points by city, search by location, and select the  branch.")
    @Test(description = "SCRUM-T15 Step 4: Branch Service Point Selection ",
            priority = 4,
            dependsOnMethods = "locationsPageSelection")
    public void selectNearestBranchByCityAndLocation() {
        locationSteps
                .clickBranchOptions()
                .clickCityDropdown()
                .waitForCityOption(CITY_NAME)
                .clickCityOption(CITY_NAME)
                .typeInLocationInput(LOCATION_INPUT2)
                .chooseNearestBranch()
                .verifyBranchCount();
    }

    @Story("Selected Branch Validation")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that the selected branch is displayed on the map and highlighted with the expected icon color.")
    @Test(priority = 5,
            description = "SCRUM-T15 Step 5: Validate selected branch visibility",
            dependsOnMethods = "selectNearestBranchByCityAndLocation")
    public void assertSelectedBranchIsVisible() {
        locationSteps
                .assertBranchIconVisibleColor(RGB_ICON);
    }
}