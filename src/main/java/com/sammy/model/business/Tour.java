package com.sammy.model.business;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "Tour")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tour {

    @Id
    private String tourId;

    @Indexed
    private String title;

    private String description;

    private String blurb;

    private BigDecimal price;

    private String duration;

    private String bullets;

    private String keywords;

    @Indexed
    private String tourPackageCode;

    private String tourPackageName;

    private Map<String, String> details;
}

