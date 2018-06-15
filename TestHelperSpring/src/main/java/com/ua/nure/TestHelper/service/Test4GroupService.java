package com.ua.nure.TestHelper.service;

import com.ua.nure.TestHelper.domain.Test4Group;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface Test4GroupService {

    Test4Group addTest(Test4Group test);

    void delete(Test4Group id);

    Test4Group getById(long id);

    Test4Group editTest(Test4Group test);

    List<Test4Group> getAll();

    List<Test4Group> getAllByLink(String idGroup);
}
