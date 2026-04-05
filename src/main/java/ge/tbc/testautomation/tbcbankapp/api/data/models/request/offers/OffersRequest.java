package ge.tbc.testautomation.tbcbankapp.api.data.models.request.offers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OffersRequest {

    @JsonProperty("filter")
    private List<String> filter;

    @JsonProperty("locale")
    private String locale;

    @JsonProperty("segment")
    private String segment;

    @JsonProperty("pageIndex")
    private int pageIndex;

    @JsonProperty("pageSize")
    private int pageSize;
}
