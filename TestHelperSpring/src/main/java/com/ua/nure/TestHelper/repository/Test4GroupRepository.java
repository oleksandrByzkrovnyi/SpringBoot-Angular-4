package com.ua.nure.TestHelper.repository;


import com.ua.nure.TestHelper.domain.Test4Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Test4GroupRepository extends JpaRepository<Test4Group, Long> {
    List<Test4Group> getAllByIdGroup(String idGroup);

}
