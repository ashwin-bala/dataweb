package com.applns.bigdata.web.data.models.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "response" })
public class EarliestArtDatesPlaceOfOriginResponse {
	@JsonProperty("response")
	private List<EarliestArtDatesPlaceOfOriginObj> response;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("response")
	public List<EarliestArtDatesPlaceOfOriginObj> getResponse() {
		return response;
	}

	@JsonProperty("response")
	public void setResponse(List<EarliestArtDatesPlaceOfOriginObj> response) {
		this.response = response;
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
