package com.sammy.service;

import com.sammy.entity.resource.RatingApi;

public interface TourRatingService {
    RatingApi createTourRating(int tourId, RatingApi ratingApi);
}
