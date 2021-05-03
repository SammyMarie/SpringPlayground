package com.sammy.service;

import com.sammy.entity.resource.RatingApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface TourRatingService {
    RatingApi createTourRating(int tourId, RatingApi ratingApi);
    List<RatingApi> retrieveAllRatings(int tourId);
    Page<RatingApi> retrieveAllRatingsPaging(int tourId, Pageable pageable);
    Map<String, Double> findAverage(int tourId);
    RatingApi updateRating(int tourId, RatingApi ratingApi);
    RatingApi patchRating(int tourId, RatingApi ratingApi);
    void removeRating(int tourId, int customerId);
}
