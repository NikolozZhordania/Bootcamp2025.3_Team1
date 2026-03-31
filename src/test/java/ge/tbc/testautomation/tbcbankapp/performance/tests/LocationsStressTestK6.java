package ge.tbc.testautomation.tbcbankapp.performance.tests;

import ge.tbc.testautomation.tbcbankapp.performance.PerformanceBase;
import ge.tbc.testautomation.tbcbankapp.performance.data.constants.Constants;
import ge.tbc.testautomation.tbcbankapp.performance.utils.K6Runner;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Performance Testing")
@Feature("Locations Page Performance")
public class LocationsStressTestK6 extends PerformanceBase {

    @Test(description = "k6 stress test - ramping VUs up to 300, 2min total")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Stress Test - Locations API")
    @Description("Tests API resilience under extreme load with ramping virtual users (up to 300) over 2 minutes to identify breaking points")
    public void stressTest() throws Exception {
        K6Runner.K6Result result = K6Runner.run(
                Constants.STRESS_TEST_PATH,
                PerformanceBase.FEEDER_PATH
        );

        if (!result.thresholdsPassed()) {
            String message = String.format(Constants.FAILURE_MESSAGE_TEMPLATE, "StressTest", result.exitCode());
            System.out.println(message);
            Allure.addAttachment("Warning", "⚠ " + message);
        } else {
            System.out.println(String.format(Constants.SUCCESS_MESSAGE_TEMPLATE, "StressTest"));
        }
    }
}
