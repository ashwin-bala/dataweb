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
"artwork_type_title",
"num_rows"
})
@Generated("jsonschema2pojo")
public class ArtWorkTypeTitlesObj {

@JsonProperty("artwork_type_title")
private String artworkTypeTitle;
@JsonProperty("num_rows")
private String numRows;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("artwork_type_title")
public String getArtworkTypeTitle() {
return artworkTypeTitle;
}

@JsonProperty("artwork_type_title")
public void setArtworkTypeTitle(String artworkTypeTitle) {
this.artworkTypeTitle = artworkTypeTitle;
}

@JsonProperty("num_rows")
public String getNumRows() {
return numRows;
}

@JsonProperty("num_rows")
public void setNumRows(String numRows) {
this.numRows = numRows;
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