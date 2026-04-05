package ge.tbc.testautomation.tbcbankapp.ui.tests;

import ge.tbc.testautomation.tbcbankapp.ui.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class QRCodeNavigationTest extends BaseTest {

    @Story("Homepage Access")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Open the homepage and verify that the main page elements and navigation menu are visible.")
    @Test(description = "SCRUM-T13 Step 1: Homepage access", priority = 1)
    public void homepageAccess() {
        homeSteps
                .verifyHomepageLoaded()
                .verifyMenuVisibility()
                .openNavigationMenu();
    }

    @Story("TBC Card Navigation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the dropdown menu is visible, TBC Cards option is accessible, and the take card button can be clicked.")
    @Test(description = "SCRUM-T13 Step 1: TBC Cards Navigation",
            priority = 2,
            dependsOnMethods = "homepageAccess")
    public void navigateToTbcCard() {
        homeSteps
                .verifyDropDownMenuVisibility()
                .verifyTbcCardsVisibility();
        tbcCardsSteps
                .waitForButtonToBeVisible()
                .clickTakeCardButton();
    }

    @Story("QR Code Verification")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the QR code is visible, read the URL from it, navigate to it, and assert the correct page is loaded.")
    @Test(description = "SCRUM-T13 Step 3: QR Code Verification and Navigation",
            priority = 3,
            dependsOnMethods = "navigateToTbcCard")
    public void verifyQRCodeNavigation() throws Exception {
        tbcCardsSteps
                .waitForQRToBeVisible()
                .navigateToQRUrl()
                .assertNavigatedToQRUrl();
    }
}