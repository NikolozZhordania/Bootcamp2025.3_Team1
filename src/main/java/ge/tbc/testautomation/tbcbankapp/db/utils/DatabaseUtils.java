package ge.tbc.testautomation.tbcbankapp.db.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseUtils {

    public static final Path PROJECT_ROOT = Paths.get(System.getProperty("user.dir"));
    public static final Path DB_PATH = PROJECT_ROOT.resolve("tbc_map.db");
    public static final Path SCHEMA_PATH = PROJECT_ROOT.resolve("db/schema.sql");
    public static final Path SEED_PATH = PROJECT_ROOT.resolve("db/seed.sql");

    public static void executeSqlFile(String jdbcUrl, Path sqlFile) throws Exception {
        String sql = Files.readString(sqlFile);
        try (Connection conn = DriverManager.getConnection(jdbcUrl);
             Statement stmt = conn.createStatement()) {
            for (String statement : sql.split(";")) {
                String trimmed = statement.trim();
                if (!trimmed.isEmpty()) {
                    stmt.execute(trimmed);
                }
            }
        }
    }

    public static String getJdbcUrl(Path dbPath) {
        return "jdbc:sqlite:" + dbPath.toAbsolutePath()
                .toString()
                .replace("\\", "/");
    }
}
