package ge.tbc.testautomation.tbcbankapp.api;

import ge.tbc.testautomation.tbcbankapp.api.steps.ExchangeRatesSteps;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FullExchangeRateFlowTest {
    private ExchangeRatesSteps exchangeRatesSteps;
    private static final double SAMPLE_AMOUNT = 100;
    private static final String TO_GEL = "GEL";

    @BeforeClass
    public void setup() {
        exchangeRatesSteps = new ExchangeRatesSteps();
    }

    @Test(priority = 1)
    @Story("Fetch commercial rates")
    @Severity(SeverityLevel.CRITICAL)
    public void fetchCommercialRates() {
        exchangeRatesSteps
                .fetchCommercialRates();
    }

    @Test(priority = 2, dependsOnMethods = "fetchCommercialRates")
    public void validateCommercialRates() {
        exchangeRatesSteps
                .validateBaseCurrencyIsGel()
                .validateCommercialRatesNotEmpty()
                .validateCommercialRateFields()
                .validateBuyLessThanSell();
    }

    @Test(priority = 3, dependsOnMethods = "validateCommercialRates")
    public void pickCurrency() {
        exchangeRatesSteps
                .pickFirstAvailableCurrency();
    }

    @Test(priority = 4, dependsOnMethods = "pickCurrency")
    public void convertCommercial() {
        exchangeRatesSteps
                .convertCommercialWithActiveCurrency(SAMPLE_AMOUNT, TO_GEL)
                .validateConversionTo(TO_GEL)
                .validateConversionAmount(SAMPLE_AMOUNT)
                .validateConversionValueIsPositive();
    }

    @Test(priority = 5, dependsOnMethods = "convertCommercial")
    public void fetchOfficialRates() {
        exchangeRatesSteps
                .fetchOfficialRates()
                .validateOfficialRatesNotEmpty()
                .validateOfficialRateFields();
    }

    @Test(priority = 6, dependsOnMethods = "fetchOfficialRates")
    public void convertOfficial() {
        exchangeRatesSteps
                .convertOfficialWithActiveCurrency(SAMPLE_AMOUNT, TO_GEL)
                .validateConversionTo(TO_GEL)
                .validateConversionAmount(SAMPLE_AMOUNT)
                .validateConversionValueIsPositive();
    }

    @Test(priority = 7, dependsOnMethods = "convertOfficial")
    public void compareRates() {
        exchangeRatesSteps
                .validateCommercialSellHigherThanOfficial();
    }
}
