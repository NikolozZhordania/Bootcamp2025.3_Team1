package ge.tbc.testautomation.tbcbankapp.ui.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.tbcbankapp.ui.pages.CurrencyExchangePage;
import ge.tbc.testautomation.tbcbankapp.ui.utils.CurrencyExchangeHelper;
import io.qameta.allure.Step;

import java.math.BigDecimal;

public class CurrencyExchangeSteps {

    private final CurrencyExchangePage currencyExchangePage;

    public CurrencyExchangeSteps(Page page) {
        this.currencyExchangePage = new CurrencyExchangePage(page);
    }

    @Step("Fill in transfer amount: '{amount}'")
    public CurrencyExchangeSteps inputMoney(String amount) {
        currencyExchangePage.moneyInput.scrollIntoViewIfNeeded();
        currencyExchangePage.moneyInput.fill(amount);
        return this;
    }

    @Step("Select source currency from dropdown: '{currency}'")
    public CurrencyExchangeSteps inputCurrency(String currency) {
        currencyExchangePage.inputCurrencyDropdown.scrollIntoViewIfNeeded();
        currencyExchangePage.inputCurrencyDropdown.click();
        Locator option = currencyExchangePage.currencyDropdown
                .filter(new Locator.FilterOptions().setHasText(currency));
        option.scrollIntoViewIfNeeded();
        option.click();
        return this;
    }

    @Step("Select target currency from dropdown: '{currency}'")
    public CurrencyExchangeSteps outputCurrency(String currency) {
        currencyExchangePage.outputCurrencyDropdown.scrollIntoViewIfNeeded();
        currencyExchangePage.outputCurrencyDropdown.click();
        Locator option = currencyExchangePage.currencyDropdown
                .filter(new Locator.FilterOptions().setHasText(currency));
        option.scrollIntoViewIfNeeded();
        option.click();
        return this;
    }

    @Step("Assert converted output amount is greater than zero and mathematically valid")
    public CurrencyExchangeSteps verifyConversion() {
        BigDecimal input = CurrencyExchangeHelper.extractNumber(currencyExchangePage.moneyInput.inputValue());
        BigDecimal output = CurrencyExchangeHelper.extractNumber(currencyExchangePage.moneyOutput.inputValue());
        CurrencyExchangeHelper.verifyConversion(input, output);
        return this;
    }
}