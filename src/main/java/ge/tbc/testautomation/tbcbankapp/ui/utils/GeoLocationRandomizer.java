package ge.tbc.testautomation.tbcbankapp.ui.utils;

import ge.tbc.testautomation.tbcbankapp.ui.data.GeoLocationEnum;

import java.util.Random;

public class GeoLocationRandomizer {

    private static final Random random = new Random();

    public static String getDistrict(double lat, double lng) {
        for (GeoLocationEnum d : GeoLocationEnum.values()) {
            if (lat >= d.minLat && lat <= d.maxLat && lng >= d.minLng && lng <= d.maxLng) {
                return d.name();
            }
        }
        return "UNKNOWN";
    }

    public static double[] getRandom() {
        double lat = 41.63 + (random.nextDouble() * (41.83 - 41.63));
        double lng = 44.72 + (random.nextDouble() * (44.92 - 44.72));
        return new double[]{lat, lng};
    }

    public static double[] getRandomFromDistrict(GeoLocationEnum district) {
        double lat = district.minLat + (random.nextDouble() * (district.maxLat - district.minLat));
        double lng = district.minLng + (random.nextDouble() * (district.maxLng - district.minLng));
        return new double[]{lat, lng};
    }
}