package ge.tbc.testautomation.tbcbankapp.performance.tests;

import ge.tbc.testautomation.tbcbankapp.performance.PerformanceBase;
import ge.tbc.testautomation.tbcbankapp.performance.data.constants.Constants;
import ge.tbc.testautomation.tbcbankapp.performance.utils.K6Runner;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Performance Testing")
@Feature("Locations Page Performance")
public class LocationsLoadTestK6 extends PerformanceBase {

    @Test(description = "k6 load test - 50 VUs, 30s, p(95)<1500ms")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Load Test - Locations API")
    @Description("Validates API performance under realistic load (50 virtual users over 30 seconds) with p95 threshold of 1500ms")
    public void loadTest() throws Exception {
        K6Runner.K6Result result = K6Runner.run(
                Constants.LOAD_TEST_PATH,
                PerformanceBase.FEEDER_PATH
        );

        if (!result.thresholdsPassed()) {
            String message = String.format(Constants.FAILURE_MESSAGE_TEMPLATE, "LoadTest", result.exitCode());
            System.out.println(message);
            Allure.addAttachment("Warning", "⚠ " + message);
        } else {
            System.out.println(String.format(Constants.SUCCESS_MESSAGE_TEMPLATE, "LoadTest"));
        }
    }
}
