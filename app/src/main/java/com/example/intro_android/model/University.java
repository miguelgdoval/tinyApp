package com.example.intro_android.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

@SuppressLint("ParcelCreator")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "alpha_two_code",
        "domains",
        "country",
        "web_pages",
        "state-province"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class University implements Serializable, Parcelable {

    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("alpha_two_code")
    private String alphaTwoCode;
    @JsonProperty("domains")
    private List<String> domains = null;
    @JsonProperty("country")
    private String country;
    @JsonProperty("web_pages")
    private List<String> webPages = null;
    @JsonProperty("state-province")
    private String stateProvince;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAlphaTwoCode() {
        return alphaTwoCode;
    }

    public void setAlphaTwoCode(String alphaTwoCode) {
        this.alphaTwoCode = alphaTwoCode;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public List<String> getWebPages() {
        return webPages;
    }

    public void setWebPages(List<String> webPages) {
        this.webPages = webPages;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public University() {}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}