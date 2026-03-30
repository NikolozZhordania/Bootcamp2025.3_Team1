package ge.tbc.testautomation.tbcbankapp.ui.data;

import ge.tbc.testautomation.tbcbankapp.ui.utils.CurrencyDataGenerator;
import org.testng.annotations.DataProvider;

public class CurrencyExchangeDataProvider {

    @DataProvider(name = "transferData")
    public static Object[][] transferData() {
        String from;
        return new Object[][]{
                {
                        (from = CurrencyDataGenerator.randomCurrency()),
                        CurrencyDataGenerator.randomDifferentCurrency(from),
                        CurrencyDataGenerator.randomAmount(100, 200)
                },
                {
                        (from = CurrencyDataGenerator.randomCurrency()),
                        CurrencyDataGenerator.randomDifferentCurrency(from),
                        CurrencyDataGenerator.randomAmount(200, 300)
                },
                {
                        (from = CurrencyDataGenerator.randomCurrency()),
                        CurrencyDataGenerator.randomDifferentCurrency(from),
                        CurrencyDataGenerator.randomAmount(300, 400)
                },
                {
                        (from = CurrencyDataGenerator.randomCurrency()),
                        CurrencyDataGenerator.randomDifferentCurrency(from),
                        CurrencyDataGenerator.randomAmount(400, 500)
                }
        };
    }
}