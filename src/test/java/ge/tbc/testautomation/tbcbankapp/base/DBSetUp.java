package ge.tbc.testautomation.tbcbankapp.base;

import ge.tbc.testautomation.tbcbankapp.db.utils.DatabaseUtils;
import org.testng.annotations.BeforeSuite;

import java.nio.file.Files;

public class DBSetUp {

    @BeforeSuite(alwaysRun = true)
    public void setupDatabase() throws Exception {
        Files.deleteIfExists(DatabaseUtils.DB_PATH);

        String jdbcUrl = DatabaseUtils.getJdbcUrl(DatabaseUtils.DB_PATH);

        DatabaseUtils.executeSqlFile(jdbcUrl, DatabaseUtils.SCHEMA_PATH);
        DatabaseUtils.executeSqlFile(jdbcUrl, DatabaseUtils.SEED_PATH);

        System.out.println("Database ready at: " + DatabaseUtils.DB_PATH.toAbsolutePath());
    }
}