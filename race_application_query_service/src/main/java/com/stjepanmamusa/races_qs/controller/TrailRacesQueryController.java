package com.stjepanmamusa.races_qs.controller;

import com.stjepanmamusa.races_qs.entity.TrailRace;
import com.stjepanmamusa.races_qs.service.TrailRacesQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/races")
public class TrailRacesQueryController {
    @Autowired
    private TrailRacesQueryService queryService;

    @GetMapping("/all")
    public List<TrailRace> getAllRaces() {
        return queryService.getAllRaces();
    }

    @GetMapping("/{id}")
    public TrailRace getSingleRace(@PathVariable UUID id) {
        return queryService.getRaceById(id);
    }
}
