package com.ua.nure.TestHelper.controller;

import com.ua.nure.TestHelper.domain.Link;
import com.ua.nure.TestHelper.domain.User;
import com.ua.nure.TestHelper.repository.UserRepository;
import com.ua.nure.TestHelper.service.LinkService;
import com.ua.nure.TestHelper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    LinkService linkService;

    @Autowired
    UserService userService;

    @CrossOrigin
    @RequestMapping(value ="/getStudents", method = RequestMethod.GET)
    public List<User> getStudents(@RequestParam(name = "user")String idTeacher, @RequestParam(name = "link") String link) {
        try{
           return userService.getAllActivatedStudent(idTeacher,link);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }


    @CrossOrigin
    @RequestMapping(value ="/getAllStudent", method = RequestMethod.GET)
    public List<User> getAllStudents() {
        try{
            return userService.getStudents();
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

}
