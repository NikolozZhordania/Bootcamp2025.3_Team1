package ge.tbc.testautomation.tbcbankapp.ui.utils;


import java.math.BigDecimal;


import static org.testng.Assert.assertTrue;

public class CurrencyExchangeHelper {

    public static BigDecimal extractNumber(String text) {
        String cleaned = text.replaceAll("[^0-9.]", "");
        return new BigDecimal(cleaned);
    }

    public static void verifyConversion(BigDecimal input, BigDecimal output) {
        assertTrue(input.compareTo(BigDecimal.ZERO) > 0,
                "Input amount must be positive");
        assertTrue(output.compareTo(BigDecimal.ZERO) > 0,
                "Output amount must be positive");
    }
}