package ge.tbc.testautomation.tbcbankapp.base;

import org.testng.annotations.BeforeSuite;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBSetUp {

    @BeforeSuite(alwaysRun = true)
    public void setupDatabase() throws Exception {
        Path projectRoot = Paths.get(System.getProperty("user.dir"));
        Path dbPath      = projectRoot.resolve("tbc_map.db");
        Path schemaPath  = projectRoot.resolve("db/schema.sql");
        Path seedPath    = projectRoot.resolve("db/seed.sql");

        Files.deleteIfExists(dbPath);

        String jdbcUrl = "jdbc:sqlite:" + dbPath.toAbsolutePath()
                .toString()
                .replace("\\", "/");

        executeSqlFile(jdbcUrl, schemaPath);
        executeSqlFile(jdbcUrl, seedPath);

        System.out.println("Database ready at: " + dbPath.toAbsolutePath());
    }

    private void executeSqlFile(String jdbcUrl, Path sqlFile) throws Exception {
        String sql = Files.readString(sqlFile);
        try (Connection conn = DriverManager.getConnection(jdbcUrl);
             Statement stmt  = conn.createStatement()) {
            for (String statement : sql.split(";")) {
                String trimmed = statement.trim();
                if (!trimmed.isEmpty()) {
                    stmt.execute(trimmed);
                }
            }
        }
    }
}