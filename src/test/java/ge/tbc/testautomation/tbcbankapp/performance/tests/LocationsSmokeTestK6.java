package ge.tbc.testautomation.tbcbankapp.performance.tests;

import ge.tbc.testautomation.tbcbankapp.performance.PerformanceBase;
import ge.tbc.testautomation.tbcbankapp.performance.services.LocationsPerformanceService;
import ge.tbc.testautomation.tbcbankapp.performance.steps.PerformanceSteps;
import ge.tbc.testautomation.tbcbankapp.performance.utils.K6Runner.K6Result;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Performance Testing")
@Feature("Locations Page Performance")
public class LocationsSmokeTestK6 extends PerformanceBase {

    private final LocationsPerformanceService service = new LocationsPerformanceService();
    private final PerformanceSteps steps = new PerformanceSteps();

    @Test(description = "k6 smoke test - 1 VU, 10s, validates basic API availability")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Smoke Test - Locations API")
    @Description("Validates basic API availability and response times with minimal load (1 virtual user)")
    public void smokeTest() throws Exception {
        K6Result result = service.runSmokeTest(FEEDER_PATH);
        steps.reportResult("SmokeTest", result);
    }
}