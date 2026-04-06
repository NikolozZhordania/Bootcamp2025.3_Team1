package ge.tbc.testautomation.tbcbankapp.api.client;

import ge.tbc.testautomation.tbcbankapp.api.data.constants.MapConstants;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TbcAtmBranchApi extends BaseTbcApi {

    public Response getLocations(Map<String, Object> body)
    {
        return given()
                .spec(REQ_SPEC)
                .body(body)
                .when()
                .post(MapConstants.ATM_BRANCH_LIST_ENDPOINT);
    }

    public Map<String, Object> buildBody(String keyword)
    {
        return Map.of(
                "filter", List.of(),
                "locale", MapConstants.LOCALE_KA,
                "keyword", keyword,
                "myLocation", MapConstants.DEFAULT_MY_LOCATION
        );
    }
}