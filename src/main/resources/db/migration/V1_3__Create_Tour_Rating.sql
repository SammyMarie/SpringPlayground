CREATE TABLE IF NOT EXISTS Tour_Rating(
    tourId INT NOT NULL,
    customerId INT NOT NULL,
    score INT NOT NULL,
    comment VARCHAR(2000),
    FOREIGN KEY (tourId) REFERENCES Tour(tourId),
    PRIMARY KEY(tourId, customerId),
    UNIQUE KEY unique_rating (tourId, customerId)
);