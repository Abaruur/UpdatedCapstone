package com.app.ELearning.repository;

import com.app.ELearning.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//Is specialization of @Component annotation which is used to indicate that the class provides
// the mechanism for storage, retrieval, update, delete and search operation on objects.
@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
    //custom native query to fulfil one test case. S.boot JPA-native Query
    @Query(value = "select * from Class", nativeQuery = true)
    List<Class> findAllClasses();
}
