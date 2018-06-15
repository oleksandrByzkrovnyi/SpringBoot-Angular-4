package com.ua.nure.TestHelper.service;


import com.ua.nure.TestHelper.domain.Link;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LinkService {
    Link addLink(Link link);

    void delete(long id);

    Link getById(long id);

    Link editLink(Link group);

    List<Link> getAllByIdTeacher(String id);

    List<Link> getAll();

    Link getByLink(String link);

    void deleteByLink(String link);
}
