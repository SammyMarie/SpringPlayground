package com.sammy.model.business;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@AllArgsConstructor
@Document(collection = "TourPackage")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourPackage {

    @Id
    private String code;

    private String name;
}
