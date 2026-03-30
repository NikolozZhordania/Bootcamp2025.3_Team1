package ge.tbc.testautomation.tbcbankapp.performance.tests;

import ge.tbc.testautomation.tbcbankapp.performance.PerformanceBase;
import ge.tbc.testautomation.tbcbankapp.performance.data.constants.Constants;
import ge.tbc.testautomation.tbcbankapp.performance.utils.K6Runner;
import org.testng.annotations.Test;

public class LocationsLoadTestK6 extends PerformanceBase {

    @Test(description = "k6 load test — 50 VUs, 30s, p(95)<1500ms")
    public void loadTest() throws Exception {
        K6Runner.K6Result result = K6Runner.run(
                Constants.LOAD_TEST_PATH,
                PerformanceBase.FEEDER_PATH
        );

        if (!result.thresholdsPassed()) {
            System.out.println(String.format(Constants.FAILURE_MESSAGE_TEMPLATE, "LoadTest", result.exitCode()));
        } else {
            System.out.println(String.format(Constants.SUCCESS_MESSAGE_TEMPLATE, "LoadTest"));
        }
    }
}
