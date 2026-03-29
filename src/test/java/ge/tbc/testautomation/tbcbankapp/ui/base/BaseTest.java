package ge.tbc.testautomation.tbcbankapp.ui.base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbc.testautomation.tbcbankapp.ui.data.GeoLocationRandomization;
import ge.tbc.testautomation.tbcbankapp.ui.pages.CommonPage;
import ge.tbc.testautomation.tbcbankapp.ui.steps.*;
import ge.tbc.testautomation.tbcbankapp.ui.utils.DeviceType;
import ge.tbc.testautomation.tbcbankapp.ui.utils.TestContext;
import org.testng.annotations.*;

import java.util.Random;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected double[] currentLocation;

    protected HomeSteps homeSteps;
    protected LocationSteps locationSteps;

    @Parameters({"device", "browser"})
    @BeforeClass(alwaysRun = true)
    public void setUp(
            @Optional("desktop") String device,
            @Optional("chromium") String browserType) {

        DeviceType deviceType = device.equalsIgnoreCase("mobile")
                ? DeviceType.MOBILE
                : DeviceType.DESKTOP;
        TestContext.setDevice(deviceType);

        playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(java.util.List.of("--start-maximized"));

        switch (browserType.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(options);
                break;
            case "webkit":
                browser = playwright.webkit().launch(options);
                break;
            case "chromium":
            default:
                browser = playwright.chromium().launch(options);
                break;
        }

        createContextAndPage(deviceType);
        initSteps();
        handleCookies();
    }

    private void createContextAndPage(DeviceType deviceType) {
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();

        if (deviceType == DeviceType.MOBILE) {
            contextOptions.setViewportSize(390, 844);
            contextOptions.setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 15_0 like Mac OS X)");
        } else {
            contextOptions.setViewportSize(null);
        }

        //get a random district from GeoLocationRandomization and place the user there
        // random district
         GeoLocationRandomization.District[] districts = GeoLocationRandomization.District.values();
         currentLocation = GeoLocationRandomization.getRandomFromDistrict(districts[new Random().nextInt(districts.length)]);

        // specific district
//        currentLocation = GeoLocationRandomization.getRandomFromDistrict(GeoLocationRandomization.District.VAKE);

        contextOptions
                .setGeolocation(currentLocation[0], currentLocation[1])
                .setPermissions(java.util.List.of("geolocation"));

        context = browser.newContext(contextOptions);
        page = context.newPage();
    }

    private void initSteps() {
        homeSteps = new HomeSteps(page);
        locationSteps = new LocationSteps(page);
    }

    private void handleCookies() {
        try {
            CommonPage commonPage = new CommonPage(page);
            Locator cookieButton = commonPage.cookieAcceptButton;

            cookieButton.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(3000));

            cookieButton.click();
            page.waitForTimeout(500);
            System.out.println("Cookie banner accepted");

        } catch (Exception e) {
            System.out.println("No cookie banner to dismiss");
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (page != null) {
            page.close();
            page = null;
        }
        if (context != null) {
            context.close();
            context = null;
        }
        if (browser != null) {
            browser.close();
            browser = null;
        }
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}