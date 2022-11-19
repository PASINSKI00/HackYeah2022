package com.pasinski.sl.backend.user.forms;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AdminRequestForm {
    @NotBlank(message = "id is mandatory")
    private Long idUser;
    private String reason;
}
