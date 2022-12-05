package com.enigmacamp.rest.repository;

import com.enigmacamp.rest.model.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseTypeRepository extends JpaRepository<CourseType, String> {
}
