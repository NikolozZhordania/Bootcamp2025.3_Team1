package ge.tbc.testautomation.tbcbankapp.api.data.constants;

public class ExchangeRatesConstants {

    public static class URI {
        public static final String BASE = "https://api.tbcbank.ge";
    }

    public static class Paths {
        public static final String BASE = "/v1";
    }

    public static class Endpoints {
        public static final String COMMERCIAL         = "/exchange-rates/commercial";
        public static final String COMMERCIAL_CONVERT = "/exchange-rates/commercial/convert";
        public static final String NBG                = "/exchange-rates/nbg";
        public static final String NBG_CONVERT        = "/exchange-rates/nbg/convert";
        public static final String EXTENDED           = "/extendedexchangerates";
    }

    public static class Headers {
        public static final String APIKEY       = "apikey";
        public static final String APIKEY_VALUE = "I3Rz8QXBVPofKtucsenkW7g2n7aDwqBg";
    }

    public static class QueryParams {
        public static final String CURRENCY = "currency";
        public static final String DATE     = "date";
        public static final String AMOUNT   = "amount";
        public static final String FROM     = "from";
        public static final String TO       = "to";
    }

    public static class Currencies {
        public static final String USD = "USD";
        public static final String EUR = "EUR";
        public static final String GBP = "GBP";
        public static final String AZN = "AZN";
        public static final String GEL = "GEL";
    }

    public static class ConversionValues {
        public static final double SAMPLE_AMOUNT = 100;
        public static final String FROM_USD        = "usd";
        public static final String TO_GEL          = "GEL";
        public static final String INVALID_CURRENCY = "INVALID";
    }
}