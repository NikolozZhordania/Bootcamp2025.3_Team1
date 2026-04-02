package ge.tbc.testautomation.tbcbankapp.performance.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;

/**
 * Launches k6 as a subprocess for a given script resource on the classpath.
 *
 * Why the temp-copy step:
 *   k6 requires a real filesystem path — it cannot run a script from a jar:// URL.
 *   We mirror the entire performance/ resource folder into a temp directory so that
 *   relative imports (e.g. './utils/httpClient.js') resolve correctly at runtime.
 *   The feeder CSV is copied into the same temp root, matching the path k6 scripts
 *   expect: open('./performance_feeder.csv').
 *
 * k6 must be installed globally and available on PATH.
 * CI note: install k6 on your runner before the Maven test step.
 *   GitHub Actions → uses: grafana/setup-k6-action@v1
 *   GitLab CI      → before_script: apt-get install -y k6
 */
public class K6Runner {

    public record K6Result(int exitCode, boolean thresholdsPassed) {}

    private static final String PERFORMANCE_RESOURCE_DIR = "performance";

    /**
     * Mirrors the classpath performance/ folder to a temp directory, copies the
     * feeder CSV alongside the scripts, then runs k6 against the requested script.
     *
     * @param classpathResource e.g. "performance/smoke_test.js"
     * @param feederCsvPath     absolute path to performance_feeder.csv
     * @return K6Result with exit code and whether all thresholds passed
     */
    public static K6Result run(String classpathResource, Path feederCsvPath) throws Exception {
        Path tempDir    = mirrorResourceDirToTemp(feederCsvPath);
        Path scriptFile = tempDir.resolve(Path.of(classpathResource).getFileName());

        return executeK6(scriptFile, tempDir);
    }

    // -------------------------------------------------------------------------
    // Mirror the entire performance/ classpath folder into a temp directory
    // -------------------------------------------------------------------------

    private static Path mirrorResourceDirToTemp(Path feederCsvPath)
            throws IOException, URISyntaxException {

        URL dirUrl = K6Runner.class.getClassLoader().getResource(PERFORMANCE_RESOURCE_DIR);
        if (dirUrl == null) {
            throw new IllegalStateException(
                    "Classpath directory not found: " + PERFORMANCE_RESOURCE_DIR +
                            "\nEnsure performance/ exists under src/main/resources/"
            );
        }

        Path tempDir = Files.createTempDirectory("k6-run-");
        tempDir.toFile().deleteOnExit();

        // Works both on plain filesystem (IDE/Maven) and inside a JAR (CI fat-jar)
        URI uri = dirUrl.toURI();
        if ("jar".equals(uri.getScheme())) {
            copyFromJar(uri, tempDir);
        } else {
            copyFromFilesystem(Path.of(uri), tempDir);
        }

        // Copy feeder CSV to the temp root — scripts use open('./performance_feeder.csv')
        Path tempCsv = tempDir.resolve("performance_feeder.csv");
        Files.copy(feederCsvPath, tempCsv, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("[K6Runner] performance/ mirrored to: " + tempDir);
        System.out.println("[K6Runner] Feeder CSV copied to:      " + tempCsv);

        return tempDir;
    }

    /** Copies resources when running from a plain directory (IDE / mvn test). */
    private static void copyFromFilesystem(Path sourceDir, Path targetDir) throws IOException {
        Files.walkFileTree(sourceDir, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException {
                Path relative = sourceDir.relativize(dir);
                Path target   = targetDir.resolve(relative.toString());
                Files.createDirectories(target);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                Path relative = sourceDir.relativize(file);
                Path target   = targetDir.resolve(relative.toString());
                Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /** Copies resources when running from inside a JAR (CI). */
    private static void copyFromJar(URI jarUri, Path targetDir) throws IOException {
        try (FileSystem jar = FileSystems.newFileSystem(jarUri, Collections.emptyMap())) {
            Path jarRoot = jar.getPath(PERFORMANCE_RESOURCE_DIR);
            Files.walkFileTree(jarRoot, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                        throws IOException {
                    Path relative = jarRoot.relativize(dir);
                    Files.createDirectories(targetDir.resolve(relative.toString()));
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        throws IOException {
                    Path relative = jarRoot.relativize(file);
                    Files.copy(file, targetDir.resolve(relative.toString()),
                            StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    // -------------------------------------------------------------------------
    // Subprocess execution — unchanged
    // -------------------------------------------------------------------------

    private static K6Result executeK6(Path scriptFile, Path workingDir) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(
                "k6", "run", scriptFile.toAbsolutePath().toString()
        );
        pb.directory(workingDir.toFile());
        pb.redirectErrorStream(true);

        System.out.println("[K6Runner] Starting: k6 run " + scriptFile.getFileName());
        System.out.println("[K6Runner] " + "=".repeat(60));

        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();

        System.out.println("[K6Runner] " + "=".repeat(60));
        System.out.println("[K6Runner] Finished with exit code: " + exitCode);

        return new K6Result(exitCode, exitCode == 0);
    }
}