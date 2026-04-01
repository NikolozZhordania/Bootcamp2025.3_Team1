package ge.tbc.testautomation.tbcbankapp.api.steps;

import ge.tbc.testautomation.tbcbankapp.api.client.ExchangeRatesAPI;
import ge.tbc.testautomation.tbcbankapp.api.data.models.response.exchangerates.*;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ExchangeRatesSteps {

    private final ExchangeRatesAPI api = new ExchangeRatesAPI();
    private Response rawResponse;
    private CommercialRatesResponse commercialRates;
    private List<OfficialRate> officialRates;
    private ConversionResponse conversion;
    private String activeCurrency;

    @Step("Fetch all commercial rates")
    public ExchangeRatesSteps fetchCommercialRates() {
        this.rawResponse = api.getCommercialRates();
        this.commercialRates = rawResponse
                .then()
                .statusCode(200)
                .extract()
                .as(CommercialRatesResponse.class);
        return this;
    }

    @Step("Fetch commercial rates for currencies: {currencies}")
    public ExchangeRatesSteps fetchCommercialRates(String currencies) {
        this.rawResponse = api.getCommercialRates(currencies);
        this.commercialRates = rawResponse
                .then()
                .statusCode(200)
                .extract()
                .as(CommercialRatesResponse.class);
        return this;
    }

    @Step("Fetch all official (NBG) rates")
    public ExchangeRatesSteps fetchOfficialRates() {
        this.rawResponse = api.getOfficialRates();
        OfficialRate[] arr = rawResponse
                .then()
                .statusCode(200)
                .extract()
                .as(OfficialRate[].class);
        this.officialRates = Arrays.asList(arr);
        return this;
    }

    @Step("Fetch official (NBG) rates for currencies: {currencies} on date: {date}")
    public ExchangeRatesSteps fetchOfficialRates(String currencies, String date) {
        this.rawResponse = api.getOfficialRates(currencies, date);
        OfficialRate[] arr = rawResponse
                .then()
                .statusCode(200)
                .extract()
                .as(OfficialRate[].class);
        this.officialRates = Arrays.asList(arr);
        return this;
    }

    @Step("Pick first available currency from commercial rates")
    public ExchangeRatesSteps pickFirstAvailableCurrency() {
        this.activeCurrency = commercialRates.getRates().stream()
                .map(CommercialRate::getCurrency)
                .findFirst()
                .orElseThrow(() -> new AssertionError("No currencies available in commercial rates"));
        return this;
    }

    @Step("Convert {amount} {from} to {to} using commercial rates")
    public ExchangeRatesSteps convertCommercial(double amount, String from, String to) {
        this.rawResponse = api.convertCommercial(amount, from, to);
        this.conversion = rawResponse
                .then()
                .statusCode(200)
                .extract()
                .as(ConversionResponse.class);
        return this;
    }

    @Step("Convert {amount} active currency to {to} using commercial rates")
    public ExchangeRatesSteps convertCommercialWithActiveCurrency(double amount, String to) {
        this.rawResponse = api.convertCommercial(amount, activeCurrency, to);
        this.conversion = rawResponse
                .then()
                .statusCode(200)
                .extract()
                .as(ConversionResponse.class);
        return this;
    }

    @Step("Convert {amount} {from} to {to} using official (NBG) rates")
    public ExchangeRatesSteps convertOfficial(double amount, String from, String to) {
        this.rawResponse = api.convertOfficial(amount, from, to);
        this.conversion = rawResponse
                .then()
                .statusCode(200)
                .extract()
                .as(ConversionResponse.class);
        return this;
    }

    @Step("Convert {amount} active currency to {to} using official (NBG) rates")
    public ExchangeRatesSteps convertOfficialWithActiveCurrency(double amount, String to) {
        this.rawResponse = api.convertOfficial(amount, activeCurrency, to);
        this.conversion = rawResponse
                .then()
                .statusCode(200)
                .extract()
                .as(ConversionResponse.class);
        return this;
    }

    @Step("Convert using commercial rates, expecting error")
    public ExchangeRatesSteps convertCommercialExpectingError(double amount, String from, String to) {
        this.rawResponse = api.convertCommercial(amount, from, to);
        return this;
    }

    @Step("Convert using official rates, expecting error")
    public ExchangeRatesSteps convertOfficialExpectingError(double amount, String from, String to) {
        this.rawResponse = api.convertOfficial(amount, from, to);
        return this;
    }

    @Step("Fetch extended commercial rates")
    public ExchangeRatesSteps fetchExtendedRates() {
        this.rawResponse = api.getExtendedRates();
        return this;
    }

    @Step("Validate commercial rates base currency is GEL")
    public ExchangeRatesSteps validateBaseCurrencyIsGel() {
        assertThat("Base currency must be GEL",
                commercialRates.getBase(), equalToIgnoringCase("GEL"));
        return this;
    }

    @Step("Validate commercial rates list is not empty")
    public ExchangeRatesSteps validateCommercialRatesNotEmpty() {
        assertThat("Commercial rates list must not be empty",
                commercialRates.getRates(), is(not(empty())));
        return this;
    }

    @Step("Validate all commercial rates have currency, buy and sell")
    public ExchangeRatesSteps validateCommercialRateFields() {
        commercialRates.getRates().forEach(rate -> {
            assertThat("Currency must not be blank", rate.getCurrency(), not(blankOrNullString()));
            assertThat("Buy rate must not be null",  rate.getBuy(),      notNullValue());
            assertThat("Sell rate must not be null", rate.getSell(),     notNullValue());
        });
        return this;
    }

    @Step("Validate commercial rates contain currency: {currency}")
    public ExchangeRatesSteps validateCommercialRatesContain(String currency) {
        boolean found = commercialRates.getRates().stream()
                .anyMatch(r -> currency.equalsIgnoreCase(r.getCurrency()));
        assertThat("Commercial rates must contain " + currency, found, is(true));
        return this;
    }

    @Step("Validate buy rate is less than sell rate for each currency")
    public ExchangeRatesSteps validateBuyLessThanSell() {
        commercialRates.getRates().forEach(rate ->
                assertThat("Buy must be less than sell for " + rate.getCurrency(),
                        rate.getBuy(), lessThan(rate.getSell())));
        return this;
    }

    @Step("Validate official rates list is not empty")
    public ExchangeRatesSteps validateOfficialRatesNotEmpty() {
        assertThat("Official rates list must not be empty",
                officialRates, is(not(empty())));
        return this;
    }

    @Step("Validate all official rates have currency and value")
    public ExchangeRatesSteps validateOfficialRateFields() {
        officialRates.forEach(rate -> {
            assertThat("Currency must not be blank", rate.getCurrency(), not(blankOrNullString()));
            assertThat("Value must not be null",     rate.getValue(),    notNullValue());
            assertThat("Value must be positive",     rate.getValue(),    greaterThan(0.0));
        });
        return this;
    }

    @Step("Validate official rates contain currency: {currency}")
    public ExchangeRatesSteps validateOfficialRatesContain(String currency) {
        boolean found = officialRates.stream()
                .anyMatch(r -> currency.equalsIgnoreCase(r.getCurrency()));
        assertThat("Official rates must contain " + currency, found, is(true));
        return this;
    }

    @Step("Validate conversion 'from' field is {expectedFrom}")
    public ExchangeRatesSteps validateConversionFrom(String expectedFrom) {
        assertThat("Conversion 'from' field",
                conversion.getFrom(), equalToIgnoringCase(expectedFrom));
        return this;
    }

    @Step("Validate conversion 'to' field is {expectedTo}")
    public ExchangeRatesSteps validateConversionTo(String expectedTo) {
        assertThat("Conversion 'to' field",
                conversion.getTo(), equalToIgnoringCase(expectedTo));
        return this;
    }

    @Step("Validate conversion amount is {expectedAmount}")
    public ExchangeRatesSteps validateConversionAmount(double expectedAmount) {
        assertThat("Conversion amount",
                conversion.getAmount(), equalTo(expectedAmount));
        return this;
    }

    @Step("Validate conversion result value is positive")
    public ExchangeRatesSteps validateConversionValueIsPositive() {
        assertThat("Conversion result must be positive",
                conversion.getValue(), greaterThan(0.0));
        return this;
    }

    @Step("Validate commercial sell rate is higher than official NBG rate for active currency")
    public ExchangeRatesSteps validateCommercialSellHigherThanOfficial() {
        double commercialSell = commercialRates.getRates().stream()
                .filter(r -> activeCurrency.equalsIgnoreCase(r.getCurrency()))
                .findFirst()
                .map(CommercialRate::getSell)
                .orElseThrow(() -> new AssertionError("Currency " + activeCurrency + " not found in commercial rates"));

        double officialValue = officialRates.stream()
                .filter(r -> activeCurrency.equalsIgnoreCase(r.getCurrency()))
                .findFirst()
                .map(OfficialRate::getValue)
                .orElseThrow(() -> new AssertionError("Currency " + activeCurrency + " not found in official rates"));

        assertThat("Commercial sell rate must be higher than official NBG rate for " + activeCurrency,
                commercialSell, greaterThan(officialValue));
        return this;
    }

    @Step("Validate status code is {expectedCode}")
    public ExchangeRatesSteps validateStatusCode(int expectedCode) {
        assertThat("Status code", rawResponse.statusCode(), equalTo(expectedCode));
        return this;
    }

    @Step("Validate status code is 4xx")
    public ExchangeRatesSteps validateStatusCodeIs4xx() {
        assertThat("Status code should be 4xx",
                rawResponse.statusCode(), anyOf(is(400), is(401), is(404), is(422)));
        return this;
    }

    @Step("Validate status code is an error (4xx or 5xx)")
    public ExchangeRatesSteps validateStatusCodeIsError() {
        assertThat("Status code should be an error",
                rawResponse.statusCode(), greaterThanOrEqualTo(400));
        return this;
    }
}