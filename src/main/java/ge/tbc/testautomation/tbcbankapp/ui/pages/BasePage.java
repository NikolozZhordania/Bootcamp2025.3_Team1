package ge.tbc.testautomation.tbcbankapp.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BasePage {

    public Locator
            denyCookieBtn
                    ;


    public BasePage(Page page) {

        this.denyCookieBtn = page.locator("app-cookie-consent").getByText("უარყოფა");


    }
}