package com.ua.nure.TestHelper.controller;


import com.ua.nure.TestHelper.domain.Template;
import com.ua.nure.TestHelper.domain.Test;
import com.ua.nure.TestHelper.domain.Test4Group;
import com.ua.nure.TestHelper.service.Test4GroupService;
import com.ua.nure.TestHelper.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("tests")
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    Test4GroupService test4GroupService;

    @CrossOrigin
    @RequestMapping(value = "/getTeacherTest", method = RequestMethod.GET)
    public List<Test> getTeacherTest(@RequestParam("user") String userId, @RequestParam("group") String group) {
        try {
            System.out.println(testService.getAllbyTeacherNotIn(group, userId));
            return testService.getAllbyTeacherNotIn(group, userId);
        } catch (NullPointerException e) {
            System.out.println("no no no");
        }
        return null;
    }

    @CrossOrigin
    @RequestMapping(value = "/addOldTest", method = RequestMethod.GET)
    public Test4Group addOldTest(@RequestParam("idGroup") String groupId, @RequestParam("idTest") String idTest) {
        try {
            Test4Group addedTest = new Test4Group();
          //  addedTest.setIdTest(Integer.valueOf(idTest));
            addedTest.setIdTest(idTest);
            addedTest.setIdGroup(groupId);
            return test4GroupService.addTest(addedTest);
        } catch (NullPointerException e) {
            System.out.println("no no no");
        }
        return null;
    }

    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public void addTest(@RequestParam(name = "idTest") String idTest,
                        @RequestParam(name = "name") String name,
                        @RequestParam(name = "template") String Idtemplate) throws IOException {


        Test newTest = new Test();
        newTest.setIdTest(idTest);
        newTest.setName(name);
        newTest.setIdTemplate(Long.parseLong(Idtemplate));
        testService.addTest(newTest);

    }

}
