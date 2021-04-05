package com.sammy.service;

import com.sammy.entity.business.Tour;
import com.sammy.entity.business.TourPackage;
import com.sammy.entity.resource.TourApi;
import com.sammy.respository.TourPackageRepository;
import com.sammy.respository.TourRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.sammy.entity.mappers.TourMapper.toApi;
import static com.sammy.entity.mappers.TourMapper.toBusiness;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final TourPackageRepository packageRepository;

    @Override
    public TourApi createTour(TourApi tourApi) {

        TourPackage tourPackage = packageRepository.findByName(tourApi.getTourPackageName())
                                                   .orElseThrow(() -> new RuntimeException("Tour Package does not exist "
                                                                                           + tourApi.getTourPackageName()));

        Tour tour = toBusiness(tourApi, tourPackage);
        return toApi(tourRepository.save(tour));
    }

    @Override
    public long total() {
        return tourRepository.count();
    }
}
