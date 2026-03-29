package ge.tbc.testautomation.tbcbankapp.ui.tests;

import ge.tbc.testautomation.tbcbankapp.ui.base.BaseTest;
import ge.tbc.testautomation.tbcbankapp.ui.data.constants.DivisionData;
import io.qameta.allure.*;
import org.testng.annotations.Test;


@Epic("TBC Bank Web Application")
@Feature("Locations & ATMs")
@Test(description = "DEV-T3: View 24/7 Branches")
public class FilterLocations extends BaseTest {

    @Story("Homepage Access")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Open the homepage and verify that the main page elements and navigation menu are visible.")
    @Test(description = "DEV-T2 Step 1: Homepage access", priority = 1)
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
    @Test(description = "DEV-T3 Step 2: Navigation menu access",
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
    @Test(description = "DEV-T3 Step 3: Locations page selection",
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
    @Story("Branch Tab Selection")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Select the Branch Tab. ")
    @Test(description = "DEV-T3 Step 4:Branch Selection",
            priority = 4,
            dependsOnMethods = "locationsPageSelection")
    public void branchTabSelection() {
        locationSteps
                .waitForBranchButton()
                .clickBranchButton()
                .waitForBranchTitleToAppear();
    }
    @Story("24/7 filter Selection")
    @Severity(SeverityLevel.NORMAL)
    @Description("Select the filter.")
    @Test(description = "DEV-T3 Step 5:filter Selection",
            priority = 5,
            dependsOnMethods = "branchTabSelection")
    public void filterSelection() {
        locationSteps
                .waitForTimeFilterButton()
                .clickTimeFilterButton();
    }

    @Story("Branch District Selection")
    @Severity(SeverityLevel.NORMAL)
    @Description("Type in the location input to filter Branches, verify filtered results")
    @Test(
            description = "DEV-T3 Step 6: Branch District input",
            dataProvider = "DistrictNames",
            dataProviderClass = DivisionData.class,
            priority = 6
    )
    public void searchDistrictAndVerify(String location) {
        locationSteps
                .waitForLocationInput()
                .typeInLocationInput(location)
                .waitForBranchListToUpdate()
                .verifyBranchCount()
                .verifyBranchResults();
    }

}
