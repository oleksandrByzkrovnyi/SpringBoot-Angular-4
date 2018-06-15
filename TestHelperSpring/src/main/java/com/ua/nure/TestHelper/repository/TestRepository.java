package com.ua.nure.TestHelper.repository;

import com.ua.nure.TestHelper.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> getAllByIdTest(String idTest);

    @Query(value = "SELECT * FROM tests WHERE tests.id_template in(SELECT id_template FROM templates WHERE templates.id_teacher = ?1) group by id_test;", nativeQuery = true)
    List<Test> getAllTestTeacher(String idTeacher);

    @Query(value ="SELECT * FROM tests WHERE tests.id_test not In (SELECT id_test from test4groups WHERE id_group = ?1) AND tests.id_template in(SELECT id_template FROM templates WHERE templates.id_teacher = ?2) group by id_test;", nativeQuery = true)
    List<Test> getAllTestTeacherNotInTheGroup(String id_Group, String id_teacher);

    @Query(value = "SELECT * FROM tests WHERE id_test = ?1 GROUP by id_test", nativeQuery = true)
    Test getByIdTest(@NotNull String idTest);
}
