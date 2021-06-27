package com.sammy.model.mappers;

import com.sammy.model.business.TourPackage;
import com.sammy.model.resource.TourPackageApi;

public final class TourPackageMapper {

    public static TourPackage toBusiness(TourPackageApi apiTourPackage) {
        return TourPackage.builder()
                          .code(apiTourPackage.getCode())
                          .name(apiTourPackage.getName())
                          .build();
    }

    public static TourPackageApi toApi(TourPackage businessTourPackage) {
        return TourPackageApi.builder()
                             .code(businessTourPackage.getCode())
                             .name(businessTourPackage.getName())
                             .build();
    }
}
