package ge.tbc.testautomation.tbcbankapp.api.data.models.response.financialReports;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportFile {

    @JsonProperty("src")
    private String src;

    @JsonProperty("alt")
    private String alt;
}
