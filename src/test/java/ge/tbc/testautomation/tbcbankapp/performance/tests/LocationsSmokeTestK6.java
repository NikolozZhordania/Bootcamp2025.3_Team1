package ge.tbc.testautomation.tbcbankapp.performance.tests;

import ge.tbc.testautomation.tbcbankapp.performance.PerformanceBase;
import ge.tbc.testautomation.tbcbankapp.performance.data.constants.Constants;
import ge.tbc.testautomation.tbcbankapp.performance.utils.K6Runner;
import org.testng.annotations.Test;

public class LocationsSmokeTestK6 extends PerformanceBase {

    @Test(description = "k6 smoke test — 1 VU, 10s, validates basic API availability")
    public void smokeTest() throws Exception {
        K6Runner.K6Result result = K6Runner.run(
                Constants.SMOKE_TEST_PATH,
                PerformanceBase.FEEDER_PATH
        );

        if (!result.thresholdsPassed()) {
            System.out.println(String.format(Constants.FAILURE_MESSAGE_TEMPLATE, "SmokeTest", result.exitCode()));
        } else {
            System.out.println(String.format(Constants.SUCCESS_MESSAGE_TEMPLATE, "SmokeTest"));
        }
    }
}
