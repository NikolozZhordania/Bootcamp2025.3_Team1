package ge.tbc.testautomation.tbcbankapp.api.steps;

import ge.tbc.testautomation.tbcbankapp.api.client.TbcAtmBranchApi;
import ge.tbc.testautomation.tbcbankapp.api.data.constants.MapConstants;
import ge.tbc.testautomation.tbcbankapp.api.data.models.response.map.AtmBranchResponse;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TbcAtmBranchSteps {

    private final TbcAtmBranchApi api = new TbcAtmBranchApi();

    public List<AtmBranchResponse> getDefaultLocations()
    {
        List<AtmBranchResponse> resp = api.getLocations(MapConstants.DEFAULT_ATM_BRANCH_BODY)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("", AtmBranchResponse.class);

        assertThat(resp, notNullValue());
        assertThat(resp, not(empty()));

        return resp;
    }

    public List<AtmBranchResponse> searchByKeyword(String keyword)
    {
        List<AtmBranchResponse> resp = api.getLocations(api.buildBody(keyword))
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("", AtmBranchResponse.class);

        assertThat(resp, notNullValue());

        return resp;
    }

    public void assertRequiredFields(List<AtmBranchResponse> resp)
    {
        for (AtmBranchResponse item : resp) {
            assertThat(item.getId(), notNullValue());
            assertThat(item.getAddress(), not(isEmptyOrNullString()));
            assertThat(item.getLatitude(), notNullValue());
            assertThat(item.getLongitude(), notNullValue());
            assertThat(item.getType(), not(isEmptyOrNullString()));
            assertThat(item.getRegionName(), not(isEmptyOrNullString()));
        }
    }

    public void assertValidTypes(List<AtmBranchResponse> resp)
    {
        for (AtmBranchResponse item : resp) {
            assertThat(item.getType(), is(in(MapConstants.VALID_LOCATION_TYPES)));
        }
    }

    public void assertAnyResultContainsKeyword(List<AtmBranchResponse> resp, String keyword)
    {
        String loweredKeyword = keyword.toLowerCase();

        boolean found = resp.stream().anyMatch(item ->
                (item.getAddress() != null && item.getAddress().toLowerCase().contains(loweredKeyword)) ||
                        (item.getRegionName() != null && item.getRegionName().toLowerCase().contains(loweredKeyword)) ||
                        (item.getLocation() != null && item.getLocation().toLowerCase().contains(loweredKeyword)) ||
                        (item.getName() != null && item.getName().toLowerCase().contains(loweredKeyword))
        );

        assertThat(found, is(true));
    }

    public void assertAtLeastOneBranchExists(List<AtmBranchResponse> resp)
    {
        boolean hasBranch = resp.stream()
                .anyMatch(item -> MapConstants.TYPE_BRANCH.equalsIgnoreCase(item.getType()));

        assertThat(hasBranch, is(true));
    }
}
