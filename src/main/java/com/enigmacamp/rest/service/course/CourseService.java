package com.enigmacamp.rest.service.course;

import com.enigmacamp.rest.dto.CourseRequestDTO;
import com.enigmacamp.rest.exception.EntityExistException;
import com.enigmacamp.rest.exception.NotFoundException;
import com.enigmacamp.rest.model.Course;
import com.enigmacamp.rest.model.CourseType;
import com.enigmacamp.rest.repository.ICourseRepository;
import com.enigmacamp.rest.repository.ICourseTypeRepository;
import com.enigmacamp.rest.response.SuccessResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    private ICourseRepository courseRepository;
    private ModelMapper modelMapper;

    public CourseService(@Autowired ICourseRepository courseRepository,@Autowired ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    //    public CourseService(@Autowired ICourseRepository courseRepository) {
//        this.courseRepository = courseRepository;
//    }

    @Override
    public Course create(Course course) {
        try {
            Course newCourse = courseRepository.save(course);
            return newCourse;
        } catch (DataIntegrityViolationException e){
            throw new EntityExistException();
        }
    }



    @Override
    public Course update(CourseRequestDTO course, String id) {
        try {
            Course existingCourse = get(id);
            modelMapper.map(course, existingCourse);
            courseRepository.save(existingCourse);
            return existingCourse;
        } catch (NotFoundException e){
            throw new NotFoundException("Update failed because ID not found");
        }
    }

    @Override
    public Course delete(String id){
        try {
            Course existingCourse = get(id);
            courseRepository.delete(existingCourse);
            return existingCourse;
        } catch (NotFoundException e){
            throw new NotFoundException("Delete Failed because id not found");
        }

    }
    @Override
    public Course get(String id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()){
            throw new NotFoundException("Course Not Found");
        }
        return course.get();
    }
//    @Override
//    public Course get(String id) throws Exception {
//        return null;
//    }

    @Override
    public List<Course> getBy(String key, String value) {
        List<Course> results;
        switch (key){
            case "title":
                results = findByTitleContains(value);
                break;
            case "description":
                results = findByDescriptionContains(value);
                break;
            default:
                results = null;
                break;
        }
        return results;
    }


    @Override
    public List<Course> getAll() throws Exception {
        List<Course> courses = courseRepository.findAll();
        return courses;
    }

    @Override

    public Page<Course> getAll(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page), size, sort);
        Page<Course> result = courseRepository.findAll(pageable);
        return result;
    }

    //find by
    public List<Course> findByTitleContains(String value){
        List<Course> courses = courseRepository.findByTitleContains(value);
        if (courses.isEmpty()){
            throw new NotFoundException("Course with " + value + " title is not found");
        }
        return courses;
    }

    public List<Course> findByDescriptionContains(String value){
        List<Course> courses = courseRepository.findByDescriptionContains(value);
        if (courses.isEmpty()){
            throw new NotFoundException("Course with " + value + " description is not found");
        }
        return courses;
    }
}
