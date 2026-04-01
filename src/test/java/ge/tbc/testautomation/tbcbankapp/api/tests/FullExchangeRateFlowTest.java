package ge.tbc.testautomation.tbcbankapp.api.tests;

import ge.tbc.testautomation.tbcbankapp.api.base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.tbcbankapp.api.data.constants.ExchangeRatesConstants.ConversionValues.SAMPLE_AMOUNT;
import static ge.tbc.testautomation.tbcbankapp.api.data.constants.ExchangeRatesConstants.ConversionValues.TO_GEL;

@Epic("TBC Bank API")
@Feature("Exchange Rates")
@Test(description = "SCRUM-T10: Exchange Rates API - Commercial vs Official Conversion Consistency For Dynamically Selected Currency")
public class FullExchangeRateFlowTest extends BaseTest {

    @Test(description = "SCRUM-T10 Step 1: Commercial Rates Retrieval",
            priority = 1)
    @Story("Commercial Rates Retrieval")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Retrieve commercial exchange rates from API and ensure response is successfully returned.")
    public void fetchCommercialRates() {
        exchangeRatesSteps
                .fetchCommercialRates();
    }

    @Test(description = "SCRUM-T10 Step 2: Commercial Rates Validation",
            priority = 2,
            dependsOnMethods = "fetchCommercialRates")
    @Story("Commercial Rates Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validate that commercial exchange rates contain correct base currency, non-empty list, valid fields, and logical buy/sell values.")
    public void validateCommercialRates() {
        exchangeRatesSteps
                .validateBaseCurrencyIsGel()
                .validateCommercialRatesNotEmpty()
                .validateCommercialRateFields()
                .validateBuyLessThanSell();
    }

    @Test(description = "SCRUM-T10 Step 3: Active Currency Selection",
            priority = 3,
            dependsOnMethods = "validateCommercialRates")
    @Story("Active Currency Selection")
    @Severity(SeverityLevel.NORMAL)
    @Description("Select the first available currency dynamically from the commercial rates response for further operations.")
    public void pickCurrency() {
        exchangeRatesSteps
                .pickFirstAvailableCurrency();
    }

    @Test(description = "SCRUM-T10 Step 4: Commercial Conversion Execution",
            priority = 4,
            dependsOnMethods = "pickCurrency")
    @Story("Commercial Conversion")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Convert selected currency to GEL using commercial exchange rates and validate conversion correctness.")
    public void convertCommercial() {
        exchangeRatesSteps
                .convertCommercialWithActiveCurrency(SAMPLE_AMOUNT, TO_GEL)
                .validateConversionTo(TO_GEL)
                .validateConversionAmount(SAMPLE_AMOUNT)
                .validateConversionValueIsPositive();
    }

    @Test(description = "SCRUM-T10 Step 5: Official (NBG) Rates Retrieval & Validation",
            priority = 5,
            dependsOnMethods = "convertCommercial")
    @Story("Official Rates Retrieval")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Retrieve official (NBG) exchange rates and validate that response contains valid data.")
    public void fetchOfficialRates() {
        exchangeRatesSteps
                .fetchOfficialRates()
                .validateOfficialRatesNotEmpty()
                .validateOfficialRateFields();
    }

    @Test(description = "SCRUM-T10 Step 6: Official Conversion Execution",
            priority = 6,
            dependsOnMethods = "fetchOfficialRates")
    @Story("Official Conversion")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Convert selected currency to GEL using official exchange rates and validate conversion correctness.")
    public void convertOfficial() {
        exchangeRatesSteps
                .convertOfficialWithActiveCurrency(SAMPLE_AMOUNT, TO_GEL)
                .validateConversionTo(TO_GEL)
                .validateConversionAmount(SAMPLE_AMOUNT)
                .validateConversionValueIsPositive();
    }

    @Test(description = "SCRUM-T10 Step 7: Exchange Rate Consistency Validation",
            priority = 7,
            dependsOnMethods = "convertOfficial")
    @Story("Exchange Rate Comparison")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Validate that the commercial sell rate is higher than the official exchange rate for the selected currency, ensuring correct business logic.")
    public void compareRates() {
        exchangeRatesSteps
                .validateCommercialSellHigherThanOfficial();
    }
}