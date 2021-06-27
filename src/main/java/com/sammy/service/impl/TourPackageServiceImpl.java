package com.sammy.service.impl;

import com.sammy.model.business.TourPackage;
import com.sammy.model.mappers.TourPackageMapper;
import com.sammy.model.resource.TourPackageApi;
import com.sammy.respository.TourPackageRepository;
import com.sammy.service.TourPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.sammy.model.mappers.TourPackageMapper.toApi;

@Service
@RequiredArgsConstructor
public class TourPackageServiceImpl implements TourPackageService {

    private final TourPackageRepository packageRepository;

    @Override
    public TourPackageApi createTourPackage(String code, String name) {
        return toApi(packageRepository.findById(code)
                                .orElse(packageRepository.save(TourPackage.builder().code(code).name(name).build())));
    }

    @Override
    public List<TourPackageApi> retrieveTourPackages() {
        return packageRepository.findAll().stream()
                                .map(TourPackageMapper::toApi)
                                .collect(Collectors.toList());
    }

    @Override
    public long total() {
        return packageRepository.count();
    }
}
