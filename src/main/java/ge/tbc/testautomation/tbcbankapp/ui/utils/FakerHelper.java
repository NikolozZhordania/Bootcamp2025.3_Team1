package ge.tbc.testautomation.tbcbankapp.ui.utils;

import com.github.javafaker.Faker;

public class FakerHelper {

    private static final Faker faker = new Faker();

    public static String randomStreetName() {
        return faker.address().streetName();
    }

    public static String randomCityName() {
        return faker.address().city();
    }

    public static String randomFullAddress() {
        return faker.address().fullAddress();
    }

    public static String randomString(int length) {
        return faker.lorem().characters(length, true, false);
    }
}
