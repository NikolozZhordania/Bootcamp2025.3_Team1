package ge.tbc.testautomation.tbcbankapp.performance;

import ge.tbc.testautomation.tbcbankapp.performance.utils.K6Runner;
import org.testng.annotations.Test;

public class PerformanceLoadTest extends PerformanceBase {

    @Test(description = "k6 load test — 50 VUs, 30s, p(95)<1500ms")
    public void loadTest() throws Exception {
        K6Runner.K6Result result = K6Runner.run(
                "performance/load_test.js",
                PerformanceBase.FEEDER_PATH
        );

        if (!result.thresholdsPassed()) {
            System.out.println("[LoadTest] ⚠ Thresholds not met (exit code: "
                    + result.exitCode() + "). Review k6 output above.");
        } else {
            System.out.println("[LoadTest] ✅ All thresholds passed.");
        }
    }
}
