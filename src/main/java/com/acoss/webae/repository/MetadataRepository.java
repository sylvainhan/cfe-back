package com.acoss.webae.repository;

import com.acoss.webae.domain.Metadata;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Metadata entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MetadataRepository extends JpaRepository<Metadata, Long> {

}
