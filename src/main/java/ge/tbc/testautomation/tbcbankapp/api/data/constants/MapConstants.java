package ge.tbc.testautomation.tbcbankapp.api.data.constants;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapConstants {

    public static final String ATM_BRANCH_LIST_ENDPOINT = "/api/v1/atmsAndBranches/list";
    public static final String TBC_API_BASE_URI = "https://apigw.tbcbank.ge";

    public static final String LOCALE_KA = "ka-GE";
    public static final String LOCALE_EN = "en-US";

    public static final String DEFAULT_KEYWORD = "";
    public static final String SEARCH_KEYWORD_BATUMI = "ბათუმი";

    public static final double DEFAULT_LATITUDE = 0;
    public static final double DEFAULT_LONGITUDE = 0;

    public static final String TYPE_ATM = "ATM";
    public static final String TYPE_BRANCH = "Branch";
    public static final String TYPE_CDM = "Cdm";

    public static final Set<String> VALID_LOCATION_TYPES = Set.of(
            TYPE_ATM,
            TYPE_BRANCH,
            TYPE_CDM
    );

    public static final Map<String, Object> DEFAULT_MY_LOCATION = Map.of(
            "latitude", DEFAULT_LATITUDE,
            "longitude", DEFAULT_LONGITUDE
    );

    public static final Map<String, Object> DEFAULT_ATM_BRANCH_BODY = Map.of(
            "filter", List.of(),
            "locale", LOCALE_KA,
            "keyword", DEFAULT_KEYWORD,
            "myLocation", DEFAULT_MY_LOCATION
    );
}