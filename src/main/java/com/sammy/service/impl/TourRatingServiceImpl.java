package com.sammy.service.impl;

import com.sammy.model.business.Tour;
import com.sammy.model.business.TourPackage;
import com.sammy.model.business.TourRating;
import com.sammy.model.resource.RatingApi;
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

import static com.sammy.model.mappers.TourMapper.toBusiness;

@Service
@RequiredArgsConstructor
public class TourRatingServiceImpl implements TourRatingService {

    private final TourRatingRepository ratingRepository;
    private final TourService tourService;

    @Override
    public RatingApi createTourRating(String tourId, RatingApi ratingApi) {

        var tourRating = TourRating.builder()
                                   .tourId(ratingApi.getTourId())
                                   .customerId(ratingApi.getCustomerId())
                                   .score(ratingApi.getScore())
                                   .comment(ratingApi.getComment())
                                   .build();

        var convertToApi = ratingRepository.save(tourRating);

        return buildRatingApi(convertToApi);
    }

    @Override
    public List<RatingApi> retrieveAllRatings(String tourId) {

        return ratingRepository.findByTourId(verifyTour(tourId).getTourId())
                               .stream().map(this::buildRatingApi)
                               .collect(Collectors.toList());
    }

    @Override
    public Page<RatingApi> retrieveAllRatingsPaging(String tourId, Pageable pageable) {

        Page<TourRating> ratings = ratingRepository.findByTourId(verifyTour(tourId).getTourId(), pageable);

        return new PageImpl<>(
                ratings.get().map(this::buildRatingApi).collect(Collectors.toList()),
                pageable, ratings.getTotalElements()
        );
    }

    @Override
    public Map<String, Double> findAverage(String tourId) {
        return Map.of("average",
                      ratingRepository.findByTourId(verifyTour(tourId).getTourId())
                                      .stream().mapToInt(TourRating::getScore).average()
                                      .orElseThrow(() -> new NoSuchElementException("Tour has no ratings")));
    }

    @Override
    public RatingApi updateRating(String tourId, RatingApi ratingApi){
        var tourRating = verifyTourRating(tourId, ratingApi.getCustomerId());
        tourRating.setScore(ratingApi.getScore());
        tourRating.setComment(ratingApi.getComment());

        return buildRatingApi(ratingRepository.save(tourRating));
    }

    @Override
    public RatingApi patchRating(String tourId, RatingApi ratingApi){
        var tourRating = verifyTourRating(tourId, ratingApi.getCustomerId());
        tourRating.setScore(ratingApi.getScore());
        tourRating.setComment(!ratingApi.getComment().isBlank() ? ratingApi.getComment() : "Comment not provided!");

        return buildRatingApi(ratingRepository.save(tourRating));
    }

    @Override
    public void removeRating(String tourId, int customerId){
        var tourRating = verifyTourRating(tourId, customerId);
        ratingRepository.delete(tourRating);
    }

    private TourRating verifyTourRating(String tourId, int customerId) {
        return ratingRepository.findByTourIdAndCustomerId(tourId, customerId)
                               .orElseThrow(() -> new NoSuchElementException("Tour-Rating pair for request( " + tourId
                                                                             + " for customer " + customerId));
    }

    private RatingApi buildRatingApi(TourRating convertToApi) {
        return RatingApi.builder()
                        .score(convertToApi.getScore())
                        .comment(convertToApi.getComment())
                        .customerId(convertToApi.getCustomerId())
                        .build();
    }

    private Tour verifyTour(String tourId) throws NoSuchElementException {
        return toBusiness(tourService.retrieveTour(tourId), TourPackage.builder().build());
    }
}
