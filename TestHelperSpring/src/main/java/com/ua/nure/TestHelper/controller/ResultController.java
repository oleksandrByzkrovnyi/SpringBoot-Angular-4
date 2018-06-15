package com.ua.nure.TestHelper.controller;


import com.ua.nure.TestHelper.domain.Result;
import com.ua.nure.TestHelper.domain.Test;
import com.ua.nure.TestHelper.service.ResultService;
import com.ua.nure.TestHelper.service.TestService;
import com.ua.nure.TestHelper.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController {

    @Autowired
    ResultService resultService;

    @Autowired
    TestService testService;

    @CrossOrigin
    @RequestMapping(value = "/getStudentTest", method = RequestMethod.GET)
    public List<Test> getAllTestStudent(@RequestParam(name = "user") String idStudent) {

        List<Test> test = new ArrayList<>();
        for (String idTest : resultService.getIdTestbyIdStudent(idStudent)) {
            System.out.println("Я собрала id testiv");
            test.add(testService.getByIdTest(idTest));
        }
        return test;
    }

    @CrossOrigin
    @RequestMapping(value = "/getStudentResult", method = RequestMethod.GET)
    public ResultData getAllResultStudent(@RequestParam(name = "user") String idUser, @RequestParam(name = "idTest") String idTest) {
        Integer countAll = 0;
        Integer correct = 0;
        for (Result result : resultService.getAllByStudentAndTest(idUser, idTest)) {
            if (result.isAnswer()) {
                correct++;
                countAll++;
            } else {
                countAll++;
            }
        }
        return new ResultData().setNumAll(String.valueOf(countAll)).setNumCorrect(String.valueOf(correct));
    }
}
