package com.ua.nure.TestHelper.service.serviceImpl;


import com.ua.nure.TestHelper.domain.Template;
import com.ua.nure.TestHelper.repository.TemplateRepository;
import com.ua.nure.TestHelper.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "templateService")
public class TemplateServiseImpl implements TemplateService {

    @Autowired
    TemplateRepository templateRepository;

    @Override
    public Template addTemplate(Template template) {
        return templateRepository.saveAndFlush(template);
    }

    @Override
    public void delete(Template templ) {
        templateRepository.delete(templ);
    }

    @Override
    public Template getById(long id) {
       return templateRepository.findById(id).get();
    }

    @Override
    public Template editTemplate(Template template) {
        return templateRepository.saveAndFlush(template);
    }

    @Override
    public List<Template> getAll() {
        return templateRepository.findAll();
    }

    @Override
    public List<Template> getAllByTeacherId(String id) {
        return templateRepository.getAllByIdTeacher(id);
    }
}
