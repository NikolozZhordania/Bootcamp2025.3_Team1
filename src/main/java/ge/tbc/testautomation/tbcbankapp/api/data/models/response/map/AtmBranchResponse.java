package ge.tbc.testautomation.tbcbankapp.api.data.models.response.map;

import java.util.List;

public class AtmBranchResponse {

    private Integer id;
    private String address;
    private List<String> currencies;
    private Integer distance;
    private Double latitude;
    private String location;
    private Double longitude;
    private String name;
    private String placement;
    private String regionName;
    private String type;
    private List<WorkHour> workHours;
    private Boolean worksallday;

    public Integer getId()
    {
        return id;
    }

    public String getAddress()
    {
        return address;
    }

    public List<String> getCurrencies()
    {
        return currencies;
    }

    public Integer getDistance()
    {
        return distance;
    }

    public Double getLatitude()
    {
        return latitude;
    }

    public String getLocation()
    {
        return location;
    }

    public Double getLongitude()
    {
        return longitude;
    }

    public String getName()
    {
        return name;
    }

    public String getPlacement()
    {
        return placement;
    }

    public String getRegionName()
    {
        return regionName;
    }

    public String getType()
    {
        return type;
    }

    public List<WorkHour> getWorkHours()
    {
        return workHours;
    }

    public Boolean getWorksallday()
    {
        return worksallday;
    }
}