package com.pasinski.sl.backend.mission;

import com.pasinski.sl.backend.mission.forms.MissionForm;
import com.pasinski.sl.backend.security.UserSecurityService;
import com.pasinski.sl.backend.task.Task;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
@Service
@AllArgsConstructor
public class MissionService {
    private MissionRepository missionRepository;
    private UserSecurityService userSecurityService;

    public List<Mission> getMissions() {
        if(!userSecurityService.isAdmin())
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);

        List<Mission> list = missionRepository.findAll();

        list.forEach(mission -> {
            mission.setTasks(null);
        });

        return list;
    }

    public void addMission(MissionForm missionForm) {
        if(!userSecurityService.isAdmin())
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);

        Mission mission = new Mission();
        mission.setName(missionForm.getName());
        mission.setDescription(missionForm.getDescription());
        mission.setStartDate(missionForm.getStartDate());
        mission.setDueDate(missionForm.getDueDate());

        missionRepository.save(mission);
    }
}
