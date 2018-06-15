package com.ua.nure.TestHelper.repository;

import com.ua.nure.TestHelper.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findDistinctByLink(String link);

    Group findDistinctFirstByLink(String link);

    void deleteGroupByIdStudent(String idStudent);

    @Query(value = "SELECT * FROM groups WHERE enabled = 0 AND link in (SELECT link FROM links WHERE id_teacher = ?1);", nativeQuery = true)
    List<Group> getDisabledGroup(String user);

    @Modifying
    @Query(value = "update groups SET enabled = 1 where groups.id_student  =  ?1 AND groups.link =  ?2 ;", nativeQuery = true)
    void updateGroup(String idStudent, String link);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM groups WHERE groups.id_student = ?1 AND groups.link  =  ?2  ;", nativeQuery = true)
    void deleteByIdStudentAndLink(@NotBlank String idStudent, String link);
}
