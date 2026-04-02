package ge.tbc.testautomation.tbcbankapp.performance.steps;

import ge.tbc.testautomation.tbcbankapp.performance.data.constants.Constants;
import ge.tbc.testautomation.tbcbankapp.performance.utils.K6Runner.K6Result;
import io.qameta.allure.Allure;

/**
 * Step layer for performance tests.
 *
 * Owns all result-handling logic: console logging and Allure attachment.
 * Test classes call a single method per result — they contain no
 * if/else threshold logic themselves.
 *
 * The naming convention matches the test type labels used in Constants
 * message templates (e.g. "SmokeTest", "LoadTest", "StressTest").
 */
public class PerformanceSteps {

    /**
     * Evaluates and reports a k6 result.
     *
     * Prints a success or warning message to stdout and, on failure,
     * attaches a warning note to the Allure report.
     *
     * @param testLabel human-readable label matching the template placeholder
     *                  (e.g. "SmokeTest", "LoadTest", "StressTest")
     * @param result    the K6Result returned by the service layer
     */
    public void reportResult(String testLabel, K6Result result) {
        if (result.thresholdsPassed()) {
            System.out.println(String.format(Constants.SUCCESS_MESSAGE_TEMPLATE, testLabel));
        } else {
            String message = String.format(
                    Constants.FAILURE_MESSAGE_TEMPLATE, testLabel, result.exitCode());
            System.out.println(message);
            Allure.addAttachment("Warning", "⚠ " + message);
        }
    }
}