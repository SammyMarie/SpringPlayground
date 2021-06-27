package com.sammy.service;

import com.sammy.model.resource.RatingApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface TourRatingService {
    RatingApi createTourRating(String tourId, RatingApi ratingApi);
    List<RatingApi> retrieveAllRatings(String tourId);
    Page<RatingApi> retrieveAllRatingsPaging(String tourId, Pageable pageable);
    Map<String, Double> findAverage(String tourId);
    RatingApi updateRating(String tourId, RatingApi ratingApi);
    RatingApi patchRating(String tourId, RatingApi ratingApi);
    void removeRating(String tourId, int customerId);
}
