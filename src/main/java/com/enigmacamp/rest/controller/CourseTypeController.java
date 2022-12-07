package com.enigmacamp.rest.controller;

import com.enigmacamp.rest.dto.CourseTypeRequestDTO;
import com.enigmacamp.rest.model.CourseType;
import com.enigmacamp.rest.response.ErrorResponse;
import com.enigmacamp.rest.response.SuccessResponse;
import com.enigmacamp.rest.service.coursetype.ICourseTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course_types")
@Validated
public class CourseTypeController {

    ICourseTypeService service;
    ModelMapper modelMapper;

    public CourseTypeController(@Autowired ICourseTypeService service,@Autowired ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity getAllCourse(){
        try {
            List<CourseType> courseTypes = service.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get All Course", courseTypes));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("404", e.getMessage()));
        }

    }

    @PostMapping
    public ResponseEntity createCourseType(@Valid @RequestBody CourseTypeRequestDTO course) {
        try {
            CourseType newCourseType = modelMapper.map(course, CourseType.class);
            CourseType result = service.create(newCourseType);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create course", result));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("404", e.getMessage()));
        }

    }
}
