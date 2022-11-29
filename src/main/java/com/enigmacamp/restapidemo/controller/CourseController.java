package com.enigmacamp.restapidemo.controller;

import com.enigmacamp.restapidemo.model.Course;
import com.enigmacamp.restapidemo.service.CourseService;
import com.enigmacamp.restapidemo.service.Iservice.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.list();
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.create(course);
    }

    @GetMapping("/{id}")
    public Optional<Course> getById(@PathVariable("id") String id) {
        return courseService.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") String id) {
        courseService.delete(id);
    }
}
