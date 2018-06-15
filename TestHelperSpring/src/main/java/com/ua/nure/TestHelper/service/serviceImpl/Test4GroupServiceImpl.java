package com.ua.nure.TestHelper.service.serviceImpl;

import com.ua.nure.TestHelper.domain.Test4Group;
import com.ua.nure.TestHelper.repository.Test4GroupRepository;
import com.ua.nure.TestHelper.service.Test4GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "test4groupImpl")
public class Test4GroupServiceImpl implements Test4GroupService {

    @Autowired
    Test4GroupRepository test4GroupService;


    @Override
    public Test4Group addTest(Test4Group test) {
        return test4GroupService.saveAndFlush(test);
    }

    @Override
    public void delete(Test4Group test) {
        test4GroupService.delete(test);
    }

    @Override
    public Test4Group getById(long id) {
        return test4GroupService.findById(id).get();
    }

    @Override
    public Test4Group editTest(Test4Group test) {
        return test4GroupService.saveAndFlush(test);
    }

    @Override
    public List<Test4Group> getAll() {
        return getAll();
    }

    @Override
    public List<Test4Group> getAllByLink(String idGroup) {
        return test4GroupService.getAllByIdGroup(idGroup);
    }
}
