
package com.gmail.project2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "principalId"
})
public class UserIdentity {

    @JsonProperty("principalId")
    private String principalId;

    @JsonProperty("principalId")
    public String getPrincipalId() {
        return principalId;
    }

    @JsonProperty("principalId")
    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

}
