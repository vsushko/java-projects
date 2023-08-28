package com.reactivespring.repository;

import domain.MovieInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author vsushko
 */
public interface MovieInfoRepository extends ReactiveMongoRepository<MovieInfo, String> {

}
