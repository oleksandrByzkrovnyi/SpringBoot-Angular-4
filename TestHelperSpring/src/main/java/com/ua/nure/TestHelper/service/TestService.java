package com.ua.nure.TestHelper.service;

import com.ua.nure.TestHelper.domain.Test;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TestService {

    Test addTest(Test test);

    void delete(long id);

    List<Test> getById(String test);

    Test editTest(Test test);

    List<Test> getAll();

    List<Test> getAllByTeacher(String idTeacher);

    List<Test> getAllbyTeacherNotIn(String idGroup, String id_teacher);

    Test getByIdTest(String idTEst);

}
