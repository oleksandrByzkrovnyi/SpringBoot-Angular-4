package com.ua.nure.TestHelper.service.serviceImpl;

import com.ua.nure.TestHelper.domain.User;
import com.ua.nure.TestHelper.repository.UserRepository;
import com.ua.nure.TestHelper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getById(String id) {
        return userRepository.getByIdUser(id);
    }

    @Override
    public User editUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getUserByLoginAndPass(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User getUserByLogin(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllByLink(String link) {
        return userRepository.findAllStudentsByLink(link);
    }

    @Override
    public List<User> getAllActivatedStudent(String idTeacher, String link) {
        return userRepository.getAllActivatedStudents(idTeacher,link);
    }

    @Override
    public List<User> getStudents() {
        return userRepository.getAllByPosition_Student();
    }


    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       return userRepository.findByEmail(s);
    }
}
