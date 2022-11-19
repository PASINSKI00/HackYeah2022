package com.pasinski.sl.backend.task;

import com.pasinski.sl.backend.mission.Mission;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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
    private int points;
    private boolean proofRequired;
    private int usages;
}
