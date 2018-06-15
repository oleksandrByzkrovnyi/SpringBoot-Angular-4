package com.ua.nure.TestHelper.controller;


import com.ua.nure.TestHelper.domain.Link;
import com.ua.nure.TestHelper.domain.User;
import com.ua.nure.TestHelper.service.GroupService;
import com.ua.nure.TestHelper.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/links")
public class LinkController {

    @Autowired
    GroupService groupService;

    @Autowired
    LinkService linkService;

    @CrossOrigin
    @RequestMapping(value = "/getAllGroupsByTeacher", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Link> getGroups(@RequestBody User user) {
        try {
            return linkService.getAllByIdTeacher(user.getIdUser());
        } catch (NullPointerException e) {
            System.out.println("this teacher have not links");
        }
        return null;
    }

    @CrossOrigin
    @RequestMapping(value = "/addGroup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Link addGroup(@RequestBody Link link) {
        try {
            return linkService.addLink(link);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    @CrossOrigin
    @RequestMapping(value = "/getGroupInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Link getGroupInfo(@RequestBody Link link) {
        try {
            return linkService.getByLink(link.getLink());
        } catch (Exception e) {
            System.out.println("Такого чувака нет");
        }
        return null;
    }


    @CrossOrigin
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody Link link) {
        try {
            linkService.deleteByLink(link.getLink());
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    @CrossOrigin
    @RequestMapping(value = "/getAllGroups", method = RequestMethod.GET)
    public List<Link> getAllLink() {
        try {
            return linkService.getAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
