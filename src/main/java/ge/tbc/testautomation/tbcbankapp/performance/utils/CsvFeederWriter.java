package ge.tbc.testautomation.tbcbankapp.performance.utils;

import ge.tbc.testautomation.tbcbankapp.db.utils.DatabaseUtils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Exports the tbc_locations table from the SQLite database into a CSV file
 * consumed by all k6 scripts as their data feeder.
 *
 * Extracted from PerformanceBase to keep the base class focused on
 * lifecycle setup only, and to make the CSV generation independently reusable.
 *
 * CSV format:
 *   id,regionName,type
 *   1,"თბილისი",ATM
 *   ...
 *
 * regionName is quoted to safely handle Georgian (UTF-8 multi-byte) characters.
 */
public class CsvFeederWriter {

    private static final String QUERY = "SELECT id, regionName, type FROM tbc_locations";

    /**
     * Queries all location records and writes them to the given CSV path.
     *
     * @param outputPath destination file (created or overwritten)
     */
    public static void write(Path outputPath) throws Exception {
        String jdbcUrl = DatabaseUtils.getJdbcUrl(DatabaseUtils.DB_PATH);

        try (Connection conn    = DriverManager.getConnection(jdbcUrl);
             Statement stmt     = conn.createStatement();
             ResultSet rs       = stmt.executeQuery(QUERY);
             PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {

            writer.println("id,regionName,type");

            while (rs.next()) {
                int    id         = rs.getInt("id");
                String regionName = rs.getString("regionName");
                String type       = rs.getString("type");

                // Quote regionName to handle Georgian characters safely in CSV
                writer.printf("%d,\"%s\",%s%n", id, regionName, type);
            }
        }

        System.out.println("[CsvFeederWriter] Feeder CSV written to: " + outputPath.toAbsolutePath());
    }
}