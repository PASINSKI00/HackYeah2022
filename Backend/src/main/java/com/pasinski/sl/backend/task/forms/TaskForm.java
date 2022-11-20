package com.pasinski.sl.backend.task.forms;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
public class TaskForm {
    @NotBlank(message = "Name is mandatory")
    String name;
    @NotNull(message = "Points are mandatory")
    Integer points;
    @NotBlank(message = "Description is mandatory")
    String description;
    @NotNull(message = "MissionId is mandatory")
    Long MissionId;

    Boolean proofRequired;
    String proofTypeName;
}
