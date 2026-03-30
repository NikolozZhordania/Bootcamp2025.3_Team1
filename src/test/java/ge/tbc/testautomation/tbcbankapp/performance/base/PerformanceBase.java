package ge.tbc.testautomation.tbcbankapp.performance;

import ge.tbc.testautomation.tbcbankapp.db.utils.DatabaseUtils;
import org.testng.annotations.BeforeSuite;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Base class for all performance tests.
 *
 * Responsibilities (in order):
 *   1. Rebuild the SQLite database from schema.sql + seed.sql
 *   2. Export performance_feeder.csv from the DB (id, regionName, type)
 *
 * Intentionally does NOT extend DBSetUp — performance tests manage their
 * own DB lifecycle so they never interfere with API/UI suite runs.
 */
public class PerformanceBase {

    protected static final Path PROJECT_ROOT = Paths.get(System.getProperty("user.dir"));

    private static final Path DB_PATH     = PROJECT_ROOT.resolve("tbc_map.db");
    private static final Path SCHEMA_PATH = PROJECT_ROOT.resolve("db/schema.sql");
    private static final Path SEED_PATH   = PROJECT_ROOT.resolve("db/seed.sql");

    protected static final Path FEEDER_PATH = PROJECT_ROOT.resolve("performance_feeder.csv");

    @BeforeSuite(alwaysRun = true)
    public void setupPerformanceEnvironment() throws Exception {
        buildDatabase();
        generateFeederCsv();
    }

    private void buildDatabase() throws Exception {
        Files.deleteIfExists(DB_PATH);

        String jdbcUrl = DatabaseUtils.getJdbcUrl(DB_PATH);

        DatabaseUtils.executeSqlFile(jdbcUrl, SCHEMA_PATH);
        DatabaseUtils.executeSqlFile(jdbcUrl, SEED_PATH);

        System.out.println("[PerformanceBase] Database ready at: " + DB_PATH.toAbsolutePath());
    }

    private void generateFeederCsv() throws Exception {
        String jdbcUrl = DatabaseUtils.getJdbcUrl(DB_PATH);

        try (Connection conn = DriverManager.getConnection(jdbcUrl);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(
                     "SELECT id, regionName, type FROM tbc_locations");
             PrintWriter writer = new PrintWriter(new FileWriter(FEEDER_PATH.toFile()))) {

            writer.println("id,regionName,type");

            while (rs.next()) {
                int    id         = rs.getInt("id");
                String regionName = rs.getString("regionName");
                String type       = rs.getString("type");

                // Quote regionName to handle Georgian characters safely in CSV
                writer.printf("%d,\"%s\",%s%n", id, regionName, type);
            }
        }

        System.out.println("[PerformanceBase] Feeder CSV ready at: " + FEEDER_PATH.toAbsolutePath());
    }
}