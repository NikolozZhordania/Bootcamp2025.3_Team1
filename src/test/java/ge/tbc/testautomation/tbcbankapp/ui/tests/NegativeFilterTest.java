package ge.tbc.testautomation.tbcbankapp.ui.tests;

import ge.tbc.testautomation.tbcbankapp.ui.base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

@Epic("TBC Bank Web Application")
@Feature("Locations & ATMs")
@Test(description = "DEV-T4: Negative scenario for ATM filtering")
public class NegativeFilterTest extends BaseTest {

    @Story("Homepage Access")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Open the homepage and verify that the main page elements and navigation menu are visible.")
    @Test(description = "DEV-T4 Step 1: Homepage access", priority = 1)
    public void homepageAccess() {
        homeSteps
                .openHomepage()
                .verifyHomepageLoaded();
    }
    @Story("Kebab Menu Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the Kebab menu is visible and the Locations option is accessible.")
    @Test(description = "DEV-T4 Step 2: Navigation menu access",
            priority = 2,
            dependsOnMethods = "homepageAccess")
    public void kebabMenuAccess() {
        homeSteps
                .verifyKebabMenuVisibility()
                .openKebabMenu();
    }
    @Story("Locations Page Access")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Open the Locations page and validate the page header and URL are displayed correctly.")
    @Test(description = "DEV-T4 Step 3: Locations page selection",
            priority = 3,
            dependsOnMethods = "KebabMenuAccess")
    public void locationsPageSelection() {
        homeSteps
                .verifyLocationsIconVisibility()
                .openLocations();

        locationSteps
                .waitForLocationsPageToLoad()
                .verifyLocationsPageURL()
                .verifyPageHeaderIsVisible();
    }
    @Story("Bank Service Point Selection")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Select the ATM service point option")
    @Test(description = "DEV-T4 Step 4: Bank service point selection (ATM)",
            priority = 4,
            dependsOnMethods = "locationsPageSelection")
    public void servicePointSelection() {
        locationSteps
                .waitForATMServiceButton()
                .clickATMServiceButton()
                .waitForATMTitleToAppear();

    }
    @Story("Location Input Filtering")
    @Severity(SeverityLevel.NORMAL)
    @Description("Enter a random street name in the location input to filter ATMs and Verify that the filtered ATM list is empty.")
    @Test(description = "DEV-T4 Step 5: Location input and filtering",
            priority = 5,
            dependsOnMethods = "servicePointSelection")
    public void locationInput() {
        Faker faker = new Faker();
        String randomStreet = faker.address().streetName();
        locationSteps
                .typeInLocationInput(randomStreet)
                .waitForATMListToUpdate()
                .logATMListCount()
                .verifyAtmListCount();

    }



}