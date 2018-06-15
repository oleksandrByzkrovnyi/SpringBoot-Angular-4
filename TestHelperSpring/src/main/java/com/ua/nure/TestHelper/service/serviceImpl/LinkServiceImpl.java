package com.ua.nure.TestHelper.service.serviceImpl;


import com.ua.nure.TestHelper.domain.Link;
import com.ua.nure.TestHelper.repository.LinkRepository;
import com.ua.nure.TestHelper.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "linkService")
public class LinkServiceImpl implements LinkService {

    @Autowired
    LinkRepository linkRepository;

    @Override
    public Link addLink(Link link) {
        return linkRepository.saveAndFlush(link);
    }



    @Override
    public void delete(long id) {
        linkRepository.deleteById(id);
    }

    @Override
    public Link getById(long id) {
        return linkRepository.findById(id).get();
    }

    @Override
    public Link editLink(Link group) {
        return linkRepository.saveAndFlush(group);
    }

    @Override
    public List<Link> getAllByIdTeacher(String id) {
       return linkRepository.getAllByIdTeacher(id);
    }

    @Override
    public List<Link> getAll() {
        return linkRepository.findAll();
    }

    @Override
    public Link getByLink(String link) {
        return  linkRepository.getByLink(link);
    }

    @Override
    public void deleteByLink(String link) {
         linkRepository.deleteByLink(link);
    }
}
