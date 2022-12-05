package com.enigmacamp.rest.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class CourseRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;
    @Size(max = 100, message = "Kepanjangan cuy")
    private String description;
    @NotBlank(message = "Input exceed limit")
    private String link;


}
