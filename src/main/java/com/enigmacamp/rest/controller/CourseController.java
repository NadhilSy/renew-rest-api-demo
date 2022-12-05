package com.enigmacamp.rest.controller;

import com.enigmacamp.rest.dto.CourseRequestDTO;
import com.enigmacamp.rest.model.Course;
import com.enigmacamp.rest.response.ErrorResponse;
import com.enigmacamp.rest.response.PagingResponse;
import com.enigmacamp.rest.response.SuccessResponse;
import com.enigmacamp.rest.service.course.CourseService;
import com.enigmacamp.rest.service.course.ICourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/courses")
@Validated
public class CourseController {

    //constructor injection

    private ModelMapper modelMapper;
    private ICourseService service;

    public CourseController(@Autowired ModelMapper modelMapper,@Autowired ICourseService service) {
        this.modelMapper = modelMapper;
        this.service = service;
    }

    //create method
    @PostMapping
    public ResponseEntity createCourse(@Valid @RequestBody CourseRequestDTO courseRequest){
        try {
            Course newCourse = modelMapper.map(courseRequest, Course.class);//mapping DTO dengan CLass Entity
            Course result = service.create(newCourse);//lempar data ke service, yang mana perlu di assign kembali ke result untuk digunkan untuk validasi apakah data null atau tidak
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create course", result));//kalo tidak null, me-return success response
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("404", e.getMessage()));
        }

    }

    //deprecated, diantikan oleh getAllCOurse with param
    //simple getall
/*    @GetMapping
    public ResponseEntity getAllCourse() {
        try {
            List<Course> courses = service.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all Courses", courses));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("404", e.getMessage()));
        }
    }
*/

    //getall with param
    @GetMapping(params = {"page","size","direction","sortBy"})
    public ResponseEntity getAllCourse(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "5") Integer size,
        @RequestParam(defaultValue = "DESC",required = false) String direction,
        @RequestParam(defaultValue = "id",required = false) String sortBy
    ){
        try {
            Page<Course> pageCourses = service.getAll(page, size, direction, sortBy);
            return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Ok",pageCourses));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("404", e.getMessage()));
        }
    }
}
