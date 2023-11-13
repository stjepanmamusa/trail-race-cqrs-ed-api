package com.stjepanmamusa.races_qs.service;

import com.stjepanmamusa.races_qs.entity.TrailRace;
import com.stjepanmamusa.races_qs.repository.TrailRacesRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class TrailRacesQueryService {
    @Autowired
    private TrailRacesRepository repository;

    public List<TrailRace> getAllRaces() {
        return repository.findAll();
    }

    public TrailRace getRaceById(UUID uuid) {
        return repository.findByUuid(uuid);
    }

    public TrailRace createRace(TrailRace race) {
        return repository.save(race);
    }

    public TrailRace updateRace(TrailRace race) {
        TrailRace currentRace = race;
        try {
            currentRace = repository.findByUuid(race.getUuid());
            currentRace.setFirstName(race.getFirstName());
            currentRace.setLastName(race.getLastName());
            currentRace.setDistance(race.getDistance());
            currentRace.setClub(race.getClub());
            return repository.save(currentRace);
            
        } catch (NullPointerException e) {
            log.error("Update operation called for non-existing race!");
        }
        return currentRace;
    }

    public void deleteRace(UUID id) {
        repository.deleteByUuid(id);
    }
}
