package ge.tbc.testautomation.tbcbankapp.api;

import ge.tbc.testautomation.tbcbankapp.db.mappers.LocationMapper;
import ge.tbc.testautomation.tbcbankapp.db.models.Location;
import ge.tbc.testautomation.tbcbankapp.db.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class SampleTest {

    @BeforeSuite
    public void setupDatabase() throws Exception {
        Path projectRoot = Paths.get(System.getProperty("user.dir"));
        Path dbPath      = projectRoot.resolve("tbc_map.db");
        Path schemaPath  = projectRoot.resolve("db/schema.sql");
        Path seedPath    = projectRoot.resolve("db/seed.sql");

        // Replicate: rm -f tbc_map.db
        Files.deleteIfExists(dbPath);

        String jdbcUrl = "jdbc:sqlite:" + dbPath.toAbsolutePath();

        // Replicate: sqlite3 tbc_map.db < db/schema.sql
        executeSqlFile(jdbcUrl, schemaPath);

        // Replicate: sqlite3 tbc_map.db < db/seed.sql
        executeSqlFile(jdbcUrl, seedPath);

        System.out.println("SQLite database created at: " + dbPath.toAbsolutePath());
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

    private String findGitBash() {
        // Common Git Bash locations on Windows
        String[] candidates = {
                "C:\\Program Files\\Git\\bin\\bash.exe",
                "C:\\Program Files (x86)\\Git\\bin\\bash.exe",
                System.getenv("ProgramFiles") + "\\Git\\bin\\bash.exe"
        };

        for (String path : candidates) {
            if (new java.io.File(path).exists()) {
                return path;
            }
        }

        throw new RuntimeException(
                "Git Bash not found. Install Git for Windows from https://git-scm.com " +
                        "or add its path to the candidates list in findGitBash()."
        );
    }

    private Path getSeedPath() throws URISyntaxException {
        // Looks for seed.sql in src/test/resources/db/seed.sql
        return Paths.get(
                getClass().getClassLoader()
                        .getResource("db/seed.sql")
                        .toURI()
        );
    }

    @Test
    public void testDatabaseConnection() {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            LocationMapper mapper = session.getMapper(LocationMapper.class);
            List<Location> locations = mapper.getFirstFiveLocations();

            System.out.println("--- Database Content Check ---");
            for (Location loc : locations) {
                System.out.println(
                        "ID: " + loc.getId() +
                                " | Name: " + loc.getName() +
                                " | City: " + loc.getRegionName()
                );
            }

            assert !locations.isEmpty() : "Database is empty! Check seed.sql";
        }
    }
}
