package com.sammy.controller;

import com.sammy.model.resource.RatingApi;
import com.sammy.service.TourRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {

    private final TourRatingService ratingService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createTourRating(@PathVariable("tourId") String tourId,
                                 @RequestBody @Validated RatingApi ratingApi) {
        ratingService.createTourRating(tourId, ratingApi);
    }

    @GetMapping
    public List<RatingApi> retrieveAllRatingsForTour(@PathVariable("tourId") String tourId){

        return ratingService.retrieveAllRatings(tourId);
    }

    @GetMapping("/pageable")
    public Page<RatingApi> retrieveAllRatingsForTourPageable(@PathVariable("tourId") String tourId, Pageable pageable){

        return ratingService.retrieveAllRatingsPaging(tourId, pageable);
    }

    @GetMapping("/average")
    public Map<String, Double> retrieveAverage(@PathVariable("tourId") String tourId){
        return ratingService.findAverage(tourId);
    }

    @PutMapping
    public RatingApi modifyRating(@PathVariable("tourId") String tourId,
                                  @RequestBody @Validated RatingApi ratingApi){
        return ratingService.updateRating(tourId, ratingApi);
    }

    @PatchMapping
    public RatingApi patchModifyRating(@PathVariable("tourId") String tourId,
                                       @RequestBody @Validated RatingApi ratingApi){
        return ratingService.patchRating(tourId, ratingApi);
    }

    @DeleteMapping("/{customerId}")
    public void removeRating(@PathVariable("tourId") String tourId,
                             @PathVariable("customerId") int customerId){
        ratingService.removeRating(tourId, customerId);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String respondWith404(NoSuchElementException elementException) {
        return elementException.getMessage();
    }
}