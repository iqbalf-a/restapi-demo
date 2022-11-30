package com.enigmacamp.restapidemo.service;

import com.enigmacamp.restapidemo.Exception.NotFoundException;
import com.enigmacamp.restapidemo.model.Course;
import com.enigmacamp.restapidemo.repository.Irepository.ICourseRepository;
import com.enigmacamp.restapidemo.service.Iservice.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    @Value("3")
    Integer dataLength;
    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public List<Course> list() throws Exception {
        List<Course> result = courseRepository.getAll();
        if (result.isEmpty()) throw new NotFoundException();
        return result;
    }

    @Override
    public Course create(Course course) throws Exception {
        if (!(courseRepository.getAll().size() < dataLength)) {
            throw new NotFoundException("Data is full");
        }
        return courseRepository.create(course);
    }

    @Override
    public Course get(String id) throws Exception {
        Optional<Course> result = courseRepository.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException();
        }
        return result.get();
    }

    @Override
    public void update(Course course, String id) throws Exception {
        Optional<Course> result = courseRepository.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException();
        }
        courseRepository.update(course, id);
    }

    @Override
    public void delete(String id) throws Exception {
        Optional<Course> result = courseRepository.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException();
        }
        courseRepository.delete(id);

    }

    @Override
    public List<Course> getBy(String key, String value) throws Exception {
        List<Course> result = courseRepository.getBy(key, value);
        if (result.isEmpty()) throw new NotFoundException();
        return result;
    }

}
