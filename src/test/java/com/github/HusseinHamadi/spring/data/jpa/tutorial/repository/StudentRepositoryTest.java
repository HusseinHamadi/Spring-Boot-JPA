package com.github.HusseinHamadi.spring.data.jpa.tutorial.repository;

import com.github.HusseinHamadi.spring.data.jpa.tutorial.entity.Guardian;
import com.github.HusseinHamadi.spring.data.jpa.tutorial.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    //need a student repository to test it
    @Autowired
    private StudentRepository studentRepository;


    @BeforeEach
    void setUp() {
    }

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("ali@gmail.com")
                .firstName("Ali")
                .lastName("Hamadi")
//                .guardianEmail("hussein@gmail.com")
//                .guardianName("Hussein")
//                .guardianMobile("888888")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){

        Guardian guardian = Guardian.builder()
                .name("Sam")
                .email("sam@gmail.com")
                .mobile("99999999")
                .build();

        Student student= Student.builder()
                .emailId("mohamad@gmail.com")
                .firstName("Mohamad")
                .lastName("Khalaf")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudents(){
        List<Student> studentList=
                studentRepository.findAll();
        System.out.println("student list: "+studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students=
                studentRepository.findByFirstName("Ali");
        System.out.println(students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students=
                studentRepository.findByFirstNameContaining("i");

        System.out.println(students);
    }

    @Test
    public void printStudentWithLastName(){
        List<Student> students=
                studentRepository.findByLastNameNotNull();
        System.out.println(students);
    }

    @Test
    public void printStudentByGuardianName(){
        List<Student> students=
                studentRepository.findByGuardianName("Hussein");
        System.out.println(students);
    }
    @Test
    public void printStudentByFirstNameAndLastName(){
        List<Student> students=
                studentRepository.findByFirstNameAndLastName("Ali", "Hamadi");
        System.out.println(students);
    }

    @Test
    public void printStudentByEmailAddress(){
        Student student=
                studentRepository.getStudentByEmailAddress("ali@gmail.com");
        System.out.println(student);
    }

    @Test
    public void printStudentFirstNameByEmailAddress(){
        String  firstName=
                studentRepository.getStudentFirstNameByEmailAddress("ali@gmail.com");
        System.out.println("firstName"+firstName);
    }

    @Test
    public void printStudentByEmailAddressNative(){
        Student student=
                studentRepository.getStudentByEmailAddressNative("ali@gmail.com");
        System.out.println(student);
    }

    @Test
    public void printStudentByEmailAddressNativeParam(){
        Student student=
                studentRepository.getStudentByEmailAddressNativeParam("ali@gmail.com");
        System.out.println(student);
    }

    @Test
    public void updateStudentNameByEmailId(){

        studentRepository.updateStudentNameByEmailId("John","ali@gmail.com");

    }

}