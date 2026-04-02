package ge.tbc.testautomation.tbcbankapp.performance.services;

import ge.tbc.testautomation.tbcbankapp.performance.data.constants.Constants;
import ge.tbc.testautomation.tbcbankapp.performance.utils.K6Runner;
import ge.tbc.testautomation.tbcbankapp.performance.utils.K6Runner.K6Result;

import java.nio.file.Path;

/**
 * Service layer for the Locations API performance tests.
 *
 * Centralises the mapping of test type → k6 script path.
 * Test classes ask this service to run a scenario; they never
 * reference script paths or K6Runner directly.
 *
 * If a new endpoint is added, create a new XxxPerformanceService
 * following the same pattern — no test class changes required.
 */
public class LocationsPerformanceService {

    /**
     * Runs the smoke test script (1 VU, 10 s).
     *
     * @param feederCsvPath path to the CSV produced by CsvFeederWriter
     */
    public K6Result runSmokeTest(Path feederCsvPath) throws Exception {
        return K6Runner.run(Constants.SMOKE_TEST_PATH, feederCsvPath);
    }

    /**
     * Runs the load test script (50 VUs, 30 s, p95 &lt; 1500 ms).
     *
     * @param feederCsvPath path to the CSV produced by CsvFeederWriter
     */
    public K6Result runLoadTest(Path feederCsvPath) throws Exception {
        return K6Runner.run(Constants.LOAD_TEST_PATH, feederCsvPath);
    }

    /**
     * Runs the stress test script (ramp up to 300 VUs over 4 stages).
     *
     * @param feederCsvPath path to the CSV produced by CsvFeederWriter
     */
    public K6Result runStressTest(Path feederCsvPath) throws Exception {
        return K6Runner.run(Constants.STRESS_TEST_PATH, feederCsvPath);
    }
}