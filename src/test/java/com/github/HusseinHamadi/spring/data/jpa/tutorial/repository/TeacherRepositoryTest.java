package com.github.HusseinHamadi.spring.data.jpa.tutorial.repository;

import com.github.HusseinHamadi.spring.data.jpa.tutorial.entity.Course;
import com.github.HusseinHamadi.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;


    @Test
    public void saveTeacher(){

//        Course course = Course.builder()
//                .title("DBA")
//                .credit(5)
//                .build();
//        Course course1 = Course.builder()
//                .title("Java")
//                .credit(5)
//                .build();
//        Course course2 = Course.builder()
//                .title("Python")
//                .credit(4)
//                .build();
//        Course course3 = Course.builder()
//                .title("Assembly")
//                .credit(3)
//                .build();

        Teacher teacher =
                Teacher.builder()
//                        .courses(List.of(course,course1,course2,course3))
                        .firstName("Jack")
                        .lastName("Black")
                        .build();
        teacherRepository.save(teacher);
    }

}