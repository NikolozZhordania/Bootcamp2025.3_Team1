package ge.tbc.testautomation.tbcbankapp.api.tests;

import ge.tbc.testautomation.tbcbankapp.api.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Feature("Financial Reports API")
public class FinancialReportsIntegrityTest extends BaseTest {

    @Story("Reports Structure")
    @Description("Verify that the financial highlights page endpoint returns a non-empty list of report items.")
    @Test(description = "SCRUM-T21 Step 1: Financial Reports Response Structure Validation")
    public void reportsStructureTest() {
        financialReportsSteps
                .fetchFinancialReports()
                .validateReportsNotEmpty()
                .validateReportFields();
    }

    @Story("Reports Structure")
    @Description("Verify that every report's year falls within the expected range of published reports.")
    @Test(description = "SCRUM-T21 Step 2: Report Year Range Validation")
    public void reportYearsInRangeTest() {
        financialReportsSteps
                .fetchFinancialReports()
                .validateReportsNotEmpty()
                .validateReportYearsInRange();
    }

    @Story("File Integrity")
    @Description("Verify that every report item has at least one file attached with a non-null src.")
    @Test(description = "SCRUM-T21 Step 3: Every Report Has an Attached File")
    public void everyReportHasFileTest() {
        financialReportsSteps
                .fetchFinancialReports()
                .validateReportsNotEmpty()
                .validateEveryReportHasFile();
    }

    @Story("File Integrity")
    @Description("Verify that all file URLs point to trusted Contentful CDN domains and not unexpected third-party hosts.")
    @Test(description = "SCRUM-T21 Step 4: File URL Domain Trust Validation")
    public void fileUrlDomainTest() {
        financialReportsSteps
                .fetchFinancialReports()
                .validateReportsNotEmpty()
                .validateFileUrlDomains();
    }

    @Story("File Integrity")
    @Description("Verify that every downloadable file responds with 200 to a HEAD request.")
    @Test(description = "SCRUM-T21 Step 5: File Reachability Check")
    public void filesAreReachableTest() {
        financialReportsSteps
                .fetchFinancialReports()
                .validateReportsNotEmpty()
                .validateFilesAreReachable();
    }

    @Story("File Integrity")
    @Description("Verify that every file returns the correct Content-Type for its format.")
    @Test(description = "SCRUM-T21 Step 6: File Content-Type Validation")
    public void fileContentTypesTest() {
        financialReportsSteps
                .fetchFinancialReports()
                .validateReportsNotEmpty()
                .validateFileContentTypes();
    }

    @Story("File Integrity")
    @Description("Verify that every file on the CDN has an ETag header, confirming it is a real stored asset.")
    @Test(description = "SCRUM-T21 Step 7: File ETag Presence Validation")
    public void filesHaveETagTest() {
        financialReportsSteps
                .fetchFinancialReports()
                .validateReportsNotEmpty()
                .validateFilesHaveETag();
    }

    @Story("File Integrity")
    @Description("Verify that every file has a Last-Modified header, confirming it was properly uploaded.")
    @Test(description = "SCRUM-T21 Step 8: File Last-Modified Presence Validation")
    public void filesHaveLastModifiedTest() {
        financialReportsSteps
                .fetchFinancialReports()
                .validateReportsNotEmpty()
                .validateFilesHaveLastModified();
    }

    @Story("File Integrity")
    @Description("Verify that Excel files report a Content-Length above the minimum threshold.")
    @Test(description = "SCRUM-T21 Step 9: Excel File Size Validation")
    public void excelFileSizesTest() {
        financialReportsSteps
                .fetchFinancialReports()
                .validateReportsNotEmpty()
                .validateExcelFileSizes();
    }
}
