package com.applns.bigdata.web.data.models.web;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"earliest_start_date",
"place_of_origin"
})
@Generated("jsonschema2pojo")
public class EarliestArtDatesPlaceOfOriginObj {

@JsonProperty("earliest_start_date")
private String earliestStartDate;
@JsonProperty("place_of_origin")
private String placeOfOrigin;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("earliest_start_date")
public String getEarliestStartDate() {
return earliestStartDate;
}

@JsonProperty("earliest_start_date")
public void setEarliestStartDate(String earliestStartDate) {
this.earliestStartDate = earliestStartDate;
}

@JsonProperty("place_of_origin")
public String getPlaceOfOrigin() {
return placeOfOrigin;
}

@JsonProperty("place_of_origin")
public void setPlaceOfOrigin(String placeOfOrigin) {
this.placeOfOrigin = placeOfOrigin;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}