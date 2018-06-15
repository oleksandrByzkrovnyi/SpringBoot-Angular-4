package com.ua.nure.TestHelper.service;


import com.ua.nure.TestHelper.domain.Result;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ResultService {

    Result addResult(Result result);

    void delete(Result id);

    Result getById(long id);

    Result editResult(Result result);

    List<Result> getAll();

    List<Result> getAllByIdStudent(String id);

    List<String> getIdTestbyIdStudent(String id);

    List<Result> getAllByStudentAndTest(String idStudent, String IdTest);

}
