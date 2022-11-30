package com.enigmacamp.restapidemo.service;

import com.enigmacamp.restapidemo.model.Course;
import com.enigmacamp.restapidemo.repository.Irepository.ICourseRepository;
import com.enigmacamp.restapidemo.service.Iservice.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {
    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public List<Course> list() {
        try {
            return courseRepository.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course create(Course course) {
        try {
            return courseRepository.create(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Course> get(String id) {
        try {
            return courseRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Course course, String id) {
        try {
            courseRepository.update(course, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            courseRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> getBy(String key, String value) {
        try {
            return courseRepository.getBy(key, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
