
package com.gmail.project2.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Records"
})
public class S3Obj {

    @JsonProperty("Records")
    private List<Record> records = null;

    @JsonProperty("Records")
    public List<Record> getRecords() {
        return records;
    }

    @JsonProperty("Records")
    public void setRecords(List<Record> records) {
        this.records = records;
    }

}
