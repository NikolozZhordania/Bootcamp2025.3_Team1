package ge.tbc.testautomation.tbcbankapp.performance.tests;

import ge.tbc.testautomation.tbcbankapp.performance.PerformanceBase;
import ge.tbc.testautomation.tbcbankapp.performance.data.constants.Constants;
import ge.tbc.testautomation.tbcbankapp.performance.utils.K6Runner;
import org.testng.annotations.Test;

public class LocationsStressTestK6 extends PerformanceBase {

    @Test(description = "k6 stress test — ramping VUs up to 300, 2min total")
    public void stressTest() throws Exception {
        K6Runner.K6Result result = K6Runner.run(
                Constants.STRESS_TEST_PATH,
                PerformanceBase.FEEDER_PATH
        );

        if (!result.thresholdsPassed()) {
            System.out.println(String.format(Constants.FAILURE_MESSAGE_TEMPLATE, "StressTest", result.exitCode()));
        } else {
            System.out.println(String.format(Constants.SUCCESS_MESSAGE_TEMPLATE, "StressTest"));
        }
    }
}
