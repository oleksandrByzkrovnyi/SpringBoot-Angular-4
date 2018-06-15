package com.ua.nure.TestHelper.controller;

import com.ua.nure.TestHelper.domain.Group;
import com.ua.nure.TestHelper.domain.Link;
import com.ua.nure.TestHelper.domain.User;
import com.ua.nure.TestHelper.service.GroupService;
import com.ua.nure.TestHelper.service.LinkService;
import com.ua.nure.TestHelper.service.UserService;
import com.ua.nure.TestHelper.utils.DisableStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupsController {

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @Autowired
    LinkService linkService;

    @CrossOrigin
    @RequestMapping(value = "/getAllGroups", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Group> getGroups(@RequestBody User user) {
        List<Link> linkList = linkService.getAllByIdTeacher(user.getIdUser());
        List<Group> groupList = new ArrayList<>();
        for (Link item : linkList) {
            Group group = groupService.getByLink(item.getLink());
            if (group != null) {
                groupList.add(group);
            }
        }
        return groupList;
    }

    @Transactional
    @CrossOrigin
    @RequestMapping(value = "/deleteStudents", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void deleteStudents(@RequestParam(name = "user") String user, @RequestParam(name = "link") String link) {

        groupService.deleteByLinkAndId(user, link);


    }

    @CrossOrigin
    @RequestMapping(value = "/addToGroup", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Group addGroup(@RequestParam(name = "user") String user, @RequestParam(name = "link") String link) {
        for (Group group : groupService.getAll()) {
            if (group.getIdStudent().equals(user) && group.getLink().equals(link)) {
                return null;
            }
        }
        Group newGroup = new Group();
        newGroup.setIdStudent(user);
        newGroup.setLink(link);
        return groupService.addGroup(newGroup);
    }

    @CrossOrigin
    @RequestMapping(value = "/getDisableStudent", method = RequestMethod.GET)
    public List<DisableStudent> getAllDisableGroup(@RequestParam(name = "user") String user) {
        List<DisableStudent> data = new ArrayList<>();
        List<Group> disabledGroup = groupService.getDisableGroup(user);
        List<Link> groups = linkService.getAll();
        List<User> students = userService.getStudents();

        for (Group group : disabledGroup) {
            for (Link link : groups) {
                if (group.getLink().equals(link.getLink())) {
                    for (User student : students) {
                        if (student.getIdUser().equals(group.getIdStudent())) {
                            data.add(new DisableStudent()
                                    .setIdStudent(student.getIdUser())
                                    .setNameGroup(link.getName())
                                    .setNameStudent(student.getLastName() + " " + student.getFirstName())
                                    .setLink(link.getLink()));
                        }
                    }
                }
            }
        }
        return data;

    }

    @Transactional
    @CrossOrigin
    @RequestMapping(value = "/acceptStudent", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addStudent(@RequestParam(name = "user") String user, @RequestParam(name = "link") String link) {

        groupService.editGroup(user, link);

    }

    @Transactional
    @CrossOrigin
    @RequestMapping(value = "/deniedStudent", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void diniedStudent(@RequestParam(name = "user") String user, @RequestParam(name = "link") String link) {

        groupService.deleteByLinkAndId(user, link);

    }


}
