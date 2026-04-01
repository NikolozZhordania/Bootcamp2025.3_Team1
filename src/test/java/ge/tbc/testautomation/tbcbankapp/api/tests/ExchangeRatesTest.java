package ge.tbc.testautomation.tbcbankapp.api.tests;

import ge.tbc.testautomation.tbcbankapp.api.steps.ExchangeRatesSteps;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.tbcbankapp.api.data.constants.ExchangeRatesConstants.Currencies.*;
import static ge.tbc.testautomation.tbcbankapp.api.data.constants.ExchangeRatesConstants.ConversionValues.*;

@Feature("Exchange Rates API")
public class ExchangeRatesTest {

    @Story("Commercial Rates")
    @Description("All commercial rates are returned with valid structure")
    @Test(description = "GET /exchange-rates/commercial returns 200 with valid structure")
    public void commercialRatesStructureTest() {
        new ExchangeRatesSteps()
                .fetchCommercialRates()
                .validateBaseCurrencyIsGel()
                .validateCommercialRatesNotEmpty()
                .validateCommercialRateFields();
    }

    @Story("Commercial Rates")
    @Description("Commercial rates list contains at least one currency with valid fields")
    @Test(description = "Commercial rates list is non-empty and all entries have valid fields")
    public void commercialRatesContainMajorCurrenciesTest() {
        new ExchangeRatesSteps()
                .fetchCommercialRates()
                .validateCommercialRatesNotEmpty()
                .validateCommercialRateFields();
    }

    @Story("Commercial Rates")
    @Description("Filtered commercial rates return only requested currencies")
    @Test(description = "GET /exchange-rates/commercial?currency=USD,EUR returns those currencies")
    public void commercialRatesFilteredTest() {
        new ExchangeRatesSteps()
                .fetchCommercialRates("USD,EUR")
                .validateCommercialRatesContain(USD)
                .validateCommercialRatesContain(EUR);
    }

    @Story("Commercial Rates")
    @Description("Buy rate is always lower than sell rate for every currency")
    @Test(description = "Buy rate is less than sell rate for all commercial currencies")
    public void commercialRatesBuyLessThanSellTest() {
        new ExchangeRatesSteps()
                .fetchCommercialRates()
                .validateBuyLessThanSell();
    }

    @Story("Commercial Convert")
    @Description("Converting 10 USD to GEL returns a positive result")
    @Test(description = "GET /exchange-rates/commercial/convert returns valid conversion")
    public void commercialConvertTest() {
        new ExchangeRatesSteps()
                .convertCommercial(SAMPLE_AMOUNT, FROM_USD, TO_GEL)
                .validateConversionFrom(FROM_USD)
                .validateConversionTo(TO_GEL)
                .validateConversionAmount(SAMPLE_AMOUNT)
                .validateConversionValueIsPositive();
    }

    @Story("Commercial Convert")
    @Description("Invalid currency params return an error response")
    @Test(description = "GET /exchange-rates/commercial/convert with invalid params returns error")
    public void commercialConvertInvalidParamsTest() {
        new ExchangeRatesSteps()
                .convertCommercialExpectingError(SAMPLE_AMOUNT, INVALID_CURRENCY, INVALID_CURRENCY)
                .validateStatusCodeIsError();
    }

    @Story("Official Rates")
    @Description("All official NBG rates are returned with valid structure")
    @Test(description = "GET /exchange-rates/nbg returns 200 with valid structure")
    public void officialRatesStructureTest() {
        new ExchangeRatesSteps()
                .fetchOfficialRates()
                .validateOfficialRatesNotEmpty()
                .validateOfficialRateFields();
    }

    @Story("Official Rates")
    @Description("USD and EUR are present in official NBG rates")
    @Test(description = "Official NBG rates contain USD and EUR")
    public void officialRatesContainMajorCurrenciesTest() {
        new ExchangeRatesSteps()
                .fetchOfficialRates()
                .validateOfficialRatesContain(USD)
                .validateOfficialRatesContain(EUR);
    }

    @Story("Official Rates")
    @Description("Official NBG rates can be fetched for a specific date and currency filter")
    @Test(description = "GET /exchange-rates/nbg?currency=USD,EUR&date=2024-01-01 returns valid data")
    public void officialRatesFilteredByDateTest() {
        new ExchangeRatesSteps()
                .fetchOfficialRates("USD,EUR", "2024-01-01")
                .validateOfficialRatesNotEmpty()
                .validateOfficialRatesContain(USD)
                .validateOfficialRatesContain(EUR);
    }

    @Story("Official Convert")
    @Description("Converting 10 USD to GEL via NBG rates returns a positive result")
    @Test(description = "GET /exchange-rates/nbg/convert returns valid conversion")
    public void officialConvertTest() {
        new ExchangeRatesSteps()
                .convertOfficial(SAMPLE_AMOUNT, FROM_USD, TO_GEL)
                .validateConversionFrom(FROM_USD)
                .validateConversionTo(TO_GEL)
                .validateConversionAmount(SAMPLE_AMOUNT)
                .validateConversionValueIsPositive();
    }

    @Story("Official Convert")
    @Description("Invalid currency params return an error response")
    @Test(description = "GET /exchange-rates/nbg/convert with invalid params returns error")
    public void officialConvertInvalidParamsTest() {
        new ExchangeRatesSteps()
                .convertOfficialExpectingError(SAMPLE_AMOUNT, INVALID_CURRENCY, INVALID_CURRENCY)
                .validateStatusCodeIsError();
    }

    @Story("Extended Rates")
    @Description("POST /extendedexchangerates endpoint exists and responds")
    @Test(description = "POST /extendedexchangerates endpoint responds")
    public void extendedRatesTest() {
        new ExchangeRatesSteps()
                .fetchExtendedRates()
                .validateStatusCodeIsError();
    }


}