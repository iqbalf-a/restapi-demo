package com.enigmacamp.restapidemo.controller;

import com.enigmacamp.restapidemo.model.Course;
import com.enigmacamp.restapidemo.model.request.CourseRequest;
import com.enigmacamp.restapidemo.model.response.ErrorResponse;
import com.enigmacamp.restapidemo.model.response.SuccessResponse;
import com.enigmacamp.restapidemo.service.Iservice.ICourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
@Validated
public class CourseController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ICourseService courseService;

    @GetMapping
    public ResponseEntity getAllCourses() throws Exception {
        List<Course> courseList = courseService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all courses", courseList));

    }

    @PostMapping
    public ResponseEntity createCourse(@Valid @RequestBody CourseRequest courseRequest) throws Exception {

        Course newCourse = modelMapper.map(courseRequest, Course.class);
        Course result = courseService.create(newCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create course", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id) {
        try {
            Course result = courseService.get(id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get course with id", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable("id") String id) throws Exception {
        courseService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new SuccessResponse<>("Success delete course", id));
    }

    @GetMapping(params = {"keyword", "value"})
    @Order(1)
    public ResponseEntity getBy(@RequestParam @NotBlank(message = "{invalid.keyword.required}") String keyword, @RequestParam @NotBlank(message = "{invalid.value.required}") String value) throws Exception {

        List<Course> result = courseService.getBy(keyword, value);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get course by" + keyword, result));

    }

}
