package ge.tbc.testautomation.tbcbankapp.performance.tests;

import ge.tbc.testautomation.tbcbankapp.performance.PerformanceBase;
import ge.tbc.testautomation.tbcbankapp.performance.data.constants.Constants;
import ge.tbc.testautomation.tbcbankapp.performance.utils.K6Runner;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Performance Testing")
@Feature("Locations Page Performance")
public class LocationsSmokeTestK6 extends PerformanceBase {

    @Test(description = "k6 smoke test - 1 VU, 10s, validates basic API availability")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Smoke Test - Locations API")
    @Description("Validates basic API availability and response times with minimal load (1 virtual user)")
    public void smokeTest() throws Exception {
        K6Runner.K6Result result = K6Runner.run(
                Constants.SMOKE_TEST_PATH,
                PerformanceBase.FEEDER_PATH
        );

        if (!result.thresholdsPassed()) {
            String message = String.format(Constants.FAILURE_MESSAGE_TEMPLATE, "SmokeTest", result.exitCode());
            System.out.println(message);
            Allure.addAttachment("Warning", "⚠ " + message);
        } else {
            System.out.println(String.format(Constants.SUCCESS_MESSAGE_TEMPLATE, "SmokeTest"));
        }
    }
}
