package com.app.ELearning.repository;

import com.app.ELearning.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Is specialization of @Component annotation which is used to indicate that the class provides
// the mechanism for storage, retrieval, update, delete and search operation on objects.
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findStudentByUsernameAndPassword(String username, String password);
    Optional<Student> findStudentsByUsername(String username);
    //custom native query to fulfil one test case. S.boot JPA-native Query
    @Query(value = "select * from Student", nativeQuery = true)
    List<Student> findAllStudents();
}
