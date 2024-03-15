package com.sherenterprise.coursereport.repository;

import com.sherenterprise.coursereport.domain.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<MatchEntity, Long> {

    Optional<MatchEntity> findByEmail(String email);

}
