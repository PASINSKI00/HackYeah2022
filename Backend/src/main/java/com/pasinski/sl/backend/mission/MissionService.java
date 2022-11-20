package com.pasinski.sl.backend.mission;

import com.pasinski.sl.backend.mission.forms.MissionForm;
import com.pasinski.sl.backend.security.UserSecurityService;
import com.pasinski.sl.backend.task.Task;
import com.pasinski.sl.backend.task.TaskRepository;
import com.pasinski.sl.backend.task.forms.TaskForm;
import com.pasinski.sl.backend.task.proof.ProofType;
import com.pasinski.sl.backend.task.proof.ProofTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class MissionService {
    private MissionRepository missionRepository;
    private UserSecurityService userSecurityService;
    private ProofTypeRepository proofTypeRepository;
    private TaskRepository taskRepository;

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


    public void updateMission(Mission newMission) {
        if(!userSecurityService.isAdmin())
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);

        if(newMission.getIdMission() == null)
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);

        Mission oldMission = missionRepository.findById(newMission.getIdMission()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        if (newMission.getName() != null)
            oldMission.setName(newMission.getName());

        if (newMission.getDescription() != null)
            oldMission.setDescription(newMission.getDescription());

        if (newMission.getStartDate() != null)
            oldMission.setStartDate(newMission.getStartDate());

        if (newMission.getDueDate() != null)
            oldMission.setDueDate(newMission.getDueDate());

        missionRepository.save(oldMission);
    }

    public void deleteMission(Mission mission) {
        if(!userSecurityService.isAdmin())
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);

        if(mission.getIdMission() == null)
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);


        if(missionRepository.existsById(mission.getIdMission()))
            missionRepository.deleteById(mission.getIdMission());
    }

    public Mission getActualMission() {
        Mission mission = missionRepository.getActualMission(new Timestamp(System.currentTimeMillis()));

        if(mission == null)
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);

        return mission;
    }

    public Set<Task> getActualMissionTasks() {
        Mission mission = getActualMission();

        return mission.getTasks();
    }

    public Set<Task> getTasks(Long id) {
        Mission mission = missionRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        return mission.getTasks();
    }

    public void addTask(TaskForm taskForm){
        Task task = new Task();

        task.setName(taskForm.getName());
        task.setDescription(taskForm.getDescription());
        task.setPoints(taskForm.getPoints());
        task.setMission(missionRepository.findById(taskForm.getMissionId()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND)));

        if(taskForm.getProofRequired() && taskForm.getProofTypeName() != null) {
            task.setProofRequired(taskForm.getProofRequired());
            ProofType proofType = proofTypeRepository.findByName(taskForm.getProofTypeName());
            if(proofType == null)
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
            task.setProofType(proofType);
        }

        taskRepository.save(task);
    }

    public void updateTask(Task task){
        Task oldTask = taskRepository.findById(task.getIdTask()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        if(task.getName() != null)
            oldTask.setName(task.getName());

        if(task.getDescription() != null)
            oldTask.setDescription(task.getDescription());

        if(task.getPoints() != null)
            oldTask.setPoints(task.getPoints());

        if(task.getProofRequired() != null)
            oldTask.setProofRequired(task.getProofRequired());

        if(task.getProofType() != null)
            oldTask.setProofType(task.getProofType());

        taskRepository.save(oldTask);
    }

    public void deleteTask(Task task){
        if(task.getIdTask() == null)
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);

        if(taskRepository.existsById(task.getIdTask()))
            taskRepository.deleteById(task.getIdTask());
    }
}
