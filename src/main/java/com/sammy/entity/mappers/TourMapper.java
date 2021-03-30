package com.sammy.entity.mappers;

import com.sammy.entity.business.Tour;
import com.sammy.entity.resource.TourApi;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class TourMapper {

    public static Tour toBusiness(TourApi tourApi) {
        return Tour.builder()
                   .tourId(tourApi.getTourId())
                   .title(tourApi.getTitle())
                   .description(tourApi.getDescription())
                   .blurb(tourApi.getBlurb())
                   .price(tourApi.getPrice())
                   .duration(tourApi.getDuration())
                   .bullets(tourApi.getBullets())
                   .keywords(tourApi.getKeywords())
                   .tourPackage(tourApi.getTourPackage())
                   .difficulty(tourApi.getDifficulty())
                   .region(tourApi.getRegion())
                   .build();
    }

    public static TourApi toApi(Tour tour) {
        return TourApi.builder()
                      .tourId(tour.getTourId())
                      .title(tour.getTitle())
                      .description(tour.getDescription())
                      .blurb(tour.getBlurb())
                      .price(tour.getPrice())
                      .duration(tour.getDuration())
                      .bullets(tour.getBullets())
                      .keywords(tour.getKeywords())
                      .tourPackage(tour.getTourPackage())
                      .difficulty(tour.getDifficulty())
                      .region(tour.getRegion())
                      .build();
    }
}
