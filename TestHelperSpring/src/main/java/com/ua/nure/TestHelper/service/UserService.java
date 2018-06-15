package com.ua.nure.TestHelper.service;

import com.ua.nure.TestHelper.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    User addUser(User user);

    void delete(long id);

    User getById(String id);

    User editUser(User user);

    User getUserByLoginAndPass(String email, String password);

    User getUserByLogin(String email);

    List<User> getAll();

    List<User> getAllByLink(String link);

    List<User> getAllActivatedStudent(String idTeacher, String link);

    //void delete(List<User> user);
    List<User> getStudents();

}
