package com.enigmacamp.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CourseTypeRequestDTO {

    @Size(max = 15, message = "Kepanjangan cuy")
    @NotBlank(message = "name is required")
    private String courseTypeName;

}
