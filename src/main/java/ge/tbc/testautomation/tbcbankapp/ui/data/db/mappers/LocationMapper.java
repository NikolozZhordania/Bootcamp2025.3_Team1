package ge.tbc.testautomation.tbcbankapp.ui.data.db.mappers;

import ge.tbc.testautomation.tbcbankapp.ui.data.db.models.Location;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface LocationMapper {
    @Select("SELECT * FROM tbc_locations")
    List<Location> getAllLocations();

    @Select("SELECT * FROM tbc_locations WHERE regionName = #{region}")
    List<Location> getLocationsByRegion(String region);

    @Select("SELECT * FROM tbc_locations LIMIT 5")
    List<Location> getFirstFiveLocations();
}