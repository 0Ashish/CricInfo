package com.cric.apis.crickinfoBackend.Repositiries;

import com.cric.apis.crickinfoBackend.Entities.Cricket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchRepo extends JpaRepository<Cricket,Integer> {
    // fetching match data by providing teamHeading

    Optional<Cricket> findByTeamHeading(String teamHeading);
}
