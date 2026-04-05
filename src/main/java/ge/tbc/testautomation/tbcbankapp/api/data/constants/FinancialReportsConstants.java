package ge.tbc.testautomation.tbcbankapp.api.data.constants;

public class FinancialReportsConstants {

    public static class URI {
        public static final String BASE        = "https://apigw.tbcbank.ge";
        public static final String ASSETS_BASE = "https://assets.eu.ctfassets.net";
        public static final String DOWNLOADS_BASE = "https://downloads.eu.ctfassets.net";
    }

    public static class Paths {
        public static final String BASE = "/api/v1/sites/pages";
    }

    public static class Endpoints {
        public static final String FINANCIAL_HIGHLIGHTS_PAGE = "/6UXIojyF4L5tjlvBCe94M0";
    }

    public static class Headers {
        public static final String ORIGIN        = "Origin";
        public static final String ORIGIN_VALUE  = "https://tbcbank.ge";
        public static final String REFERER       = "Referer";
        public static final String REFERER_VALUE = "https://tbcbank.ge/";
    }

    public static class Params {
        public static final String LOCALE       = "locale";
        public static final String LOCALE_VALUE = "ka-GE";
    }

    public static class FileSize {
        public static final long MIN_BYTES = 1000L;
    }

    public static class Years {
        public static final int MIN = 2018;
        public static final int MAX = 2026;
    }
}
