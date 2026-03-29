package ge.tbc.testautomation.tbcbankapp.ui.data;


import org.testng.annotations.DataProvider;

public class DivisionData {

    @DataProvider(name = "DistrictNames")
    public Object[][] districtDataProvider() {
        return new Object[][] {
                {"ნაძალადევი"},
                {"ისანი"}
        };
    }
}
