package ge.tbc.testautomation.tbcbankapp.performance.data.constants;

public class Constants {
    public static final String SMOKE_TEST_PATH = "performance/smoke_test.js";
    public static final String STRESS_TEST_PATH = "performance/stress_test.js";
    public static final String LOAD_TEST_PATH = "performance/load_test.js";

    public static final String SUCCESS_MESSAGE_TEMPLATE = "[%s] ✅ All thresholds passed.";
    public static final String FAILURE_MESSAGE_TEMPLATE = "[%s] ⚠ Thresholds not met (exit code: %d). Review k6 output above.";
}
