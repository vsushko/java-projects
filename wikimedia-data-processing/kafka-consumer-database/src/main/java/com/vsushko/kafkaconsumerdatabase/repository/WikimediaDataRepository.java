package com.vsushko.kafkaconsumerdatabase.repository;

import com.vsushko.kafkaconsumerdatabase.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vsushko
 */
@Repository
public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {
}
