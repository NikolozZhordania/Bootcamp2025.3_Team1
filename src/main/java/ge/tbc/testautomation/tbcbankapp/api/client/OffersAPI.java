package ge.tbc.testautomation.tbcbankapp.api.client;

import ge.tbc.testautomation.tbcbankapp.api.data.models.request.offers.OffersRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static ge.tbc.testautomation.tbcbankapp.api.data.constants.OffersConstants.*;
import static io.restassured.RestAssured.given;

public class OffersAPI extends BaseAPIClient {

    private RequestSpecification base() {
        return given()
                .baseUri(URI.BASE)
                .basePath(Paths.BASE)
                .header(Headers.ORIGIN,  Headers.ORIGIN_VALUE)
                .header(Headers.REFERER, Headers.REFERER_VALUE)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().uri();
    }

    public Response getOffers(OffersRequest request) {
        return base()
                .body(request)
                .when()
                .post(Endpoints.OFFERS);
    }
}
