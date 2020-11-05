
package com.gmail.project2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "ownerIdentity",
    "arn"
})
public class Bucket {

    @JsonProperty("name")
    private String name;
    @JsonProperty("ownerIdentity")
    private OwnerIdentity ownerIdentity;
    @JsonProperty("arn")
    private String arn;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("ownerIdentity")
    public OwnerIdentity getOwnerIdentity() {
        return ownerIdentity;
    }

    @JsonProperty("ownerIdentity")
    public void setOwnerIdentity(OwnerIdentity ownerIdentity) {
        this.ownerIdentity = ownerIdentity;
    }

    @JsonProperty("arn")
    public String getArn() {
        return arn;
    }

    @JsonProperty("arn")
    public void setArn(String arn) {
        this.arn = arn;
    }

}
