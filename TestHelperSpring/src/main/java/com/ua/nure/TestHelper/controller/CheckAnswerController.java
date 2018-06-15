package com.ua.nure.TestHelper.controller;

import com.ua.nure.TestHelper.domain.Result;
import com.ua.nure.TestHelper.domain.Test;
import com.ua.nure.TestHelper.service.ResultService;
import com.ua.nure.TestHelper.service.TemplateService;
import com.ua.nure.TestHelper.service.TestService;
import com.ua.nure.TestHelper.utils.StudentsAnswer;
import com.ua.nure.TestHelper.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/check")
public class CheckAnswerController {

    @Autowired
    ResultService resultService;

    @Autowired
    TestService testService;

    @Autowired
    TemplateService templateService;

    @CrossOrigin
    @RequestMapping(value = "/sendFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpStatus upload(@RequestParam(value = "image") MultipartFile file,
                             @RequestParam(name = "student") String student,
                             @RequestParam(name = "test") String test) throws IOException {

        if (!resultService.getAllByStudentAndTest(student, test).isEmpty()) {
            return HttpStatus.CONFLICT;
        } else {
            Utils utils = new Utils();
            List<StudentsAnswer> studentsAnswers = utils.getIDMAnswers(utils.getCroppImages(file));
        try {
            for (Test testItem : testService.getById(test)) {
                String questionNum = templateService.getById(testItem.getIdTemplate()).getQuestionNum();
                String questionAnswer = templateService.getById(testItem.getIdTemplate()).getAnswer();

                for (StudentsAnswer answer : studentsAnswers) {
                    if (answer == null) {
                        System.out.println("Answer is null ");
                        Result NullResult = new Result();
                        NullResult.setIdTest(test);
                        System.out.println("Result : IdTest = " + NullResult.getIdTest());
                        NullResult.setIdStudent(student);
                        System.out.println("Result : IdStudent = " + NullResult.getIdStudent());
                        NullResult.setIdTemplate((int) templateService.getById(testItem.getIdTemplate()).getIdTemplate());
                        System.out.println("Result : IdTemplate = " + NullResult.getIdTemplate());
                        NullResult.setAnswer(false);
                        System.out.println("Result : Answer = " + NullResult.isAnswer());
                        resultService.addResult(NullResult);
                        break;
                    } else if (answer.getNum().equals(questionNum) && answer.getAnswer().equals(questionAnswer)) {
                        System.out.println("Answer is true " + answer.toString());
                        Result CorrectResult = new Result();
                        CorrectResult.setIdTest(test);
                        System.out.println("Result : IdTest = " + CorrectResult.getIdTest());
                        CorrectResult.setIdStudent(student);
                        System.out.println("Result : IdStudent = " + CorrectResult.getIdStudent());
                        CorrectResult.setIdTemplate((int) templateService.getById(testItem.getIdTemplate()).getIdTemplate());
                        System.out.println("Result : IdTemplate = " + CorrectResult.getIdTemplate());
                        CorrectResult.setAnswer(true);
                        System.out.println("Result : Answer = " + CorrectResult.isAnswer());
                        resultService.addResult(CorrectResult);
                        break;
                   } else if (answer.getNum().equals(questionNum) && !answer.getAnswer().equals(questionAnswer)) {
                        System.out.println("Answer is false " + answer.toString());
                        Result unCorrectResult = new Result();
                        unCorrectResult.setIdTest(test);
                        System.out.println("Result : IdTest = " + unCorrectResult.getIdTest());
                        unCorrectResult.setIdStudent(student);
                        System.out.println("Result : IdStudent = " + unCorrectResult.getIdStudent());
                        unCorrectResult.setIdTemplate((int) templateService.getById(testItem.getIdTemplate()).getIdTemplate());
                        System.out.println("Result : IdTemplate = " + unCorrectResult.getIdTemplate());
                        unCorrectResult.setAnswer(false);
                        System.out.println("Answer : Answer =  " + unCorrectResult.toString());
                        resultService.addResult(unCorrectResult);
                        break;
                    }
                }

            }
        } catch (Exception ex) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.ACCEPTED;
    }

}
}

