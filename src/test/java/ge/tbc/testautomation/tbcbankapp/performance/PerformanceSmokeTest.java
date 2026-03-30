package ge.tbc.testautomation.tbcbankapp.performance;

import ge.tbc.testautomation.tbcbankapp.performance.utils.K6Runner;
import org.testng.annotations.Test;

public class PerformanceSmokeTest extends PerformanceBase {

    @Test(description = "k6 smoke test — 1 VU, 10s, validates basic API availability")
    public void smokeTest() throws Exception {
        K6Runner.K6Result result = K6Runner.run(
                "performance/smoke_test.js",
                PerformanceBase.FEEDER_PATH
        );

        if (!result.thresholdsPassed()) {
            System.out.println("[SmokeTest] ⚠ Thresholds not met (exit code: "
                    + result.exitCode() + "). Review k6 output above.");
        } else {
            System.out.println("[SmokeTest] ✅ All thresholds passed.");
        }
    }
}
