package ge.tbc.testautomation.tbcbankapp.ui.tests;

import ge.tbc.testautomation.tbcbankapp.ui.base.BaseTest;
import ge.tbc.testautomation.tbcbankapp.ui.data.CurrencyExchangeDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Feature("Abroad Money Transfer")
public class CurrencyExchangeTest extends BaseTest {

    @Story("Homepage Access")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Open the homepage and verify that the main page elements and navigation menu are visible.")
    @Test(description = "SCRUM-T12 Step 1: Homepage access", priority = 1)
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
    @Test(description = "SCRUM-T12 Step 2: Navigation menu access",
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
    @Test(description = "SCRUM-T12 Step 3: Currency Exchange Page Navigation",
            priority = 3,
            dependsOnMethods = "navMenuAccess")
    public void locationsPageSelection() {
        homeSteps
                .openCurrencyExchangePage();
    }

    @Test(
            dataProvider = "transferData",
            dataProviderClass = CurrencyExchangeDataProvider.class,
            dependsOnMethods = "locationsPageSelection",
            priority = 4,
            description = "SCRUM-T12 Step 4: Currency Conversion Verification"
    )
    @Story("User converts currency using the exchange rate calculator")
    @Description("Selects source and target currencies, inputs a transfer amount, " +
            "and verifies that the converted output is valid and non-zero")
    @Severity(SeverityLevel.CRITICAL)
    public void currencyTest(String from, String to, String amount) {
        currencyExchangeSteps
                .inputCurrency(from)
                .outputCurrency(to)
                .inputMoney(amount)
                .verifyConversion();
    }
}