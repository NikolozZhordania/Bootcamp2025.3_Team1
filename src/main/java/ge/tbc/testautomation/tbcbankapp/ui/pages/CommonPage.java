package ge.tbc.testautomation.tbcbankapp.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CommonPage {

    public Locator
            forMeButton,
            forMyBusinessButton,
            tbcButton,
            dropDownMenuContainer,
            burgerMenu,
            cookieAcceptButton,
            kebabMenuButton,
            locationsIcon;

    public CommonPage(Page page) {

        Locator banner = page.getByRole(AriaRole.BANNER);

        this.forMeButton = banner.getByRole(AriaRole.LINK)
                .filter(new Locator.FilterOptions().setHasText("ჩემთვის"))
                .first();

        this.forMyBusinessButton = banner.getByRole(AriaRole.LINK)
                .filter(new Locator.FilterOptions().setHasText("ჩემი ბიზნესისთვის"))
                .first();

        this.tbcButton = banner.getByRole(AriaRole.LINK)
                .filter(new Locator.FilterOptions().setHasText("თიბისი"))
                .first();

        this.dropDownMenuContainer = page.locator("//div[contains(@class,'megaMenuBackgroundAnimation')]")
                .first();

        this.burgerMenu = page.locator("button[class*='hamburger-menu']").first();

        this.cookieAcceptButton = page.getByRole(AriaRole.BUTTON)
                .filter(new Locator.FilterOptions().setHasText("თანხმობა"))
                .first();
        this.kebabMenuButton = page.locator("button:has(tbcx-icon:has-text('kebab-menu-vertical-outlined'))");
        this.locationsIcon = page.locator("button:has(tbcx-icon[style*='location-pin-outlined'])");
    }
}