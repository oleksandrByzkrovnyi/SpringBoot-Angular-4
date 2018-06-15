package com.ua.nure.TestHelper.service.serviceImpl;


import com.ua.nure.TestHelper.domain.Test;
import com.ua.nure.TestHelper.domain.Test4Group;
import com.ua.nure.TestHelper.repository.TestRepository;
import com.ua.nure.TestHelper.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "testServiceImpl")

public class TestServiceImpl implements TestService {

    @Autowired
    TestRepository testRepository;

    @Override
    public Test addTest(Test test) {
        return testRepository.saveAndFlush(test);
    }

    @Override
    public void delete(long id) {
        testRepository.deleteById(id);
    }

    @Override
    public List<Test> getById(String idTest) {
        return testRepository.getAllByIdTest(idTest);
    }

    @Override
    public Test editTest(Test test) {
        return testRepository.saveAndFlush(test);
    }

    @Override
    public List<Test> getAll() {
        return testRepository.findAll();
    }

    @Override
    public List<Test> getAllByTeacher(String idTeacher) {
        return testRepository.getAllTestTeacher(idTeacher);
    }

    @Override
    public List<Test> getAllbyTeacherNotIn(String idGroup, String id_teacher) {
        return testRepository.getAllTestTeacherNotInTheGroup(idGroup,id_teacher);
    }

    @Override
    public Test getByIdTest(String idTEst) {
        return testRepository.getByIdTest(idTEst);
    }
}
