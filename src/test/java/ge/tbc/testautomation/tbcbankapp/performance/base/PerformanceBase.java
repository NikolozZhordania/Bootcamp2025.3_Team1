package ge.tbc.testautomation.tbcbankapp.performance;

import ge.tbc.testautomation.tbcbankapp.db.utils.DatabaseUtils;
import ge.tbc.testautomation.tbcbankapp.performance.utils.CsvFeederWriter;
import ge.tbc.testautomation.tbcbankapp.performance.utils.DatabaseBuilder;
import org.testng.annotations.BeforeSuite;

import java.nio.file.Path;

/**
 * Base class for all performance tests.
 *
 * Responsibilities:
 *   1. Trigger a clean rebuild of the SQLite database (via DatabaseBuilder)
 *   2. Export performance_feeder.csv from the DB   (via CsvFeederWriter)
 *
 * No direct DB or file I/O lives here — this class is purely a TestNG
 * lifecycle coordinator. All logic sits in the focused utility classes.
 */
public class PerformanceBase {

    protected static final Path FEEDER_PATH =
            DatabaseUtils.PROJECT_ROOT.resolve("performance_feeder.csv");

    @BeforeSuite(alwaysRun = true)
    public void setupPerformanceEnvironment() throws Exception {
        DatabaseBuilder.rebuild();
        CsvFeederWriter.write(FEEDER_PATH);
    }
}