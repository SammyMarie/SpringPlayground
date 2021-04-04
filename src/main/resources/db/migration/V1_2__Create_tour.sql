CREATE TABLE IF NOT EXISTS Tour (
    tourId integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(2000),
    blurb VARCHAR(2000),
    price NUMERIC(19,4),
    duration VARCHAR(255),
    bullets VARCHAR(2000),
    keywords VARCHAR(600),
    tourPackage VARCHAR(2),
    difficulty VARCHAR(30),
    region VARCHAR(450),
    FOREIGN KEY (tourPackage) REFERENCES Tour_Package(`code`)
);