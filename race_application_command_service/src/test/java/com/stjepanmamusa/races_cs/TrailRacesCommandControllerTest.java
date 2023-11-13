package com.stjepanmamusa.races_cs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stjepanmamusa.races_cs.controller.TrailRacesCommandController;
import com.stjepanmamusa.races_cs.entity.TrailRace;
import com.stjepanmamusa.races_cs.service.JwtService;
import com.stjepanmamusa.races_cs.service.TrailRacesCommandService;
import com.stjepanmamusa.races_cs.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;


@WebMvcTest(TrailRacesCommandController.class)
class TrailRacesCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TrailRacesCommandService commandService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserInfoService userInfoService;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createRace() throws Exception {
        TrailRace race = new TrailRace();

        mockMvc.perform(MockMvcRequestBuilders.post("/races/create-race")
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(race)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updateRace() throws Exception {
        TrailRace race = new TrailRace();

        mockMvc.perform(MockMvcRequestBuilders.patch("/races/update-race")
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(race)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void deleteRace() throws Exception {
        UUID raceId = UUID.randomUUID();

        mockMvc.perform(MockMvcRequestBuilders.delete("/races/delete-race/{id}", raceId)
                .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
