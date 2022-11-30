package com.enigmacamp.restapidemo.repository;

import com.enigmacamp.restapidemo.model.Course;
import com.enigmacamp.restapidemo.repository.Irepository.ICourseRepository;
import com.enigmacamp.restapidemo.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Repository
public class CourseRepository implements ICourseRepository {

    @Autowired
    RandomStringGenerator randomStringGenerator;

    private final List<Course> courses = new ArrayList<>();

    @Override
    public List<Course> getAll() throws Exception {
        return courses;
    }

    @Override
    public Course create(Course course) throws Exception {
        course.setCourseId(randomStringGenerator.random());
        courses.add(course);
        return course;
    }

    @Override
    public Optional<Course> findById(String id) throws Exception {
        for (Course course : courses) {
            if (course.getCourseId().equals(id)) {
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Course course, String id) throws Exception {
        for (Course existingCourse : courses) {
            if (course.getCourseId().equals(id)) {
                existingCourse.setTitle(course.getTitle());
                existingCourse.setDescription(course.getDescription());
                existingCourse.setLink(course.getLink());
                break;
            }
        }
    }

    @Override
    public void delete(String id) throws Exception {
        for (Course course : courses) {
            if (course.getCourseId().equals(id)) {
                courses.remove(course);
                break;
            }
        }

    }

    @Override
    public List<Course> getBy(String key, String value) throws Exception {
        List<Course> courseList = new ArrayList<>();
        String newKey = key.toLowerCase();
        String newValue = value.toLowerCase();
        for (Course course : courses) {
            switch (newKey) {
                case "courseId":
//                    courseList.stream().anyMatch(c -> c.getCourseId().contains(value));
                    if (course.getTitle().contains(newValue)) {
                        courseList.add(course);
                    }
                    break;
                case "title":
                    if (course.getTitle().contains(newValue)) {
                        courseList.add(course);
                        break;
                    }
                case "description":
                    if (course.getDescription().contains(newValue)) {
                        courseList.add(course);
                    }
                    break;
                case "link":
                    if (course.getLink().contains(newValue)) {
                        courseList.add(course);
                    }
                    break;
                default:
                    break;
            }
        }
        return courseList;
    }


}
