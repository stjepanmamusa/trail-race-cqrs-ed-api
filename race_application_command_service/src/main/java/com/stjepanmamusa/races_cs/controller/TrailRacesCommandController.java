package com.stjepanmamusa.races_cs.controller;

import com.stjepanmamusa.races_cs.entity.TrailRace;
import com.stjepanmamusa.races_cs.service.TrailRacesCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/races")
public class TrailRacesCommandController {
    @Autowired
    private TrailRacesCommandService commandService;
    @PostMapping("/create-race")
    @ResponseStatus(HttpStatus.OK)
    public void createRace(@RequestBody TrailRace race) {
        commandService.createRace(race);
    }

    @PatchMapping("/update-race")
    @ResponseStatus(HttpStatus.OK)
    public void updateRace(@RequestBody TrailRace race) {
        commandService.updateRace(race);
    }

    @DeleteMapping("/delete-race/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRace(@PathVariable UUID id) {
        commandService.deleteRace(id);
    }
}
