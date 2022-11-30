package com.enigmacamp.restapidemo.controller;

import com.enigmacamp.restapidemo.model.Course;
import com.enigmacamp.restapidemo.model.request.CourseRequest;
import com.enigmacamp.restapidemo.model.response.ErrorResponse;
import com.enigmacamp.restapidemo.model.response.SuccessResponse;
import com.enigmacamp.restapidemo.service.Iservice.ICourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ICourseService courseService;

    @GetMapping
    public ResponseEntity getAllCourses() {
        try {
            List<Course> courseList = courseService.list();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all courses", courseList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity createCourse(@RequestBody CourseRequest courseRequest) {
        try {
            Course newCourse = modelMapper.map(courseRequest, Course.class);
            Course result = courseService.create(newCourse);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create course", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X02", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id) {
        try {
            Optional<Course> result = courseService.get(id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get course with id", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable("id") String id) {
        courseService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new SuccessResponse<>("Success delete course", id));
    }

    @GetMapping(params = {"keyword", "value"})
    public ResponseEntity getBy(@RequestParam String keyword, @RequestParam String value) {
        try {
            List<Course> result = courseService.getBy(keyword, value);
            if (result.isEmpty()) throw new Exception();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get course by" + keyword, result));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X02", "Data not found"));
        }
    }

}
