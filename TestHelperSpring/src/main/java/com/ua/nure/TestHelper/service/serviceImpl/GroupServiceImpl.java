package com.ua.nure.TestHelper.service.serviceImpl;


import com.ua.nure.TestHelper.domain.Group;
import com.ua.nure.TestHelper.domain.Link;
import com.ua.nure.TestHelper.domain.User;
import com.ua.nure.TestHelper.repository.GroupRepository;
import com.ua.nure.TestHelper.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "groupService")
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepository groupRepository;


    @Override
    public Group addGroup(Group group) {
        return groupRepository.saveAndFlush(group);
    }

    @Override
    public void delete(long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public Group getById(long id) {
      return  groupRepository.findById(id).get();
    }

    @Override
    public Group getByLink(String link) {
       return groupRepository.findDistinctFirstByLink(link);
    }

    @Override
    public void editGroup(String idTeacher, String link) {
        groupRepository.updateGroup(idTeacher,link);
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public void deleteStudentOfGroup(User users, Link link) {
        groupRepository.deleteByIdStudentAndLink(users.getIdUser(), link.getLink());
    }


    @Override
    public List<Group> getDisableGroup(String user) {
        return groupRepository.getDisabledGroup(user);
    }

    @Override
    public void deleteByLinkAndId(String idStudent, String link) {
        groupRepository.deleteByIdStudentAndLink(idStudent,link);
    }
}
