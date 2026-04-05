package ge.tbc.testautomation.tbcbankapp.api.steps;

import ge.tbc.testautomation.tbcbankapp.api.client.TbcIdAPI;
import ge.tbc.testautomation.tbcbankapp.api.data.models.response.tbcid.JwksResponse;
import ge.tbc.testautomation.tbcbankapp.api.data.models.response.tbcid.OpenIdConfigurationResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static ge.tbc.testautomation.tbcbankapp.api.data.constants.TbcIdConstants.InvalidValues.*;
import static ge.tbc.testautomation.tbcbankapp.api.data.constants.TbcIdConstants.OpenIdConfig.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TbcIdSteps {

    private final TbcIdAPI api = new TbcIdAPI();
    private Response                    rawResponse;
    private OpenIdConfigurationResponse openIdConfig;
    private JwksResponse                jwks;

    @Step("Fetch OpenID configuration from .well-known/openid-configuration")
    public TbcIdSteps fetchOpenIdConfiguration() {
        this.rawResponse = api.getOpenIdConfiguration();
        this.openIdConfig = rawResponse
                .then()
                .statusCode(200)
                .extract()
                .as(OpenIdConfigurationResponse.class);
        return this;
    }

    @Step("Fetch JWKS keys from .well-known/keys")
    public TbcIdSteps fetchJwksKeys() {
        this.rawResponse = api.getJwksKeys();
        this.jwks = rawResponse
                .then()
                .statusCode(200)
                .extract()
                .as(JwksResponse.class);
        return this;
    }

    @Step("Request token with invalid authorization code expecting error")
    public TbcIdSteps requestTokenWithInvalidAuthCode() {
        this.rawResponse = api.getTokenWithAuthCode(
                INVALID_CLIENT_ID, "invalid_code", "https://tbcbank.ge/ka", "invalid_verifier");
        return this;
    }

    @Step("Request token with invalid CIBA auth_req_id expecting error")
    public TbcIdSteps requestTokenWithInvalidCibaReqId() {
        this.rawResponse = api.getTokenWithCiba(INVALID_CLIENT_ID, INVALID_AUTH_REQ_ID);
        return this;
    }

    @Step("Request token with missing grant_type expecting error")
    public TbcIdSteps requestTokenWithMissingGrantType() {
        this.rawResponse = api.getTokenWithInvalidParams(INVALID_CLIENT_ID, "");
        return this;
    }

    @Step("Initiate CIBA bc-authorization with missing required params expecting error")
    public TbcIdSteps initiateBcAuthorizationWithInvalidParams() {
        this.rawResponse = api.initiateBcAuthorization(INVALID_CLIENT_ID, "openid");
        return this;
    }

    @Step("Request userinfo with no token expecting error")
    public TbcIdSteps getUserInfoWithNoToken() {
        this.rawResponse = api.getUserInfoWithNoToken();
        return this;
    }

    @Step("Request userinfo with invalid token expecting error")
    public TbcIdSteps getUserInfoWithInvalidToken() {
        this.rawResponse = api.getUserInfo(INVALID_TOKEN);
        return this;
    }

    @Step("Validate issuer is not blank")
    public TbcIdSteps validateIssuerNotBlank() {
        assertThat("Issuer must not be blank",
                openIdConfig.getIssuer(), not(blankOrNullString()));
        return this;
    }

    @Step("Validate issuer matches TBC test environment")
    public TbcIdSteps validateIssuer() {
        assertThat("Issuer must match TBC test environment",
                openIdConfig.getIssuer(), containsString("tbcbank"));
        return this;
    }

    @Step("Validate authorization endpoint is not blank")
    public TbcIdSteps validateAuthorizationEndpointNotBlank() {
        assertThat("Authorization endpoint must not be blank",
                openIdConfig.getAuthorizationEndpoint(), not(blankOrNullString()));
        return this;
    }

    @Step("Validate JWKS URI is not blank")
    public TbcIdSteps validateJwksUriNotBlank() {
        assertThat("JWKS URI must not be blank",
                openIdConfig.getJwksUri(), not(blankOrNullString()));
        return this;
    }

    @Step("Validate backchannel authentication endpoint is not blank")
    public TbcIdSteps validateBackchannelEndpointNotBlank() {
        assertThat("Backchannel authentication endpoint must not be blank",
                openIdConfig.getBackchannelAuthenticationEndpoint(), not(blankOrNullString()));
        return this;
    }

    @Step("Validate mtls_endpoint_aliases is not null")
    public TbcIdSteps validateMtlsAliasesNotNull() {
        assertThat("mtls_endpoint_aliases must not be null",
                openIdConfig.getMtlsEndpointAliases(), notNullValue());
        return this;
    }

    @Step("Validate token endpoint in mtls_endpoint_aliases is not blank")
    public TbcIdSteps validateTokenEndpointNotBlank() {
        assertThat("token_endpoint inside mtls_endpoint_aliases must not be blank",
                openIdConfig.getMtlsEndpointAliases().getTokenEndpoint(), not(blankOrNullString()));
        return this;
    }

    @Step("Validate userinfo endpoint in mtls_endpoint_aliases is not blank")
    public TbcIdSteps validateUserinfoEndpointNotBlank() {
        assertThat("userinfo_endpoint inside mtls_endpoint_aliases must not be blank",
                openIdConfig.getMtlsEndpointAliases().getUserinfoEndpoint(), not(blankOrNullString()));
        return this;
    }

    @Step("Validate registration endpoint in mtls_endpoint_aliases is not blank")
    public TbcIdSteps validateRegistrationEndpointNotBlank() {
        assertThat("registration_endpoint inside mtls_endpoint_aliases must not be blank",
                openIdConfig.getMtlsEndpointAliases().getRegistrationEndpoint(), not(blankOrNullString()));
        return this;
    }

    @Step("Validate supported scopes list is not empty")
    public TbcIdSteps validateScopesNotEmpty() {
        assertThat("Scopes supported must not be empty",
                openIdConfig.getScopesSupported(), is(not(empty())));
        return this;
    }

    @Step("Validate supported scopes contain openid")
    public TbcIdSteps validateScopesContainOpenId() {
        assertThat("Scopes must contain 'openid'",
                openIdConfig.getScopesSupported(), hasItem(SCOPE_OPENID));
        return this;
    }

    @Step("Validate supported scopes contain profile")
    public TbcIdSteps validateScopesContainProfile() {
        assertThat("Scopes must contain 'profile'",
                openIdConfig.getScopesSupported(), hasItem("profile"));
        return this;
    }

    @Step("Validate supported grant types contain authorization_code")
    public TbcIdSteps validateGrantTypesContainAuthCode() {
        assertThat("Grant types must contain 'authorization_code'",
                openIdConfig.getGrantTypesSupported(), hasItem(GRANT_TYPE_AUTH_CODE));
        return this;
    }

    @Step("Validate supported grant types contain ciba")
    public TbcIdSteps validateGrantTypesContainCiba() {
        assertThat("Grant types must contain 'ciba'",
                openIdConfig.getGrantTypesSupported(), hasItem(GRANT_TYPE_CIBA));
        return this;
    }

    @Step("Validate supported response types contain code")
    public TbcIdSteps validateResponseTypesContainCode() {
        assertThat("Response types must contain 'code'",
                openIdConfig.getResponseTypesSupported(), hasItem(RESPONSE_TYPE_CODE));
        return this;
    }

    @Step("Validate id_token signing algorithms list is not empty")
    public TbcIdSteps validateSigningAlgorithmsNotEmpty() {
        assertThat("ID token signing algorithms must not be empty",
                openIdConfig.getIdTokenSigningAlgValuesSupported(), is(not(empty())));
        return this;
    }

    @Step("Validate id_token signing algorithms contain RS256")
    public TbcIdSteps validateSigningAlgorithmsContainRS256() {
        assertThat("ID token signing algorithms must contain RS256",
                openIdConfig.getIdTokenSigningAlgValuesSupported(), hasItem("RS256"));
        return this;
    }

    @Step("Validate userinfo signing algorithms contain RS256")
    public TbcIdSteps validateUserinfoSigningAlgorithmsContainRS256() {
        assertThat("Userinfo signing algorithms must contain RS256",
                openIdConfig.getUserinfoSigningAlgValuesSupported(), hasItem("RS256"));
        return this;
    }

    @Step("Validate token endpoint auth methods contain tls_client_auth")
    public TbcIdSteps validateTokenAuthMethodsContainTls() {
        assertThat("Token endpoint auth methods must contain 'tls_client_auth'",
                openIdConfig.getTokenEndpointAuthMethodsSupported(), hasItem(TOKEN_ENDPOINT_AUTH_METHOD));
        return this;
    }

    @Step("Validate verified_claims_supported is true")
    public TbcIdSteps validateVerifiedClaimsSupported() {
        assertThat("verified_claims_supported must be true",
                openIdConfig.getVerifiedClaimsSupported(), is(true));
        return this;
    }

    @Step("Validate claims_parameter_supported is true")
    public TbcIdSteps validateClaimsParameterSupported() {
        assertThat("claims_parameter_supported must be true",
                openIdConfig.getClaimsParameterSupported(), is(true));
        return this;
    }

    @Step("Validate claims_in_verified_claims_supported contains personal_id_number")
    public TbcIdSteps validateVerifiedClaimsContainPersonalId() {
        assertThat("Verified claims must contain 'personal_id_number'",
                openIdConfig.getClaimsInVerifiedClaimsSupported(), hasItem("personal_id_number"));
        return this;
    }

    @Step("Validate claims_in_verified_claims_supported contains given_name and family_name")
    public TbcIdSteps validateVerifiedClaimsContainName() {
        assertThat("Verified claims must contain 'given_name'",
                openIdConfig.getClaimsInVerifiedClaimsSupported(), hasItem("given_name"));
        assertThat("Verified claims must contain 'family_name'",
                openIdConfig.getClaimsInVerifiedClaimsSupported(), hasItem("family_name"));
        return this;
    }

    @Step("Validate id_documents_supported contains idcard and passport")
    public TbcIdSteps validateIdDocumentsSupported() {
        assertThat("ID documents must contain 'idcard'",
                openIdConfig.getIdDocumentsSupported(), hasItem("idcard"));
        assertThat("ID documents must contain 'passport'",
                openIdConfig.getIdDocumentsSupported(), hasItem("passport"));
        return this;
    }

    @Step("Validate ui_locales_supported contains ka (Georgian)")
    public TbcIdSteps validateUiLocalesContainKa() {
        assertThat("UI locales must contain Georgian 'ka'",
                openIdConfig.getUiLocalesSupported(), hasItem("ka"));
        return this;
    }

    @Step("Validate backchannel token delivery modes contain poll")
    public TbcIdSteps validateBackchannelDeliveryModesPoll() {
        assertThat("Backchannel token delivery modes must contain 'poll'",
                openIdConfig.getBackchannelTokenDeliveryModesSupported(), hasItem("poll"));
        return this;
    }

    @Step("Validate trust_frameworks_supported contains ge_aml")
    public TbcIdSteps validateTrustFrameworksContainGeAml() {
        assertThat("Trust frameworks must contain 'ge_aml'",
                openIdConfig.getTrustFrameworksSupported(), hasItem("ge_aml"));
        return this;
    }

    @Step("Validate JWKS keys list is not empty")
    public TbcIdSteps validateJwksKeysNotEmpty() {
        assertThat("JWKS keys list must not be empty",
                jwks.getKeys(), is(not(empty())));
        return this;
    }

    @Step("Validate all JWKS keys have kty, kid and alg fields")
    public TbcIdSteps validateJwksKeyFields() {
        jwks.getKeys().forEach(key -> {
            assertThat("Key type (kty) must not be blank", key.getKty(), not(blankOrNullString()));
            assertThat("Key ID (kid) must not be blank",   key.getKid(), not(blankOrNullString()));
            assertThat("Algorithm (alg) must not be blank", key.getAlg(), not(blankOrNullString()));
        });
        return this;
    }

    @Step("Validate all JWKS keys are RSA type")
    public TbcIdSteps validateJwksKeysAreRsa() {
        jwks.getKeys().forEach(key ->
                assertThat("Key type must be RSA",
                        key.getKty(), equalToIgnoringCase("RSA")));
        return this;
    }

    @Step("Validate all JWKS keys have modulus (n) and exponent (e)")
    public TbcIdSteps validateJwksRsaComponents() {
        jwks.getKeys().forEach(key -> {
            assertThat("RSA modulus (n) must not be blank",  key.getN(), not(blankOrNullString()));
            assertThat("RSA exponent (e) must not be blank", key.getE(), not(blankOrNullString()));
        });
        return this;
    }

    @Step("Validate status code is {expectedCode}")
    public TbcIdSteps validateStatusCode(int expectedCode) {
        assertThat("Status code",
                rawResponse.statusCode(), equalTo(expectedCode));
        return this;
    }

    @Step("Validate status code is 4xx")
    public TbcIdSteps validateStatusCodeIs4xx() {
        assertThat("Status code should be 4xx",
                rawResponse.statusCode(), anyOf(is(400), is(401), is(403), is(404), is(422)));
        return this;
    }

    @Step("Validate status code is an error (4xx or 5xx)")
    public TbcIdSteps validateStatusCodeIsError() {
        assertThat("Status code should be an error",
                rawResponse.statusCode(), greaterThanOrEqualTo(400));
        return this;
    }
}