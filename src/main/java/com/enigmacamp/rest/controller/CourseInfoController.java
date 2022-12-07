package com.enigmacamp.rest.controller;

import com.enigmacamp.rest.dto.CourseInfoRequestDTO;
import com.enigmacamp.rest.model.CourseInfo;
import com.enigmacamp.rest.response.ErrorResponse;
import com.enigmacamp.rest.response.SuccessResponse;
import com.enigmacamp.rest.service.courseinfo.ICourseInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course_info")
@Validated
public class CourseInfoController {

    ICourseInfoService service;
    ModelMapper modelMapper;

    public CourseInfoController(@Autowired ICourseInfoService service,@Autowired ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }
    @GetMapping
    public ResponseEntity getAllCourse() throws Exception{
        try {
            List<CourseInfo> courseInfo = service.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get All Course", courseInfo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("404", e.getMessage()));
        }

    }

    @PostMapping
    public ResponseEntity createCourseType(@Valid @RequestBody CourseInfoRequestDTO course) {
        try {
            CourseInfo newCourseInfo = modelMapper.map(course, CourseInfo.class);
            CourseInfo result = service.create(newCourseInfo);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create course", result));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("404", e.getMessage()));
        }

    }
}
