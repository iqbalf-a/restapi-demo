package com.enigmacamp.restapidemo.repository;

import com.enigmacamp.restapidemo.model.Course;
import com.enigmacamp.restapidemo.repository.Irepository.ICourseRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository implements ICourseRepository {

    private final List<Course> courses = new ArrayList<>();
    {
        Course course = new Course();
        course.setCourseId("1");
        course.setTitle("Title");
        course.setLink("Ini link");
        course.setDescription("Ini desc");

        courses.add(course);
    }

    @Override
    public List<Course> getAll() throws Exception {
        return courses;
    }

    @Override
    public Course create(Course course) throws Exception {
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
        for (Course course: courses){
            if (course.getCourseId().equals(id)){
                courses.remove(course);
                break;
            }
        }

    }
}
