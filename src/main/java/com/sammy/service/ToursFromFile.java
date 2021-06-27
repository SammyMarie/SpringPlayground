package com.sammy.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sammy.model.resource.TourApi;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ToursFromFile {

    public static TourApi buildTourApi(Map<String, String> tourRecord) {
        TourApi tourApi = null;

        if (!tourRecord.isEmpty()) {
            Map<String, String> details = new HashMap<>(tourRecord);
            details.remove("title");
            details.remove("description");
            details.remove("blurb");
            details.remove("price");
            details.remove("length");
            details.remove("bullets");
            details.remove("keywords");
            details.remove("packageType");

            tourApi = TourApi.builder()
                             .title(tourRecord.get("title"))
                             .description(tourRecord.get("description"))
                             .blurb(tourRecord.get("blurb"))
                             .price(tourRecord.get("price"))
                             .duration(tourRecord.get("length"))
                             .bullets(tourRecord.get("bullets"))
                             .keywords(tourRecord.get("keywords"))
                             .tourPackageName(tourRecord.get("packageType"))
                             .details(details)
                             .build();
        }
        return tourApi;
    }

    public static List<TourApi> readFile(String fileToImport) throws IOException {
        List<Map<String, String>> tourRecords = new ObjectMapper().setVisibility(FIELD, ANY)
                                                                  .readValue(new FileInputStream(fileToImport),
                                                                             new TypeReference<>() {
                                                                             });

        return tourRecords.stream().map(ToursFromFile::buildTourApi).collect(Collectors.toList());
    }
}
