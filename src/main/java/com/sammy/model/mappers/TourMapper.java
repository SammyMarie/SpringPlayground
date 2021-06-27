package com.sammy.model.mappers;

import com.sammy.model.business.Tour;
import com.sammy.model.business.TourPackage;
import com.sammy.model.resource.TourApi;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class TourMapper {

    public static Tour toBusiness(TourApi tourApi, TourPackage tourPackage) {

        return Tour.builder()
                   .tourId(tourApi.getTourId())
                   .title(tourApi.getTitle())
                   .description(tourApi.getDescription())
                   .blurb(tourApi.getBlurb())
                   .price(new BigDecimal(tourApi.getPrice()))
                   .duration(tourApi.getDuration())
                   .bullets(tourApi.getBullets())
                   .keywords(tourApi.getKeywords())
                   .tourPackageCode(tourPackage.getCode())
                   .tourPackageName(tourApi.getTourPackageName())
                   .details(tourApi.getDetails())
                   .build();
    }

    public static TourApi toApi(Tour tour) {
        return TourApi.builder()
                      .tourId(tour.getTourId())
                      .title(tour.getTitle())
                      .description(tour.getDescription())
                      .blurb(tour.getBlurb())
                      .price(tour.getPrice().toPlainString())
                      .duration(tour.getDuration())
                      .bullets(tour.getBullets())
                      .keywords(tour.getKeywords())
                      .tourPackageCode(tour.getTourPackageCode())
                      .tourPackageName(tour.getTourPackageName())
                      .details(tour.getDetails())
                      .build();
    }
}
