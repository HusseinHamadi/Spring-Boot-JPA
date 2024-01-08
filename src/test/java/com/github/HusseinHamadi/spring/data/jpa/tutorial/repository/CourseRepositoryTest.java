package com.github.HusseinHamadi.spring.data.jpa.tutorial.repository;

import com.github.HusseinHamadi.spring.data.jpa.tutorial.entity.Course;
import com.github.HusseinHamadi.spring.data.jpa.tutorial.entity.CourseMaterial;
import com.github.HusseinHamadi.spring.data.jpa.tutorial.entity.Student;
import com.github.HusseinHamadi.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println(courses);
    }

    @Test
    public void saveCourse() {
        Teacher teacher = Teacher.builder()
                .firstName("Antoun")
                .lastName("Yaakoub")
                .build();
        Course course = Course.builder()
                .title("Network")
                .teacher(teacher)
                .credit(4)
                .build();
        courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3);
        Pageable firstPageWithTwoRecords =
                PageRequest.of(0, 3);

        List<Course> courses =
                courseRepository.findAll(firstPageWithTwoRecords).getContent();

        long totalElements = courseRepository.findAll(firstPageWithTwoRecords)
                        .getTotalElements();

        long totalPages= courseRepository.findAll(firstPageWithTwoRecords)
                                .getTotalPages();

        System.out.println("Total Pages = " + totalPages);
        System.out.println("Total Elements = " + totalElements);
        System.out.println("course = " + courses);
    }

    @Test
    public void fidAllSorting(){
        Pageable sortByTitle=
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title")
                );
        Pageable sortByCreditDesc=
                PageRequest.of(
                        0,
                        2,
                        Sort.by("credit").descending()
                );
        Pageable sortByTitleAndCreditDesc=
                PageRequest.of(
                        0,
                        2,
                         Sort.by("title").descending()
                                .and(Sort.by("credit"))
                );
        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
    }

    @Test
    public void printFindByTitleContaining(){
        Pageable firstPageTenRecords=
                PageRequest.of(0,10);

        List<Course> courses=
                courseRepository.findByTitleContaining("D", firstPageTenRecords).getContent();

        System.out.println("Courses by title and keyword: "+courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Sara")
                .lastName("Jones")
                .build();
        Student student= Student.builder()
                .emailId("jane@gmail.com")
                .firstName("Jane")
                .lastName("Olive")
                .build();
        Course course = Course.builder()
                .title("AI")
                .teacher(teacher)
                .credit(12)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }
}
