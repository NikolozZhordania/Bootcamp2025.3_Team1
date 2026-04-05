package ge.tbc.testautomation.tbcbankapp.api.base;

import ge.tbc.testautomation.tbcbankapp.api.steps.ExchangeRatesSteps;
import ge.tbc.testautomation.tbcbankapp.api.steps.FinancialReportsSteps;
import ge.tbc.testautomation.tbcbankapp.api.steps.OffersSteps;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected ExchangeRatesSteps exchangeRatesSteps;
    protected OffersSteps offersSteps;
    protected FinancialReportsSteps financialReportsSteps;

    @BeforeClass
    public void setup() {
        exchangeRatesSteps = new ExchangeRatesSteps();
        offersSteps = new OffersSteps();
        financialReportsSteps = new FinancialReportsSteps();
    }
}
