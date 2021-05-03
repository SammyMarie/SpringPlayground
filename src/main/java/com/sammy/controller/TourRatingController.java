package com.sammy.controller;

import com.sammy.entity.resource.RatingApi;
import com.sammy.service.TourRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public void createTourRating(@PathVariable(value = "tourId") int tourId,
                                 @RequestBody @Validated RatingApi ratingApi) {
        ratingService.createTourRating(tourId, ratingApi);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String respondWith404(NoSuchElementException elementException) {
        return elementException.getMessage();
    }
}