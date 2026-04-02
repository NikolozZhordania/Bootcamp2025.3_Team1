package ge.tbc.testautomation.tbcbankapp.performance.tests;

import ge.tbc.testautomation.tbcbankapp.performance.PerformanceBase;
import ge.tbc.testautomation.tbcbankapp.performance.services.LocationsPerformanceService;
import ge.tbc.testautomation.tbcbankapp.performance.steps.PerformanceSteps;
import ge.tbc.testautomation.tbcbankapp.performance.utils.K6Runner.K6Result;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Performance Testing")
@Feature("Locations Page Performance")
public class LocationsStressTestK6 extends PerformanceBase {

    private final LocationsPerformanceService service = new LocationsPerformanceService();
    private final PerformanceSteps steps = new PerformanceSteps();

    @Test(description = "k6 stress test - ramping VUs up to 300, 2min total")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Stress Test - Locations API")
    @Description("Tests API resilience under extreme load with ramping virtual users (up to 300) over 2 minutes to identify breaking points")
    public void stressTest() throws Exception {
        K6Result result = service.runStressTest(FEEDER_PATH);
        steps.reportResult("StressTest", result);
    }
}