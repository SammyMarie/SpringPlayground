package com.sammy.model.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourApi {

    private String tourId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("blurb")
    private String blurb;

    @JsonProperty("price")
    private String price;

    @JsonProperty("length")
    private String duration;

    @JsonProperty("bullets")
    private String bullets;

    @JsonProperty("keywords")
    private String keywords;

    private String tourPackageCode;

    @JsonProperty("packageType")
    private String tourPackageName;

    private Map<String, String> details;
}
