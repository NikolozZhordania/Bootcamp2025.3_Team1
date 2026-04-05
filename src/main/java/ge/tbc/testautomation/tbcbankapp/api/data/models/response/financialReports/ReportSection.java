package ge.tbc.testautomation.tbcbankapp.api.data.models.response.financialReports;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportSection {

    @JsonProperty("reports")
    private List<ReportItem> reports;

    @JsonProperty("title")
    private String title;
}
