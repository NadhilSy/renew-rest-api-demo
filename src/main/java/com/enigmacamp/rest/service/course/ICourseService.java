package com.enigmacamp.rest.service.course;

import com.enigmacamp.rest.dto.CourseRequestDTO;
import com.enigmacamp.rest.model.Course;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICourseService {
    List<Course> getAll() throws Exception;
    Course create(Course course);
    Course get(String id) throws Exception;
    Course update(CourseRequestDTO course, String id) ;
    Course delete(String id) ;

    List<Course> getBy(String key, String value);
    List<Course> findByTitleContains(String value);
    List<Course> findByDescriptionContains(String value);
    Page<Course> getAll(Integer page, Integer size, String direction, String sortBy);
}
