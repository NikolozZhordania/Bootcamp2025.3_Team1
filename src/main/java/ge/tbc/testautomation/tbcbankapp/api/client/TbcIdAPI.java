package ge.tbc.testautomation.tbcbankapp.api.client;

import ge.tbc.testautomation.tbcbankapp.api.data.constants.TbcIdConstants.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class TbcIdAPI extends BaseAPIClient {

    private RequestSpecification base() {
        return given()
                .baseUri(URI.BASE)
                .accept(ContentType.JSON)
                .log().uri();
    }

    public Response getOpenIdConfiguration() {
        return base()
                .when()
                .get(Endpoints.OPENID_CONFIGURATION);
    }

    public Response getJwksKeys() {
        return base()
                .when()
                .get(Endpoints.JWKS_KEYS);
    }

    public Response getTokenWithAuthCode(String clientId, String authCode, String redirectUri, String codeVerifier) {
        return base()
                .contentType("application/x-www-form-urlencoded")
                .formParam("client_id",    clientId)
                .formParam("grant_type",   "authorization_code")
                .formParam("code",         authCode)
                .formParam("redirect_uri", redirectUri)
                .formParam("code_verifier", codeVerifier)
                .when()
                .post(Endpoints.TOKEN);
    }

    public Response getTokenWithInvalidParams(String clientId, String grantType) {
        return base()
                .contentType("application/x-www-form-urlencoded")
                .formParam("client_id",  clientId)
                .formParam("grant_type", grantType)
                .when()
                .post(Endpoints.TOKEN);
    }

    public Response getTokenWithCiba(String clientId, String authReqId) {
        return base()
                .contentType("application/x-www-form-urlencoded")
                .formParam("client_id",    clientId)
                .formParam("grant_type",   "urn:openid:params:grant-type:ciba")
                .formParam("auth_req_id",  authReqId)
                .when()
                .post(Endpoints.TOKEN);
    }

    public Response initiateBcAuthorization(String clientId, String scope) {
        return base()
                .contentType("application/x-www-form-urlencoded")
                .formParam("client_id", clientId)
                .formParam("scope",     scope)
                .when()
                .post(Endpoints.BC_AUTHORIZATION);
    }

    public Response getUserInfo(String accessToken) {
        return base()
                .header(Headers.APIKEY, Headers.APIKEY_VALUE)
                .auth().oauth2(accessToken)
                .when()
                .get(Endpoints.USERINFO);
    }

    public Response getUserInfoWithNoToken() {
        return base()
                .header(Headers.APIKEY, Headers.APIKEY_VALUE)
                .when()
                .get(Endpoints.USERINFO);
    }
}