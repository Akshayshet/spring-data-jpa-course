package com.example.demo.repository;

import com.example.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudeentRepository extends JpaRepository<Student,Long>{

    @Query("SELECT s FROM Student s WHERE  s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

//    @Query("SELECT s from Student s WHERE s.email = ?1 AND s.firstName = ?2")
    @Query("SELECT s from Student s Where s.email =:email AND s.firstName =:firstName")
    Optional<Student> findStudentByEmailAndFirstName(@Param("email") String email,@Param("firstName") String firstName);

    @Query(name = "select * from student where first_name =:firstName and age =:age", nativeQuery = true)
    List<Student> findStudentsByFirstNameEqualsAndAgeEquals(String firstName, Integer age);

    @Query("SELECT s from Student s Where s.firstName =:firstName AND s.age >:age")
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThan(String firstName, Integer age);

    @Modifying
    @Query("DELETE FROM Student s Where s.id =?1")
    void deleteById(Long id);
}
