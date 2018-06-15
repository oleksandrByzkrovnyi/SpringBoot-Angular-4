package com.ua.nure.TestHelper.service;

import com.ua.nure.TestHelper.domain.Template;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface TemplateService {

    Template addTemplate(Template template);

    void delete(Template template);

    Template getById(long id);

    Template editTemplate(Template template);

    List<Template> getAll();

    List<Template> getAllByTeacherId(String id);
}
