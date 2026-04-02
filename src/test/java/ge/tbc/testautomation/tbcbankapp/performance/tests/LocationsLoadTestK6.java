package ge.tbc.testautomation.tbcbankapp.performance.tests;

import ge.tbc.testautomation.tbcbankapp.performance.PerformanceBase;
import ge.tbc.testautomation.tbcbankapp.performance.services.LocationsPerformanceService;
import ge.tbc.testautomation.tbcbankapp.performance.steps.PerformanceSteps;
import ge.tbc.testautomation.tbcbankapp.performance.utils.K6Runner.K6Result;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Performance Testing")
@Feature("Locations Page Performance")
public class LocationsLoadTestK6 extends PerformanceBase {

    private final LocationsPerformanceService service = new LocationsPerformanceService();
    private final PerformanceSteps steps = new PerformanceSteps();

    @Test(description = "k6 load test - 50 VUs, 30s, p(95)<1500ms")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Load Test - Locations API")
    @Description("Validates API performance under realistic load (50 virtual users over 30 seconds) with p95 threshold of 1500ms")
    public void loadTest() throws Exception {
        K6Result result = service.runLoadTest(FEEDER_PATH);
        steps.reportResult("LoadTest", result);
    }
}