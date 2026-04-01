package ge.tbc.testautomation.tbcbankapp.api.data.models.response.exchangerates;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommercialRatesResponse {

    @JsonProperty("base")
    private String base;

    @JsonProperty("commercialRatesList")
    @JsonAlias("commercialRates")
    private List<CommercialRate> rates;
}