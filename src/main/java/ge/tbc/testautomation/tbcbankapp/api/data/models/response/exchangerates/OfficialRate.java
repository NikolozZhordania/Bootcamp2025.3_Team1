package ge.tbc.testautomation.tbcbankapp.api.data.models.response.exchangerates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficialRate {

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("value")
    private Double value;
}