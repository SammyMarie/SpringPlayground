package com.sammy.service.impl;

import com.sammy.model.business.Tour;
import com.sammy.model.mappers.TourMapper;
import com.sammy.model.resource.TourApi;
import com.sammy.respository.TourPackageRepository;
import com.sammy.respository.TourRepository;
import com.sammy.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static com.sammy.model.mappers.TourMapper.toApi;
import static com.sammy.model.mappers.TourMapper.toBusiness;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final TourPackageRepository packageRepository;

    @Override
    public TourApi createTour(TourApi tourApi) {

        var tourPackage = packageRepository.findByName(tourApi.getTourPackageName())
                                                   .orElseThrow(() -> new RuntimeException("Tour Package does not exist "
                                                                                           + tourApi.getTourPackageName()));

        var tour = toBusiness(tourApi, tourPackage);
        return toApi(tourRepository.save(tour));
    }

    @Override
    public TourApi retrieveTour(String tourId) {
        return tourRepository.findById(tourId)
                             .map(TourMapper::toApi)
                             .orElseThrow(() -> new NoSuchElementException("Tour does not exist " + tourId));
    }

    @Override
    public long total() {
        return tourRepository.count();
    }
}
