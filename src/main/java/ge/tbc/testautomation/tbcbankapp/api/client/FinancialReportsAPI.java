package ge.tbc.testautomation.tbcbankapp.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static ge.tbc.testautomation.tbcbankapp.api.data.constants.FinancialReportsConstants.*;
import static io.restassured.RestAssured.given;

public class FinancialReportsAPI extends BaseAPIClient {

    private RequestSpecification base() {
        return given()
                .baseUri(URI.BASE)
                .basePath(Paths.BASE)
                .header(Headers.ORIGIN,  Headers.ORIGIN_VALUE)
                .header(Headers.REFERER, Headers.REFERER_VALUE)
                .accept(ContentType.JSON)
                .log().uri();
    }

    public Response getFinancialHighlightsPage() {
        return base()
                .queryParam(Params.LOCALE, Params.LOCALE_VALUE)
                .when()
                .get(Endpoints.FINANCIAL_HIGHLIGHTS_PAGE);
    }

    public Response headFile(String fileUrl) {
        return given()
                .header(Headers.ORIGIN,  Headers.ORIGIN_VALUE)
                .header(Headers.REFERER, Headers.REFERER_VALUE)
                .log().uri()
                .when()
                .head(fileUrl);
    }
}
