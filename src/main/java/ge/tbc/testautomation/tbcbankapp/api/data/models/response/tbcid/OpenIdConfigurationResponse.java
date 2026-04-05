package ge.tbc.testautomation.tbcbankapp.api.data.models.response.tbcid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenIdConfigurationResponse {

    @JsonProperty("issuer")
    private String issuer;

    @JsonProperty("authorization_endpoint")
    private String authorizationEndpoint;

    @JsonProperty("jwks_uri")
    private String jwksUri;

    @JsonProperty("backchannel_authentication_endpoint")
    private String backchannelAuthenticationEndpoint;

    @JsonProperty("scopes_supported")
    private List<String> scopesSupported;

    @JsonProperty("response_types_supported")
    private List<String> responseTypesSupported;

    @JsonProperty("grant_types_supported")
    private List<String> grantTypesSupported;

    @JsonProperty("token_endpoint_auth_methods_supported")
    private List<String> tokenEndpointAuthMethodsSupported;

    @JsonProperty("subject_types_supported")
    private List<String> subjectTypesSupported;

    @JsonProperty("id_token_signing_alg_values_supported")
    private List<String> idTokenSigningAlgValuesSupported;

    @JsonProperty("userinfo_signing_alg_values_supported")
    private List<String> userinfoSigningAlgValuesSupported;

    @JsonProperty("backchannel_token_delivery_modes_supported")
    private List<String> backchannelTokenDeliveryModesSupported;

    @JsonProperty("backchannel_user_code_parameter_supported")
    private Boolean backchannelUserCodeParameterSupported;

    @JsonProperty("claims_parameter_supported")
    private Boolean claimsParameterSupported;

    @JsonProperty("verified_claims_supported")
    private Boolean verifiedClaimsSupported;

    @JsonProperty("ui_locales_supported")
    private List<String> uiLocalesSupported;

    @JsonProperty("claims_in_verified_claims_supported")
    private List<String> claimsInVerifiedClaimsSupported;

    @JsonProperty("id_documents_supported")
    private List<String> idDocumentsSupported;

    @JsonProperty("trust_frameworks_supported")
    private List<String> trustFrameworksSupported;

    @JsonProperty("acr_values_supported")
    private List<String> acrValuesSupported;

    @JsonProperty("mtls_endpoint_aliases")
    private MtlsEndpointAliases mtlsEndpointAliases;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MtlsEndpointAliases {

        @JsonProperty("token_endpoint")
        private String tokenEndpoint;

        @JsonProperty("userinfo_endpoint")
        private String userinfoEndpoint;

        @JsonProperty("registration_endpoint")
        private String registrationEndpoint;
    }
}