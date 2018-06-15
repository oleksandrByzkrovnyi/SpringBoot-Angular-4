package com.ua.nure.TestHelper.repository;


import com.ua.nure.TestHelper.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> getAllByIdStudent(String id);

    @Query(value = "SELECT DISTINCT id_test FROM results WHERE id_student = ?1  ;", nativeQuery = true)
    List<String>  getIdTestByStudentId(String studentId);

    List<Result> getAllByIdStudentAndIdTest(@NotBlank String idStudent, String idTest);
}
