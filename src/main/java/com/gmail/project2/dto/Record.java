
package com.gmail.project2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "eventVersion",
    "eventSource",
    "awsRegion",
    "eventTime",
    "eventName",
    "userIdentity",
    "requestParameters",
    "responseElements",
    "s3"
})
public class Record {

    @JsonProperty("eventVersion")
    private String eventVersion;
    @JsonProperty("eventSource")
    private String eventSource;
    @JsonProperty("awsRegion")
    private String awsRegion;
    @JsonProperty("eventTime")
    private String eventTime;
    @JsonProperty("eventName")
    private String eventName;
    @JsonProperty("userIdentity")
    private UserIdentity userIdentity;
    @JsonProperty("requestParameters")
    private RequestParameters requestParameters;
    @JsonProperty("responseElements")
    private ResponseElements responseElements;
    @JsonProperty("s3")
    private S3 s3;

    @JsonProperty("eventVersion")
    public String getEventVersion() {
        return eventVersion;
    }

    @JsonProperty("eventVersion")
    public void setEventVersion(String eventVersion) {
        this.eventVersion = eventVersion;
    }

    @JsonProperty("eventSource")
    public String getEventSource() {
        return eventSource;
    }

    @JsonProperty("eventSource")
    public void setEventSource(String eventSource) {
        this.eventSource = eventSource;
    }

    @JsonProperty("awsRegion")
    public String getAwsRegion() {
        return awsRegion;
    }

    @JsonProperty("awsRegion")
    public void setAwsRegion(String awsRegion) {
        this.awsRegion = awsRegion;
    }

    @JsonProperty("eventTime")
    public String getEventTime() {
        return eventTime;
    }

    @JsonProperty("eventTime")
    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    @JsonProperty("eventName")
    public String getEventName() {
        return eventName;
    }

    @JsonProperty("eventName")
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @JsonProperty("userIdentity")
    public UserIdentity getUserIdentity() {
        return userIdentity;
    }

    @JsonProperty("userIdentity")
    public void setUserIdentity(UserIdentity userIdentity) {
        this.userIdentity = userIdentity;
    }

    @JsonProperty("requestParameters")
    public RequestParameters getRequestParameters() {
        return requestParameters;
    }

    @JsonProperty("requestParameters")
    public void setRequestParameters(RequestParameters requestParameters) {
        this.requestParameters = requestParameters;
    }

    @JsonProperty("responseElements")
    public ResponseElements getResponseElements() {
        return responseElements;
    }

    @JsonProperty("responseElements")
    public void setResponseElements(ResponseElements responseElements) {
        this.responseElements = responseElements;
    }

    @JsonProperty("s3")
    public S3 getS3() {
        return s3;
    }

    @JsonProperty("s3")
    public void setS3(S3 s3) {
        this.s3 = s3;
    }

}
