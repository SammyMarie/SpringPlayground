package com.sammy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sammy.entity.resource.TourApi;
import com.sammy.service.TourPackageService;
import com.sammy.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class SpringPlayGroundApplication{

    @Autowired
    private TourPackageService packageService;

    @Autowired
    private TourService tourService;

    public static void main(String... args) {
        run(SpringPlayGroundApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataSetup(){
        return (args) -> {
            createTourPackages();
            long numOfPackages = packageService.total();

            createTours();
            long numOfTours = tourService.total();
        };
    }

    private void createTours() throws IOException {
        List<TourApi> tourApis = new ObjectMapper().setVisibility(FIELD, ANY)
                          .readValue(new FileInputStream("ExploreCalifornia.json"), new TypeReference<>() {});
        tourApis.forEach(tourService::createTour);
    }

    private void createTourPackages() {
        packageService.createTourPackage("BC", "Backpack Cal");
        packageService.createTourPackage("CC", "California Calm");
        packageService.createTourPackage("CH", "California Hot Springs");
        packageService.createTourPackage("CY", "Cycle California");
        packageService.createTourPackage("DS", "From Desert to Sea");
        packageService.createTourPackage("KC", "Kids California");
        packageService.createTourPackage("NW", "Nature watch");
        packageService.createTourPackage("SC", "Snowboard Cali");
        packageService.createTourPackage("TC", "Taste of California");
    }
}
