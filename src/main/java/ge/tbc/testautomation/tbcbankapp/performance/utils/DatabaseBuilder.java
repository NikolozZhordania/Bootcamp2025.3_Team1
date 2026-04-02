package ge.tbc.testautomation.tbcbankapp.performance.utils;

import ge.tbc.testautomation.tbcbankapp.db.utils.DatabaseUtils;

import java.nio.file.Files;

/**
 * Rebuilds the SQLite database from scratch using schema.sql and seed.sql.
 *
 * Extracted from PerformanceBase so the base class has no direct DB concerns —
 * it simply delegates here. This also allows other test suites (e.g., future
 * API performance tests) to reuse DB construction independently.
 */
public class DatabaseBuilder {

    /**
     * Drops the existing database file (if any), then runs schema.sql followed
     * by seed.sql to produce a fresh, fully-seeded database.
     */
    public static void rebuild() throws Exception {
        Files.deleteIfExists(DatabaseUtils.DB_PATH);

        String jdbcUrl = DatabaseUtils.getJdbcUrl(DatabaseUtils.DB_PATH);

        DatabaseUtils.executeSqlFile(jdbcUrl, DatabaseUtils.SCHEMA_PATH);
        DatabaseUtils.executeSqlFile(jdbcUrl, DatabaseUtils.SEED_PATH);

        System.out.println("[DatabaseBuilder] Database ready at: " + DatabaseUtils.DB_PATH.toAbsolutePath());
    }
}