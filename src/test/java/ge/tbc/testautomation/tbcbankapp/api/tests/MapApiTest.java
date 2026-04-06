package ge.tbc.testautomation.tbcbankapp.api.tests;

import ge.tbc.testautomation.tbcbankapp.api.steps.TbcAtmBranchSteps;
import ge.tbc.testautomation.tbcbankapp.api.data.constants.MapConstants;
import org.testng.annotations.Test;

public class MapApiTest {

    private final TbcAtmBranchSteps steps = new TbcAtmBranchSteps();

    @Test(description = "Map API: Happy path 200 + response not empty")
    public void mapApiHappy()
    {

        steps.getDefaultLocations();

    }

    @Test(description = "Map API: Validate required fields in response")
    public void mapApiRequiredFields()
    {

        var resp = steps.getDefaultLocations();
        steps.assertRequiredFields(resp);

    }

    @Test(description = "Map API: Validate response types are supported")
    public void mapApiTypesValidation()
    {

        var resp = steps.getDefaultLocations();
        steps.assertValidTypes(resp);

    }

    @Test(description = "Map API: Verify at least one branch exists in response")
    public void mapApiBranchExists()
    {

        var resp = steps.getDefaultLocations();
        steps.assertAtLeastOneBranchExists(resp);

    }

    @Test(description = "Map API: Search by Batumi keyword")
    public void mapApiKeywordSearch()
    {

        var resp = steps.searchByKeyword(MapConstants.SEARCH_KEYWORD_BATUMI);
        steps.assertAnyResultContainsKeyword(resp, MapConstants.SEARCH_KEYWORD_BATUMI);

    }
}