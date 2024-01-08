package com.github.HusseinHamadi.spring.data.jpa.tutorial.repository;

import com.github.HusseinHamadi.spring.data.jpa.tutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    //----Fetching Data----

    public List<Student> findByFirstName(String firstName);
    public List<Student> findByFirstNameContaining(String keyword);
    public List<Student> findByLastNameNotNull();
    public List<Student> findByGuardianName(String guardianName);
    public List<Student> findByFirstNameAndLastName(String firstName, String lastName);


    //jpql
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);

    //jpql
    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String emailId);

    //native query for complex queries
    @Query(
            value = "select * from tbl_student s where s.email_address=?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);

    //Native named param
    @Query(
            value = "select * from tbl_student s where s.email_address=:emailId",
            nativeQuery = true
    )
    //if the variable above matched the same name as the passed argument then we can omit @Param("emailId")
    Student getStudentByEmailAddressNativeParam(@Param("emailId") String emailId);


    //----Modifying Data----

    //@Transactional goes in the service layer
    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String emailId);
}
