package ge.tbc.testautomation.tbcbankapp.ui.data;

public enum GeoLocationEnum {
    VAKE            (41.7100, 41.7400, 44.7400, 44.7900),
    SABURTALO       (41.7300, 41.7600, 44.7600, 44.8100),
    ISANI           (41.6900, 41.7100, 44.8200, 44.8600),
    KRTSANISI       (41.6700, 41.6900, 44.7900, 44.8300),
    CHUGHURETI      (41.6900, 41.7100, 44.7900, 44.8200),
    DIDUBE          (41.7200, 41.7500, 44.7900, 44.8300),
    NADZALADEVI     (41.7300, 41.7600, 44.8200, 44.8700),
    SAMGORI         (41.6600, 41.7200, 44.8500, 44.9600),
    GLDANI          (41.7500, 41.8000, 44.8200, 44.8800),
    DIDI_DIGHOMI    (41.7400, 41.7700, 44.7200, 44.7600),
    MTATSMINDA      (41.6900, 41.7100, 44.7900, 44.8100),
    AVLABARI        (41.6900, 41.7000, 44.8100, 44.8300),
    ORTACHALA       (41.6700, 41.6900, 44.8200, 44.8500),
    VARKETILI       (41.6600, 41.6900, 44.8800, 44.9200),
    PONICHALA       (41.6300, 41.6700, 44.8500, 44.9000),
    NADZALADEVI_NTH (41.7600, 41.8000, 44.8200, 44.8700),
    DIGHOMI         (41.7700, 41.8300, 44.7200, 44.7800),
    MUKHIANI        (41.7800, 41.8300, 44.8200, 44.8800),
    LILO            (41.6800, 41.7200, 44.9200, 44.9800),
    TEMQA           (41.6900, 41.7200, 44.8600, 44.9200),
    OLD_TBILISI     (41.6800, 41.7000, 44.8000, 44.8200);

    public final double minLat, maxLat, minLng, maxLng;

    GeoLocationEnum(double minLat, double maxLat, double minLng, double maxLng) {
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLng = minLng;
        this.maxLng = maxLng;
    }
}