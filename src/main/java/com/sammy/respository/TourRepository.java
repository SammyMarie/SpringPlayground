package com.sammy.respository;

import com.sammy.model.business.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends MongoRepository<Tour, String> {

    @Query(value = "{'tourPackageCode': ?0}",
           fields = "{'tourId': 1, 'title': 1, 'tourPackageCode': 1, 'tourPackageName': 1}")
    Page<Tour> findSummaryByTourPackageCode(@Param("code") String tourPackageCode, Pageable pageable);

    @Override
    @RestResource(exported = false)
    <S extends Tour> List<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    <S extends Tour> S save(S tour);

    @Override
    @RestResource(exported = false)
    void deleteById(String tourId);

    @Override
    @RestResource(exported = false)
    void delete(Tour tour);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Tour> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
