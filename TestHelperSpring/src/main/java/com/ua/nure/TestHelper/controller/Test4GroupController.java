package com.ua.nure.TestHelper.controller;


import com.ua.nure.TestHelper.domain.Link;
import com.ua.nure.TestHelper.domain.Test;
import com.ua.nure.TestHelper.domain.Test4Group;
import com.ua.nure.TestHelper.service.Test4GroupService;
import com.ua.nure.TestHelper.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test4Group")
public class Test4GroupController {


    @Autowired
    Test4GroupService test4GroupService;

    @Autowired
    TestService testService;


    @CrossOrigin
    @RequestMapping(value = "/getTests", method = RequestMethod.GET)
    public List<List<Test>> getTest(@RequestParam("link") String link) {
        try {

            List<Test4Group> test4GroupList = test4GroupService.getAllByLink(link);
            List<List<Test>> groupTest = new ArrayList<>();
            for (Test4Group test : test4GroupList) {
                groupTest.add(testService.getById(test.getIdTest()));
            }
            return groupTest;
        } catch (NullPointerException e) {
            System.out.println("no no no");
        }
        return null;
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteGroupTest", method = RequestMethod.GET)
    public void getTest(@RequestParam("idGroup") String idGroup, @RequestParam("idTest") String idTest) {
        try {
             Test4Group deleteTest = new Test4Group();
           //  deleteTest.setIdTest(Integer.parseInt(idTest));
            deleteTest.setIdTest(idTest);
             deleteTest.setIdGroup(idGroup);
             test4GroupService.delete(deleteTest);
        } catch (NullPointerException e) {
            System.out.println("no no no");
        }

    }
}
