package com.sammy.respository;

import com.sammy.model.business.TourRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends MongoRepository<TourRating, String> {

    List<TourRating> findByTourId(String tourId);
    Page<TourRating> findByTourId(String tourId, Pageable pageable);
    Optional<TourRating> findByTourIdAndCustomerId(String tourId, int customerId);
}