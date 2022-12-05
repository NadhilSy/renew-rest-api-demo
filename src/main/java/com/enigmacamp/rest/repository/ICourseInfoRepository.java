package com.enigmacamp.rest.repository;

import com.enigmacamp.rest.model.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseInfoRepository extends JpaRepository<CourseInfo, String> {
}
