package com.sammy.respository;

import com.sammy.entity.business.TourRating;
import com.sammy.entity.business.TourRatingPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends JpaRepository<TourRating, TourRatingPk> {

    List<TourRating> findByRatingPkTourTourId(int tourId);
    Page<TourRating> findByRatingPkTourTourId(int tourId, Pageable pageable);
    Optional<TourRating> findByRatingPkTourTourIdAndRatingPkCustomerId(int tourId, int customerId);
}