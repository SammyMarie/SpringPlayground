package com.sammy.entity.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourApi {

    private Integer tourId;

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

    @JsonProperty("packageType")
    private String tourPackageName;

    @JsonProperty("difficulty")
    private String difficulty;

    @JsonProperty("region")
    private String region;
}
