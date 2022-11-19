package com.pasinski.sl.backend.mission;

import com.pasinski.sl.backend.task.Task;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;


@Getter
@Setter
@Entity
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_mission", nullable = false)
    private Long idMission;
    private String name;
    private String description;
    private Date startDate;
    private Date dueDate;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private Set<Task> tasks;
}
