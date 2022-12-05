package com.enigmacamp.rest.service.coursetype;

import com.enigmacamp.rest.model.CourseType;

import java.util.List;

public interface ICourseTypeService {

    List<CourseType> list() throws Exception;
    CourseType create(CourseType courseType);
}
