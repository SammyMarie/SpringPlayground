package com.sammy.entity.resource;

import com.sammy.entity.business.TourPackage;
import com.sammy.entity.enums.Difficulty;
import com.sammy.entity.enums.Region;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourApi {

    private Integer tourId;

    private String title;

    private String description;

    private String blurb;

    private BigDecimal price;

    private String duration;

    private String bullets;

    private String keywords;

    private TourPackage tourPackage;

    private Difficulty difficulty;

    private Region region;
}
