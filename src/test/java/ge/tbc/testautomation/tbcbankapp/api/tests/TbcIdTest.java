package ge.tbc.testautomation.tbcbankapp.api.tests;

import ge.tbc.testautomation.tbcbankapp.api.steps.TbcIdSteps;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Feature("TBC ID API")
public class TbcIdTest {

    @Story(".well-known")
    @Description("OpenID configuration returns 200 with all core endpoint fields present")
    @Test(description = "GET /.well-known/openid-configuration returns valid core structure")
    public void openIdConfigurationCoreStructureTest() {
        new TbcIdSteps()
                .fetchOpenIdConfiguration()
                .validateIssuerNotBlank()
                .validateAuthorizationEndpointNotBlank()
                .validateJwksUriNotBlank()
                .validateBackchannelEndpointNotBlank();
    }

    @Story(".well-known")
    @Description("token_endpoint, userinfo_endpoint and registration_endpoint are inside mtls_endpoint_aliases")
    @Test(description = "GET /.well-known/openid-configuration mtls_endpoint_aliases contains all endpoints")
    public void openIdConfigurationMtlsAliasesTest() {
        new TbcIdSteps()
                .fetchOpenIdConfiguration()
                .validateMtlsAliasesNotNull()
                .validateTokenEndpointNotBlank()
                .validateUserinfoEndpointNotBlank()
                .validateRegistrationEndpointNotBlank();
    }

    @Story(".well-known")
    @Description("Issuer points to TBC test environment")
    @Test(description = "GET /.well-known/openid-configuration issuer contains tbcbank")
    public void openIdConfigurationIssuerTest() {
        new TbcIdSteps()
                .fetchOpenIdConfiguration()
                .validateIssuer();
    }

    @Story(".well-known")
    @Description("Supported scopes contain openid and profile")
    @Test(description = "GET /.well-known/openid-configuration scopes_supported contains openid and profile")
    public void openIdConfigurationScopesTest() {
        new TbcIdSteps()
                .fetchOpenIdConfiguration()
                .validateScopesNotEmpty()
                .validateScopesContainOpenId()
                .validateScopesContainProfile();
    }

    @Story(".well-known")
    @Description("Supported grant types contain authorization_code and ciba")
    @Test(description = "GET /.well-known/openid-configuration grant_types_supported contains auth_code and ciba")
    public void openIdConfigurationGrantTypesTest() {
        new TbcIdSteps()
                .fetchOpenIdConfiguration()
                .validateGrantTypesContainAuthCode()
                .validateGrantTypesContainCiba();
    }

    @Story(".well-known")
    @Description("Supported response types contain code")
    @Test(description = "GET /.well-known/openid-configuration response_types_supported contains code")
    public void openIdConfigurationResponseTypesTest() {
        new TbcIdSteps()
                .fetchOpenIdConfiguration()
                .validateResponseTypesContainCode();
    }

    @Story(".well-known")
    @Description("id_token and userinfo signing algorithms contain RS256")
    @Test(description = "GET /.well-known/openid-configuration signing algorithms contain RS256")
    public void openIdConfigurationSigningAlgorithmsTest() {
        new TbcIdSteps()
                .fetchOpenIdConfiguration()
                .validateSigningAlgorithmsNotEmpty()
                .validateSigningAlgorithmsContainRS256()
                .validateUserinfoSigningAlgorithmsContainRS256();
    }

    @Story(".well-known")
    @Description("Token endpoint auth methods contain tls_client_auth")
    @Test(description = "GET /.well-known/openid-configuration token auth methods contain tls_client_auth")
    public void openIdConfigurationTokenAuthMethodsTest() {
        new TbcIdSteps()
                .fetchOpenIdConfiguration()
                .validateTokenAuthMethodsContainTls();
    }

    @Story(".well-known")
    @Description("Backchannel token delivery modes contain poll")
    @Test(description = "GET /.well-known/openid-configuration backchannel delivery modes contain poll")
    public void openIdConfigurationBackchannelDeliveryModesTest() {
        new TbcIdSteps()
                .fetchOpenIdConfiguration()
                .validateBackchannelDeliveryModesPoll();
    }

    @Story(".well-known")
    @Description("verified_claims_supported and claims_parameter_supported are true")
    @Test(description = "GET /.well-known/openid-configuration verified and claims parameter are supported")
    public void openIdConfigurationClaimsSupportedTest() {
        new TbcIdSteps()
                .fetchOpenIdConfiguration()
                .validateVerifiedClaimsSupported()
                .validateClaimsParameterSupported();
    }

    @Story(".well-known")
    @Description("Verified claims contain personal_id_number, given_name and family_name")
    @Test(description = "GET /.well-known/openid-configuration verified claims contain identity fields")
    public void openIdConfigurationVerifiedClaimsFieldsTest() {
        new TbcIdSteps()
                .fetchOpenIdConfiguration()
                .validateVerifiedClaimsContainPersonalId()
                .validateVerifiedClaimsContainName();
    }

    @Story(".well-known")
    @Description("ID documents supported contain idcard and passport")
    @Test(description = "GET /.well-known/openid-configuration id_documents_supported contains idcard and passport")
    public void openIdConfigurationIdDocumentsTest() {
        new TbcIdSteps()
                .fetchOpenIdConfiguration()
                .validateIdDocumentsSupported();
    }

    @Story(".well-known")
    @Description("UI locales supported contain Georgian (ka)")
    @Test(description = "GET /.well-known/openid-configuration ui_locales_supported contains ka")
    public void openIdConfigurationUiLocalesTest() {
        new TbcIdSteps()
                .fetchOpenIdConfiguration()
                .validateUiLocalesContainKa();
    }

    @Story(".well-known")
    @Description("Trust frameworks supported contain ge_aml (Georgian AML framework)")
    @Test(description = "GET /.well-known/openid-configuration trust_frameworks_supported contains ge_aml")
    public void openIdConfigurationTrustFrameworksTest() {
        new TbcIdSteps()
                .fetchOpenIdConfiguration()
                .validateTrustFrameworksContainGeAml();
    }

    @Story(".well-known")
    @Description("JWKS endpoint returns non-empty list of RSA keys")
    @Test(description = "GET /.well-known/keys returns 200 with non-empty RSA keys")
    public void jwksKeysStructureTest() {
        new TbcIdSteps()
                .fetchJwksKeys()
                .validateJwksKeysNotEmpty()
                .validateJwksKeysAreRsa();
    }

    @Story(".well-known")
    @Description("All JWKS keys have required fields: kty, kid, alg")
    @Test(description = "GET /.well-known/keys all keys have kty, kid and alg")
    public void jwksKeyFieldsTest() {
        new TbcIdSteps()
                .fetchJwksKeys()
                .validateJwksKeyFields();
    }

    @Story(".well-known")
    @Description("All JWKS RSA keys have modulus (n) and exponent (e) components")
    @Test(description = "GET /.well-known/keys all RSA keys have n and e fields")
    public void jwksRsaComponentsTest() {
        new TbcIdSteps()
                .fetchJwksKeys()
                .validateJwksRsaComponents();
    }

    @Story("OAuth")
    @Description("Token request with invalid authorization code returns error")
    @Test(description = "POST /oauth/token with invalid auth code returns 4xx")
    public void tokenInvalidAuthCodeTest() {
        new TbcIdSteps()
                .requestTokenWithInvalidAuthCode()
                .validateStatusCodeIs4xx();
    }

    @Story("OAuth")
    @Description("Token request with invalid CIBA auth_req_id returns error")
    @Test(description = "POST /oauth/token with invalid CIBA auth_req_id returns 4xx")
    public void tokenInvalidCibaReqIdTest() {
        new TbcIdSteps()
                .requestTokenWithInvalidCibaReqId()
                .validateStatusCodeIs4xx();
    }

    @Story("OAuth")
    @Description("Token request with missing grant_type returns error")
    @Test(description = "POST /oauth/token with missing grant_type returns 4xx")
    public void tokenMissingGrantTypeTest() {
        new TbcIdSteps()
                .requestTokenWithMissingGrantType()
                .validateStatusCodeIs4xx();
    }

    @Story("CIBA")
    @Description("CIBA bc-authorization with invalid client_id returns error")
    @Test(description = "POST /bc-authorization with invalid client_id returns error")
    public void bcAuthorizationInvalidClientTest() {
        new TbcIdSteps()
                .initiateBcAuthorizationWithInvalidParams()
                .validateStatusCodeIsError();
    }

    @Story("Userinfo")
    @Description("Userinfo request with no token returns 4xx")
    @Test(description = "GET /userinfo with no token returns 4xx")
    public void userInfoNoTokenTest() {
        new TbcIdSteps()
                .getUserInfoWithNoToken()
                .validateStatusCodeIs4xx();
    }

    @Story("Userinfo")
    @Description("Userinfo request with invalid token returns 4xx")
    @Test(description = "GET /userinfo with invalid token returns 4xx")
    public void userInfoInvalidTokenTest() {
        new TbcIdSteps()
                .getUserInfoWithInvalidToken()
                .validateStatusCodeIs4xx();
    }
}