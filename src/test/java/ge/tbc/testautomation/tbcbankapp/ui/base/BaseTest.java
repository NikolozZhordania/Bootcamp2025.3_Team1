package ge.tbc.testautomation.tbcbankapp.ui.base;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.tbcbankapp.base.DBSetUp;
import ge.tbc.testautomation.tbcbankapp.ui.data.GeoLocationEnum;
import ge.tbc.testautomation.tbcbankapp.ui.steps.*;
import ge.tbc.testautomation.tbcbankapp.ui.utils.DeviceType;
import ge.tbc.testautomation.tbcbankapp.ui.utils.GeoLocationRandomizer;
import ge.tbc.testautomation.tbcbankapp.ui.utils.NeedsCookieHandling;
import ge.tbc.testautomation.tbcbankapp.ui.utils.TestContext;
import org.testng.annotations.*;

import java.util.Random;

public class BaseTest extends DBSetUp {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected double[] currentLocation;
    protected GeoLocationEnum currentDistrict;

    protected HomeSteps homeSteps;
    protected LocationSteps locationSteps;
    protected BaseSteps baseSteps;

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
        homeSteps.openHomepage();
    }

    private void createContextAndPage(DeviceType deviceType) {
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();

        if (deviceType == DeviceType.MOBILE) {
            contextOptions.setViewportSize(390, 844);
            contextOptions.setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 15_0 like Mac OS X)");
        } else {
            contextOptions.setViewportSize(null);
        }

        GeoLocationEnum[] districts = GeoLocationEnum.values();
        currentDistrict = districts[new Random().nextInt(districts.length)];

        currentLocation = GeoLocationRandomizer.getRandomFromDistrict(currentDistrict);

        contextOptions
                .setGeolocation(currentLocation[0], currentLocation[1])
                .setPermissions(java.util.List.of("geolocation"));

        context = browser.newContext(contextOptions);
        page = context.newPage();
    }

    private void initSteps() {
        homeSteps = new HomeSteps(page);
        locationSteps = new LocationSteps(page);
        baseSteps = new BaseSteps(page);
    }

    @BeforeMethod(alwaysRun = true)
    public void cookieEater() {
        if (this instanceof NeedsCookieHandling && baseSteps != null) {
            baseSteps.waitForPageToLoad()
                    .rejectCookieIfExists()
                    .assertCookieRejected();
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