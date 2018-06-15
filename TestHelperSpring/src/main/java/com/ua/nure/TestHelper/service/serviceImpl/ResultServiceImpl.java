package com.ua.nure.TestHelper.service.serviceImpl;

import com.ua.nure.TestHelper.domain.Result;
import com.ua.nure.TestHelper.repository.ResultRepository;
import com.ua.nure.TestHelper.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "resultService")
public class ResultServiceImpl implements ResultService {

    @Autowired
    ResultRepository resultRepository;

    @Override
    public Result addResult(Result result) {
        return resultRepository.saveAndFlush(result);
    }

    @Override
    public void delete(Result id) {
        resultRepository.delete(id);
    }

    @Override
    public Result getById(long id) {
        return resultRepository.findById(id).get();
    }

    @Override
    public Result editResult(Result result) {
        return null;
    }

    @Override
    public List<Result> getAll() {
        return null;
    }

    @Override
    public List<Result> getAllByIdStudent(String id) {
        return resultRepository.getAllByIdStudent(id);
    }

    @Override
    public List<String> getIdTestbyIdStudent(String id) {
        return resultRepository.getIdTestByStudentId(id);
    }

    @Override
    public List<Result> getAllByStudentAndTest(String idStudent, String IdTest) {
        return resultRepository.getAllByIdStudentAndIdTest(idStudent, IdTest);
    }
}
