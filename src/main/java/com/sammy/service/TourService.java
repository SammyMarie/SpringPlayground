package com.sammy.service;

import com.sammy.model.resource.TourApi;

public interface TourService {

    TourApi createTour(TourApi tourApi);
    TourApi retrieveTour(String tourId);
    long total();
}
