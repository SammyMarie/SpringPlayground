package com.sammy.entity.business;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "Tour_Rating")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourRating {

    @EmbeddedId
    private TourRatingPk ratingPk;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "comment")
    private String comment;
}