package com.pasinski.sl.backend.mission;

import com.pasinski.sl.backend.mission.forms.MissionForm;
import com.pasinski.sl.backend.security.UserSecurityService;
import com.pasinski.sl.backend.task.Task;
import com.pasinski.sl.backend.task.forms.TaskForm;
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
import java.util.Set;

@AllArgsConstructor
@RestController()
@RequestMapping("/api/mission")
public class MissionController {
    private MissionService missionService;
    private UserSecurityService userSecurityService;

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getMissions() {
        if(!userSecurityService.isAdmin())
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

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
        if(!userSecurityService.isAdmin())
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        try {
            missionService.addMission(missionForm);
        } catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateMission(@RequestBody Mission mission) {
        if(!userSecurityService.isAdmin())
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        try {
            missionService.updateMission(mission);
        } catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteMission(@RequestBody Mission mission) {
        if(!userSecurityService.isAdmin())
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        try {
            missionService.deleteMission(mission);
        } catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/actual")
    public ResponseEntity<?> getActualMission() {
        Mission mission;

        try {
            mission = missionService.getActualMission();
        } catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }

        return new ResponseEntity<>(mission, HttpStatus.OK);
    }

    @GetMapping("/actual/tasks")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getActualMissionTasks() {
        Set<Task> tasks;

        if(!userSecurityService.isAdmin())
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        try {
            tasks = missionService.getActualMissionTasks();
        } catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/tasks")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getTasks(@RequestBody Mission mission) {
        Set<Task> tasks;

        if(!userSecurityService.isAdmin())
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        if (mission.getIdMission() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            Long id = mission.getIdMission();
            tasks = missionService.getTasks(id);
        } catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/task")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> addTask(@RequestBody TaskForm taskform) {
        if(!userSecurityService.isAdmin())
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        try {
            missionService.addTask(taskform);
        } catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/task")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateTask(@RequestBody Task task) {
        if(!userSecurityService.isAdmin())
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        try {
            missionService.updateTask(task);
        } catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/task")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteTask(@RequestBody Task task) {
        if(!userSecurityService.isAdmin())
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        try {
            missionService.deleteTask(task);
        } catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
