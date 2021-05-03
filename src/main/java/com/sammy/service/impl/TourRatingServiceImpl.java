package com.sammy.service.impl;

import com.sammy.entity.business.Tour;
import com.sammy.entity.business.TourRating;
import com.sammy.entity.business.TourRatingPk;
import com.sammy.entity.resource.RatingApi;
import com.sammy.respository.TourRatingRepository;
import com.sammy.service.TourRatingService;
import com.sammy.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static com.sammy.entity.mappers.TourMapper.toBusiness;

@Service
@RequiredArgsConstructor
public class TourRatingServiceImpl implements TourRatingService {

    private final TourRatingRepository ratingRepository;
    private final TourService tourService;

    public RatingApi createTourRating(int tourId, RatingApi ratingApi){

        var tour = verifyTour(tourId);

        TourRatingPk ratingPk = TourRatingPk.builder()
                                            .tour(tour)
                                            .customerId(ratingApi.getCustomerId())
                                            .build();

        var tourRating = TourRating.builder()
                                          .ratingPk(ratingPk)
                                          .score(ratingApi.getScore())
                                          .comment(ratingApi.getComment())
                                          .build();

        var convertToApi = ratingRepository.save(tourRating);

        return RatingApi.builder()
                        .score(convertToApi.getScore())
                        .comment(convertToApi.getComment())
                        .customerId(convertToApi.getRatingPk().getCustomerId())
                        .build();
    }

    private Tour verifyTour(int tourId) throws NoSuchElementException {
        return toBusiness(tourService.retrieveTour(tourId), null);
    }
}
