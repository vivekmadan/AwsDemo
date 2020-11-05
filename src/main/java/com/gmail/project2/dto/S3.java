
package com.gmail.project2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "s3SchemaVersion",
    "configurationId",
    "bucket",
    "object"
})
public class S3 {

    @JsonProperty("s3SchemaVersion")
    private String s3SchemaVersion;
    @JsonProperty("configurationId")
    private String configurationId;
    @JsonProperty("bucket")
    private Bucket bucket;
    @JsonProperty("object")
    private Object object;

    @JsonProperty("s3SchemaVersion")
    public String getS3SchemaVersion() {
        return s3SchemaVersion;
    }

    @JsonProperty("s3SchemaVersion")
    public void setS3SchemaVersion(String s3SchemaVersion) {
        this.s3SchemaVersion = s3SchemaVersion;
    }

    @JsonProperty("configurationId")
    public String getConfigurationId() {
        return configurationId;
    }

    @JsonProperty("configurationId")
    public void setConfigurationId(String configurationId) {
        this.configurationId = configurationId;
    }

    @JsonProperty("bucket")
    public Bucket getBucket() {
        return bucket;
    }

    @JsonProperty("bucket")
    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    @JsonProperty("object")
    public Object getObject() {
        return object;
    }

    @JsonProperty("object")
    public void setObject(Object object) {
        this.object = object;
    }

}
