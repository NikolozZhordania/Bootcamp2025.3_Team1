package ge.tbc.testautomation.tbcbankapp.api.data.models.response.tbcid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JwksResponse {

    @JsonProperty("keys")
    private List<JwkKey> keys;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class JwkKey {

        @JsonProperty("kty")
        private String kty;

        @JsonProperty("use")
        private String use;

        @JsonProperty("kid")
        private String kid;

        @JsonProperty("alg")
        private String alg;

        @JsonProperty("n")
        private String n;

        @JsonProperty("e")
        private String e;
    }
}