package com.pasinski.sl.backend.mission;

import com.pasinski.sl.backend.mission.forms.MissionForm;
import com.pasinski.sl.backend.user.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController()
@RequestMapping("/api/mission")
public class MissionController {
    private MissionService missionService;

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getMissions() {
        try {
            ResponseEntity<List<Mission>> response =  new ResponseEntity<List<Mission>>(missionService.getMissions(), HttpStatus.OK);
            return response;
        } catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @PostMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> addMission(@Valid @RequestBody MissionForm missionForm) {
        try {
            missionService.addMission(missionForm);
        } catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
