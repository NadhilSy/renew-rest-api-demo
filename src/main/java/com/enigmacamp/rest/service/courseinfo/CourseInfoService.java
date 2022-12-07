package com.enigmacamp.rest.service.courseinfo;

import com.enigmacamp.rest.exception.EntityExistException;
import com.enigmacamp.rest.exception.NotFoundException;
import com.enigmacamp.rest.model.CourseInfo;
import com.enigmacamp.rest.repository.ICourseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseInfoService implements ICourseInfoService{

    ICourseInfoRepository courseInfoRepository;

    public CourseInfoService(@Autowired ICourseInfoRepository courseInfoRepository) {
        this.courseInfoRepository = courseInfoRepository;
    }

    @Override
    public List<CourseInfo> getAll() {
        try {
            List<CourseInfo> courseInfos = courseInfoRepository.findAll();
            return courseInfos;
        }
        catch (Exception e){
            throw new NotFoundException();
        }
    }

    @Override
    public CourseInfo create(CourseInfo courseInfo) {
        try {
            CourseInfo newInfo = courseInfoRepository.save(courseInfo);
            return newInfo;
        }
        catch (DataIntegrityViolationException e){
            throw new EntityExistException(e.getMessage());
        }
    }
}
