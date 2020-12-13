package co.id.menanga.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class ResponseAPI {
    @Setter
    @Getter
    @JsonProperty("ResponseCode")
    private String responseCode;

    @Setter
    @Getter
    @JsonProperty("ResposeMessage")
    private String responseMsg;

    public ResponseAPI(String rc, String message) {
        this.responseCode = rc;
        this.responseMsg = message;
    }
}
