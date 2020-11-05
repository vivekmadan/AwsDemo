
package com.gmail.project2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sourceIPAddress"
})
public class RequestParameters {

    @JsonProperty("sourceIPAddress")
    private String sourceIPAddress;

    @JsonProperty("sourceIPAddress")
    public String getSourceIPAddress() {
        return sourceIPAddress;
    }

    @JsonProperty("sourceIPAddress")
    public void setSourceIPAddress(String sourceIPAddress) {
        this.sourceIPAddress = sourceIPAddress;
    }

}
