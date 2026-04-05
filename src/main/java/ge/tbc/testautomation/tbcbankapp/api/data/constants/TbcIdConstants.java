package ge.tbc.testautomation.tbcbankapp.api.data.constants;

public class TbcIdConstants {

    public static class URI {
        public static final String BASE = "https://test-api.tbcbank.ge";
    }

    public static class Endpoints {
        public static final String OPENID_CONFIGURATION = "/.well-known/openid-configuration";
        public static final String JWKS_KEYS            = "/.well-known/keys";
        public static final String TOKEN                = "/openbanking/oauth/token";
        public static final String BC_AUTHORIZATION     = "/psd2/openbanking/oauth/bc-authorization";
        public static final String AUTHORIZE            = "/psd2/openbanking/oauth/authorize";
        public static final String USERINFO             = "/userinfo";
    }

    public static class Headers {
        public static final String APIKEY       = "apikey";
        public static final String APIKEY_VALUE = "VTzlpOA4fqm5Yb5oS8QIffLdyRAeRoMT";
    }

    public static class OpenIdConfig {
        public static final String GRANT_TYPE_CIBA            = "ciba";
        public static final String GRANT_TYPE_AUTH_CODE       = "authorization_code";
        public static final String SCOPE_OPENID               = "openid";
        public static final String RESPONSE_TYPE_CODE         = "code";
        public static final String TOKEN_ENDPOINT_AUTH_METHOD = "tls_client_auth";
    }

    public static class InvalidValues {
        public static final String INVALID_CLIENT_ID  = "invalid_client_id";
        public static final String INVALID_TOKEN      = "invalid.bearer.token";
        public static final String INVALID_AUTH_REQ_ID = "invalid-auth-req-id-000";
    }
}