package com.sammy.entity.mappers;

import com.sammy.entity.business.Tour;
import com.sammy.entity.business.TourPackage;
import com.sammy.entity.enums.Difficulty;
import com.sammy.entity.enums.Region;
import com.sammy.entity.resource.TourApi;
import com.sammy.entity.resource.TourPackageApi;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class TourMapper {

    public static Tour toBusiness(TourApi tourApi, TourPackage tourPackage) {

        TourPackage packagedTour = Optional.ofNullable(tourPackage)
                                           .orElseGet(() -> TourPackageMapper.toBusiness(TourPackageApi.builder()
                                                                                                       .name(tourApi.getTourPackageName())
                                                                                                       .build()));

        return Tour.builder()
                   .tourId(tourApi.getTourId())
                   .title(tourApi.getTitle())
                   .description(tourApi.getDescription())
                   .blurb(tourApi.getBlurb())
                   .price(new BigDecimal(tourApi.getPrice()))
                   .duration(tourApi.getDuration())
                   .bullets(tourApi.getBullets())
                   .keywords(tourApi.getKeywords())
                   .tourPackage(packagedTour)
                   .difficulty(Difficulty.valueOf(tourApi.getDifficulty()))
                   .region(Region.findByLabel(tourApi.getRegion()))
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
                      .tourPackageName(tour.getTourPackage().getName())
                      .difficulty(tour.getDifficulty().name())
                      .region(tour.getRegion().name())
                      .build();
    }
}
