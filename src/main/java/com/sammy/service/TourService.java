package com.sammy.service;

import com.sammy.entity.resource.TourApi;

public interface TourService {

    TourApi createTour(TourApi tourApi);
    TourApi retrieveTour(int tourId);
    long total();
}
