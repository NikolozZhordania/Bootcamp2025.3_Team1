package ge.tbc.testautomation.tbcbankapp.api.client;

import ge.tbc.testautomation.tbcbankapp.api.data.constants.MapConstants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public abstract class BaseTbcApi {

    public static final RequestSpecification REQ_SPEC = new RequestSpecBuilder()
            .setBaseUri(MapConstants.TBC_API_BASE_URI)
            .setContentType("application/json")
            .log(LogDetail.ALL)
            .build()
            .filters(new ResponseLoggingFilter());

}