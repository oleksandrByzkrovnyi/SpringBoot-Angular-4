package com.ua.nure.TestHelper.repository;

import com.ua.nure.TestHelper.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> getAllByIdTeacher(String id);

    Link getByLink(String Link);

    @Transactional
    void deleteByLink(String link);
}
