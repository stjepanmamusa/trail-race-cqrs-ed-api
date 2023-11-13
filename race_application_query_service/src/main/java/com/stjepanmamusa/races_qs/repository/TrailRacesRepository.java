package com.stjepanmamusa.races_qs.repository;


import com.stjepanmamusa.races_qs.entity.TrailRace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrailRacesRepository extends JpaRepository<TrailRace, Long> {
    TrailRace findByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
}
