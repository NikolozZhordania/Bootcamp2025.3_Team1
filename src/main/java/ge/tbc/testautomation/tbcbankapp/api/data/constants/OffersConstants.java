package ge.tbc.testautomation.tbcbankapp.api.data.constants;

public class OffersConstants {

    public static class URI {
        public static final String BASE = "https://apigw.tbcbank.ge";
    }

    public static class Paths {
        public static final String BASE = "/api/v1/marketing/entries";
    }

    public static class Endpoints {
        public static final String OFFERS = "/offer";
    }

    public static class Headers {
        public static final String ORIGIN        = "Origin";
        public static final String ORIGIN_VALUE  = "https://tbcbank.ge";
        public static final String REFERER       = "Referer";
        public static final String REFERER_VALUE = "https://tbcbank.ge/";
    }

    public static class Payload {
        public static final String LOCALE        = "ka-GE";
        public static final String SEGMENT_ALL   = "All";
        public static final int    PAGE_INDEX     = 0;
        public static final int    PAGE_SIZE      = 50;
        public static final String FILTER_TBC    = "ProductType:TBCCard";
    }

    public static class Timezone {
        public static final String TBILISI = "Asia/Tbilisi";
    }
}
