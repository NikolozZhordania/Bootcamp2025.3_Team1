package ge.tbc.testautomation.tbcbankapp.db.models;

import lombok.Getter;

public class Location {
    @Getter
    private int id;
    @Getter
    private String name;
    @Getter
    private String type;
    private String address;
    @Getter
    private String regionName;
    private boolean worksallday;

    public Location(boolean worksallday) {
        this.worksallday = worksallday;
    }

    public String getAddress() { return address; }

}