package com.ua.nure.TestHelper.controller;

import com.ua.nure.TestHelper.domain.Position;
import com.ua.nure.TestHelper.domain.User;
import com.ua.nure.TestHelper.error.CustomErrorType;
import com.ua.nure.TestHelper.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/account")
public class AccountController {

    public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserService userService;

    // request method to create a new account by a guest
    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        if (userService.getById(newUser.getIdUser()) != null) {
            logger.error("username Already exist " + newUser.getUsername());
            return new ResponseEntity<>(
                    new CustomErrorType("user with username " + newUser.getUsername() + "already exist "),
                    HttpStatus.CONFLICT);
        }
        if (newUser.getPosition().equals(Position.STUDENT)) {
            newUser.setPosition(Position.STUDENT);
        } else {
            newUser.setPosition(Position.TEACHER);
        }
        newUser.setPassword(DigestUtils.md5DigestAsHex(newUser.getPassword().getBytes()));
        return new ResponseEntity<User>(userService.addUser(newUser), HttpStatus.CREATED);
    }

    // this is the login api/service
    @CrossOrigin
    @RequestMapping("/login")
    public Principal user(Principal principal) {
        logger.info("user logged " + principal);
        return principal;
    }

    @CrossOrigin
    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public HttpStatus editUser(@RequestBody User editedUSer) {
        System.out.println(editedUSer);
        editedUSer.setPassword(DigestUtils.md5DigestAsHex(editedUSer.getPassword().getBytes()));
        userService.addUser(editedUSer);
        return HttpStatus.ACCEPTED;
    }

}
