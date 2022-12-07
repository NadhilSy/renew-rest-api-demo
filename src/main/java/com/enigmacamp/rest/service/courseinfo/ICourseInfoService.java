package com.enigmacamp.rest.service.courseinfo;

import com.enigmacamp.rest.model.CourseInfo;

import java.util.List;

public interface ICourseInfoService  {

    List<CourseInfo> getAll();
    CourseInfo create(CourseInfo courseInfo);

}
