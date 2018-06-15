package com.ua.nure.TestHelper.repository;

import com.ua.nure.TestHelper.domain.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {
    List<Template> getAllByIdTeacher(String idTeacher);
}
