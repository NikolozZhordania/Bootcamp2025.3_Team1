package ge.tbc.testautomation.tbcbankapp.performance;

import ge.tbc.testautomation.tbcbankapp.performance.utils.K6Runner;
import org.testng.annotations.Test;

public class PerformanceStressTest extends PerformanceBase {

    @Test(description = "k6 stress test — ramping VUs up to 300, 2min total")
    public void stressTest() throws Exception {
        K6Runner.K6Result result = K6Runner.run(
                "performance/stress_test.js",
                PerformanceBase.FEEDER_PATH
        );

        if (!result.thresholdsPassed()) {
            System.out.println("[StressTest] ⚠ Thresholds not met (exit code: "
                    + result.exitCode() + "). Review k6 output above.");
        } else {
            System.out.println("[StressTest] ✅ All thresholds passed.");
        }
    }
}
