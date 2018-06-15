package com.ua.nure.TestHelper.service;

import com.ua.nure.TestHelper.domain.Group;
import com.ua.nure.TestHelper.domain.Link;
import com.ua.nure.TestHelper.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GroupService {
    Group addGroup(Group group);

    void delete(long id);

    Group getById(long id);

    Group getByLink(String link);

    void editGroup(String idTeacher, String link);

    List<Group> getAll();

    void deleteStudentOfGroup(User users, Link link);

    List<Group> getDisableGroup(String user);

    void deleteByLinkAndId(String idStudent, String link);

}
