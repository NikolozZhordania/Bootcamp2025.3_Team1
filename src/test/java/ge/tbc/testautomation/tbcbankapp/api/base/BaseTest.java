package ge.tbc.testautomation.tbcbankapp.api.base;

import ge.tbc.testautomation.tbcbankapp.api.steps.ExchangeRatesSteps;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected ExchangeRatesSteps exchangeRatesSteps;

    @BeforeClass
    public void setup() {
        exchangeRatesSteps = new ExchangeRatesSteps();
    }
}
