package com.enigmacamp.rest.service.coursetype;

import com.enigmacamp.rest.exception.EntityExistException;
import com.enigmacamp.rest.model.CourseType;
import com.enigmacamp.rest.repository.ICourseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseTypeService implements ICourseTypeService{

    ICourseTypeRepository courseTypeRepository;

    public CourseTypeService(@Autowired ICourseTypeRepository courseTypeRepository) {
        this.courseTypeRepository = courseTypeRepository;
    }

    @Override
    public List<CourseType> getAll() throws Exception {
        List<CourseType> courseTypes = courseTypeRepository.findAll();
        return courseTypes;
    }

    @Override
    public CourseType create(CourseType courseType) {
        try {
            CourseType newType = courseTypeRepository.save(courseType);
            return newType;
        }catch (DataIntegrityViolationException e){
            throw new EntityExistException();
        }
    }
}
