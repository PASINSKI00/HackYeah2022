package com.pasinski.sl.backend.mission.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
public class MissionForm {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Start date is mandatory")
    private Date startDate;

    @NotNull(message = "Due date is mandatory")
    private Date dueDate;
}
