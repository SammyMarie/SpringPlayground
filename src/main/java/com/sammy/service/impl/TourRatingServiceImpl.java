package com.sammy.service.impl;

import com.sammy.entity.business.Tour;
import com.sammy.entity.business.TourRating;
import com.sammy.entity.business.TourRatingPk;
import com.sammy.entity.resource.RatingApi;
import com.sammy.respository.TourRatingRepository;
import com.sammy.service.TourRatingService;
import com.sammy.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.sammy.entity.mappers.TourMapper.toBusiness;

@Service
@RequiredArgsConstructor
public class TourRatingServiceImpl implements TourRatingService {

    private final TourRatingRepository ratingRepository;
    private final TourService tourService;

    @Override
    public RatingApi createTourRating(int tourId, RatingApi ratingApi) {

        var tour = verifyTour(tourId);

        TourRatingPk ratingPk = TourRatingPk.builder()
                                            .tour(tour)
                                            .customerId(ratingApi.getCustomerId())
                                            .build();

        var tourRating = TourRating.builder()
                                   .ratingPk(ratingPk)
                                   .score(ratingApi.getScore())
                                   .comment(ratingApi.getComment())
                                   .build();

        var convertToApi = ratingRepository.save(tourRating);

        return buildRatingApi(convertToApi);
    }

    @Override
    public List<RatingApi> retrieveAllRatings(int tourId) {

        return ratingRepository.findByRatingPkTourTourId(verifyTour(tourId).getTourId())
                               .stream().map(this::buildRatingApi)
                               .collect(Collectors.toList());
    }

    @Override
    public Page<RatingApi> retrieveAllRatingsPaging(int tourId, Pageable pageable) {

        Page<TourRating> ratings = ratingRepository.findByRatingPkTourTourId(verifyTour(tourId).getTourId(), pageable);

        return new PageImpl<>(
                ratings.get().map(this::buildRatingApi).collect(Collectors.toList()),
                pageable, ratings.getTotalElements()
        );
    }

    @Override
    public Map<String, Double> findAverage(int tourId) {
        return Map.of("average",
                      ratingRepository.findByRatingPkTourTourId(verifyTour(tourId).getTourId())
                                      .stream().mapToInt(TourRating::getScore).average()
                                      .orElseThrow(() -> new NoSuchElementException("Tour has no ratings")));
    }

    @Override
    public RatingApi updateRating(int tourId, RatingApi ratingApi){
        var tourRating = verifyTourRating(tourId, ratingApi.getCustomerId());
        tourRating.setScore(ratingApi.getScore());
        tourRating.setComment(ratingApi.getComment());

        return buildRatingApi(ratingRepository.save(tourRating));
    }

    @Override
    public RatingApi patchRating(int tourId, RatingApi ratingApi){
        var tourRating = verifyTourRating(tourId, ratingApi.getCustomerId());
        tourRating.setScore(ratingApi.getScore());
        tourRating.setComment(!ratingApi.getComment().isBlank() ? ratingApi.getComment() : "Comment not provided!");

        return buildRatingApi(ratingRepository.save(tourRating));
    }

    @Override
    public void removeRating(int tourId, int customerId){
        var tourRating = verifyTourRating(tourId, customerId);
        ratingRepository.delete(tourRating);
    }

    private TourRating verifyTourRating(int tourId, int customerId) {
        return ratingRepository.findByRatingPkTourTourIdAndRatingPkCustomerId(tourId, customerId)
                               .orElseThrow(() -> new NoSuchElementException("Tour-Rating pair for request( " + tourId
                                                                             + " for customer " + customerId));
    }

    private RatingApi buildRatingApi(TourRating convertToApi) {
        return RatingApi.builder()
                        .score(convertToApi.getScore())
                        .comment(convertToApi.getComment())
                        .customerId(convertToApi.getRatingPk().getCustomerId())
                        .build();
    }

    private Tour verifyTour(int tourId) throws NoSuchElementException {
        return toBusiness(tourService.retrieveTour(tourId), null);
    }
}
