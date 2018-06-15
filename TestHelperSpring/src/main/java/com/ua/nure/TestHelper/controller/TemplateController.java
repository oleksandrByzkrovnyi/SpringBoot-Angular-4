package com.ua.nure.TestHelper.controller;


import com.ua.nure.TestHelper.domain.Link;
import com.ua.nure.TestHelper.domain.Template;
import com.ua.nure.TestHelper.domain.User;
import com.ua.nure.TestHelper.repository.TemplateRepository;
import com.ua.nure.TestHelper.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
public class TemplateController {

    @Autowired
    TemplateService templateService;


    @CrossOrigin
    @RequestMapping(value ="/addTemplate", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public Template addTempl(@RequestBody Template template) {
        try{
            return templateService.addTemplate(template);
        }catch (NullPointerException e){
            System.out.println("no no no");
        }
        return null;
    }

    @CrossOrigin
    @RequestMapping(value ="/getTemplates", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Template> getTemplates(@RequestBody User user) {
        try{
            System.out.println(templateService.getAllByTeacherId(user.getIdUser()));
            return templateService.getAllByTeacherId(user.getIdUser());
        }catch (NullPointerException e){
            System.out.println("no no no");
        }
        return null;
    }


    @CrossOrigin
    @RequestMapping(value ="/deleteTemplate", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTempl(@RequestBody Template template) {
        try{
            templateService.delete(template);
        }catch (NullPointerException e){
            System.out.println("no no no");
        }

    }

}
