package com.sammy.respository;

import com.sammy.model.business.TourPackage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "packages", path = "packages")
public interface TourPackageRepository extends MongoRepository<TourPackage, String> {
    Optional<TourPackage> findByName(String name);

    @Override
    @RestResource(exported = false)
    <S extends TourPackage> List<S> saveAll(Iterable<S> tourPackages);

    @Override
    @RestResource(exported = false)
    <S extends TourPackage> S save(S tourPackage);

    @Override
    @RestResource(exported = false)
    void deleteById(String id);

    @Override
    @RestResource(exported = false)
    void delete(TourPackage tourPackage);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends TourPackage> tourPackages);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
