package ge.tbc.testautomation.tbcbankapp.api.steps;

import ge.tbc.testautomation.tbcbankapp.api.client.FinancialReportsAPI;
import ge.tbc.testautomation.tbcbankapp.api.data.models.response.financialReports.FinancialReportsResponse;
import ge.tbc.testautomation.tbcbankapp.api.data.models.response.financialReports.ReportItem;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ge.tbc.testautomation.tbcbankapp.api.data.constants.FinancialReportsConstants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FinancialReportsSteps {

    private final FinancialReportsAPI api = new FinancialReportsAPI();
    private Response rawResponse;
    private FinancialReportsResponse pageResponse;
    private List<ReportItem> allReports;
    private List<String> allFileUrls;

    @Step("Fetch financial highlights page content")
    public FinancialReportsSteps fetchFinancialReports() {
        this.rawResponse = api.getFinancialHighlightsPage();
        this.pageResponse = rawResponse
                .then()
                .statusCode(200)
                .extract()
                .as(FinancialReportsResponse.class);

        this.allReports = pageResponse.getSectionComponents().stream()
                .filter(section -> section.getInputs() != null)
                .filter(section -> section.getInputs().getReports() != null)
                .flatMap(section -> section.getInputs().getReports().stream())
                .collect(Collectors.toList());

        this.allFileUrls = new ArrayList<>();
        allReports.forEach(report -> {
            if (report.getFile1() != null && report.getFile1().getSrc() != null) {
                allFileUrls.add("https:" + report.getFile1().getSrc());
            }
            if (report.getFile2() != null && report.getFile2().getSrc() != null) {
                allFileUrls.add("https:" + report.getFile2().getSrc());
            }
        });

        return this;
    }

    @Step("Validate reports list is not empty")
    public FinancialReportsSteps validateReportsNotEmpty() {
        assertThat("Reports list must not be empty", allReports, is(not(empty())));
        return this;
    }

    @Step("Validate all reports have a title and year")
    public FinancialReportsSteps validateReportFields() {
        allReports.forEach(report -> {
            assertThat("Report title must not be blank", report.getTitle(), not(blankOrNullString()));
            assertThat("Report year must not be blank",  report.getYear(),  not(blankOrNullString()));
        });
        return this;
    }

    @Step("Validate all report years are within expected range")
    public FinancialReportsSteps validateReportYearsInRange() {
        allReports.forEach(report -> {
            int year = Integer.parseInt(report.getYear());
            assertThat(
                    "Report year must be >= " + Years.MIN + " for: " + report.getTitle(),
                    year, greaterThanOrEqualTo(Years.MIN)
            );
            assertThat(
                    "Report year must be <= " + Years.MAX + " for: " + report.getTitle(),
                    year, lessThanOrEqualTo(Years.MAX)
            );
        });
        return this;
    }

    @Step("Validate all reports have at least one file attached")
    public FinancialReportsSteps validateEveryReportHasFile() {
        allReports.forEach(report -> {
            boolean hasFile = (report.getFile1() != null && report.getFile1().getSrc() != null)
                    || (report.getFile2() != null && report.getFile2().getSrc() != null);
            assertThat(
                    "Report must have at least one file: " + report.getTitle() + " (" + report.getYear() + ")",
                    hasFile, is(true)
            );
        });
        return this;
    }

    @Step("Validate all file URLs point to trusted Contentful domains")
    public FinancialReportsSteps validateFileUrlDomains() {
        allFileUrls.forEach(url -> {
            boolean isTrustedDomain = url.startsWith(URI.ASSETS_BASE)
                    || url.startsWith(URI.DOWNLOADS_BASE);
            assertThat(
                    "File URL must come from a trusted Contentful domain: " + url,
                    isTrustedDomain, is(true)
            );
        });
        return this;
    }

    @Step("Validate all files are reachable via HEAD request")
    public FinancialReportsSteps validateFilesAreReachable() {
        allFileUrls.forEach(url -> {
            Response head = api.headFile(url);
            assertThat(
                    "File must be reachable (expected 200): " + url,
                    head.statusCode(), equalTo(200)
            );
        });
        return this;
    }

    @Step("Validate Content-Type is correct for each file")
    public FinancialReportsSteps validateFileContentTypes() {
        allFileUrls.forEach(url -> {
            String contentType = api.headFile(url).header("Content-Type");
            assertThat(
                    "Content-Type must be present for: " + url,
                    contentType, notNullValue()
            );
            if (url.endsWith(".pdf")) {
                assertThat(
                        "PDF must have Content-Type application/pdf: " + url,
                        contentType, containsString("application/pdf")
                );
            } else if (url.endsWith(".xlsx")) {
                assertThat(
                        "Excel must have correct Content-Type: " + url,
                        contentType, containsString("spreadsheet")
                );
            }
        });
        return this;
    }

    @Step("Validate all files have an ETag header")
    public FinancialReportsSteps validateFilesHaveETag() {
        allFileUrls.forEach(url -> {
            String etag = api.headFile(url).header("ETag");
            assertThat(
                    "ETag must be present, meaning file exists on CDN: " + url,
                    etag, notNullValue()
            );
        });
        return this;
    }

    @Step("Validate all files have a Last-Modified header")
    public FinancialReportsSteps validateFilesHaveLastModified() {
        allFileUrls.forEach(url -> {
            String lastModified = api.headFile(url).header("Last-Modified");
            assertThat(
                    "Last-Modified must be present for: " + url,
                    lastModified, notNullValue()
            );
        });
        return this;
    }

    @Step("Validate Excel file sizes are above minimum threshold")
    public FinancialReportsSteps validateExcelFileSizes() {
        allFileUrls.stream()
                .filter(url -> url.endsWith(".xlsx"))
                .forEach(url -> {
                    String contentLength = api.headFile(url).header("Content-Length");
                    if (contentLength != null) {
                        long sizeInBytes = Long.parseLong(contentLength);
                        assertThat(
                                "File size must be above " + FileSize.MIN_BYTES + " bytes for: " + url,
                                sizeInBytes, greaterThan(FileSize.MIN_BYTES)
                        );
                    }
                });
        return this;
    }
}
