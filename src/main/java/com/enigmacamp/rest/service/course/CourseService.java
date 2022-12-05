package com.enigmacamp.rest.service.course;

import com.enigmacamp.rest.exception.EntityExistException;
import com.enigmacamp.rest.model.Course;
import com.enigmacamp.rest.model.CourseType;
import com.enigmacamp.rest.repository.ICourseRepository;
import com.enigmacamp.rest.repository.ICourseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

   private ICourseRepository courseRepository;

    public CourseService(@Autowired ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

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
    public void update(Course course, String id) {

    }

    @Override
    public Course delete(String id) throws Exception {
        return null;
    }
    @Override
    public Course get(String id) throws Exception {
        return null;
    }

    @Override
    public List<Course> getBy(String key, String value) {
        return null;
    }

    @Override
    public List<Course> findByTitleContains(String value) {
        return null;
    }

    @Override
    public List<Course> findByDescriptionContains(String value) {
        return null;
    }
    @Override
    public List<Course> getAll() throws Exception {
        List<Course> courses = courseRepository.findAll();
        return courses;
    }

    @Override
    public Page<Course> list(Integer page, Integer size, String direction, String sortBy) {
        return null;
    }
}
