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
"popularity",
"style_title"
})
@Generated("jsonschema2pojo")
public class PopularStyleTitlesObj {

@JsonProperty("popularity")
private String popularity;
@JsonProperty("style_title")
private String styleTitle;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("popularity")
public String getPopularity() {
return popularity;
}

@JsonProperty("popularity")
public void setPopularity(String popularity) {
this.popularity = popularity;
}

@JsonProperty("style_title")
public String getStyleTitle() {
return styleTitle;
}

@JsonProperty("style_title")
public void setStyleTitle(String styleTitle) {
this.styleTitle = styleTitle;
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