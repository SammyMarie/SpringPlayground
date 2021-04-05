package com.sammy.service;

import com.sammy.entity.resource.TourPackageApi;

import java.util.List;

public interface TourPackageService {

    TourPackageApi createTourPackage(String code, String name);
    List<TourPackageApi> retrieveTourPackages();
    long total();
}
