package ge.tbc.testautomation.tbcbankapp.api.data.models.response.financialReports;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportItem {

    @JsonProperty("$id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("year")
    private String year;

    @JsonProperty("isArchived")
    private boolean isArchived;

    @JsonProperty("file1")
    private ReportFile file1;

    @JsonProperty("file2")
    private ReportFile file2;
}
