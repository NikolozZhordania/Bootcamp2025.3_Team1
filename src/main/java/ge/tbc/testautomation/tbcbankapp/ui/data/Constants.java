package ge.tbc.testautomation.tbcbankapp.ui.data.constants;

public class Constants {

    public static class URLs {
        public static final String APP                  = "https://tbcbank.ge/";
        public static final String LOCATIONS_PAGE_URL       = APP + "ka/atms&branches";
    }

    public static class LocationData {
        public static final String LOCATION_INPUT        = "ვაზისუბანი";
        public static final String EXPECTED_LOCATION_INPUT = "ვაზისუბნ";
        public static final String SELECTED_ATM_LOCATION = " ვაზისუბნის 1 მ/რ, თეოფანე დავითაიას ქ. #1ა ";
        public static final String STREET_NAME           = "თეოფანე დავითაიას ქუჩა";
        public static final String CITY_NAME             = "თბილისი";
    }

    public static class GoogleMaps {
        public static final String BASE_URI          = "https://maps.googleapis.com";
        public static final String GEOCODE_PATH      = "/maps/api/geocode/json";
        public static final String API_KEY_ENV       = "GOOGLE_MAPS_API_KEY";
        public static final String LANGUAGE          = "ka";
        public static final String STATUS_OK         = "OK";
        public static final String LATLNG_PARAM      = "latlng";
        public static final String KEY_PARAM         = "key";
        public static final String LANGUAGE_PARAM    = "language";
        public static final int    STATUS_CODE       = 200;
    }

    public static class GeocodeJson {
        public static final String ADDRESS_COMPONENTS = "address_components";
        public static final String TYPES              = "types";
        public static final String LONG_NAME          = "long_name";
        public static final String STATUS             = "status";
        public static final String ERROR_MESSAGE      = "error_message";
        public static final String RESULTS            = "results";
    }

    public static class MarkerAttributes {
        public static final String POSITION_ATTR   = "position";
        public static final String COORD_SEPARATOR = ",";
        public static final int    COORD_LENGTH    = 2;
    }

    public static class APIKeys {
        public static final String GOOGLE_MAPS_API_KEY = "AIzaSyDxk3oV23GUpv89ANJEiIypf62MFbW2XgM";
    }
}