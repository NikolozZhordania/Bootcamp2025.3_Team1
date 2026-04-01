package ge.tbc.testautomation.tbcbankapp.api.client;

import ge.tbc.testautomation.tbcbankapp.api.data.constants.ExchangeRatesConstants.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ExchangeRatesAPI extends BaseAPIClient {

    private RequestSpecification base() {
        return given()
                .baseUri(URI.BASE)
                .basePath(Paths.BASE)
                .header(Headers.APIKEY, Headers.APIKEY_VALUE)
                .accept(ContentType.JSON)
                .log().uri();
    }

    public Response getCommercialRates() {
        return base()
                .when()
                .get(Endpoints.COMMERCIAL);
    }

    public Response getCommercialRates(String currencies) {
        return base()
                .queryParam(QueryParams.CURRENCY, currencies)
                .when()
                .get(Endpoints.COMMERCIAL);
    }

    public Response convertCommercial(double amount, String from, String to) {
        return base()
                .queryParam(QueryParams.AMOUNT, amount)
                .queryParam(QueryParams.FROM, from)
                .queryParam(QueryParams.TO, to)
                .when()
                .get(Endpoints.COMMERCIAL_CONVERT);
    }

    public Response getOfficialRates() {
        return base()
                .when()
                .get(Endpoints.NBG);
    }

    public Response getOfficialRates(String currencies, String date) {
        return base()
                .queryParam(QueryParams.CURRENCY, currencies)
                .queryParam(QueryParams.DATE, date)
                .when()
                .get(Endpoints.NBG);
    }

    public Response convertOfficial(double amount, String from, String to) {
        return base()
                .queryParam(QueryParams.AMOUNT, amount)
                .queryParam(QueryParams.FROM, from)
                .queryParam(QueryParams.TO, to)
                .when()
                .get(Endpoints.NBG_CONVERT);
    }

    public Response getExtendedRates() {
        return base()
                .auth().preemptive().basic(Headers.APIKEY_VALUE, "")
                .when()
                .post(Endpoints.EXTENDED);
    }
}