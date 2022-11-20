package com.pasinski.sl.backend.task;

import com.pasinski.sl.backend.mission.Mission;
import com.pasinski.sl.backend.task.proof.ProofType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_task", nullable = false)
    private Long idTask;

    private String name;
    private String description;
    private Integer points;
    private Boolean proofRequired;
    private int usages;

    @ManyToOne
    @JoinColumn(name = "mission_id_mission")
    private Mission mission;

    @ManyToOne
    @JoinColumn(name = "proof_type_id_proof_type")
    private ProofType proofType;
}
