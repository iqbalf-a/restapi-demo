package com.enigmacamp.restapidemo.service.Iservice;

import com.enigmacamp.restapidemo.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {

    List<Course> list();
    Course create(Course course);
    Optional<Course> get(String id);
    void update(Course course, String id);
    void delete(String id);
}
