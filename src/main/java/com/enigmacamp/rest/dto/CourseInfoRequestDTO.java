package com.enigmacamp.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CourseInfoRequestDTO {

    @NotNull
    @Size(max = 10, message = "Kepanjangan cuy")
    private String courseDuration;

    @NotNull
    @Size(max = 20, message = "Kepanjangan cuy")
    private String courseLevel;


}
