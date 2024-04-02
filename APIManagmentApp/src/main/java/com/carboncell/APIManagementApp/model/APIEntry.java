package com.carboncell.APIManagementApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class APIEntry {
    @JsonProperty("API")
    private String API;
    @JsonProperty("Description")
    private String Description;
    @JsonProperty("Auth")
    private String Auth;
    @JsonProperty("HTTPS")
    private String HTTPS;
    @JsonProperty("Cors")
    private String Cors;
    @JsonProperty("Link")
    private String Link;
    @JsonProperty("Category")
    private String Category;
}
