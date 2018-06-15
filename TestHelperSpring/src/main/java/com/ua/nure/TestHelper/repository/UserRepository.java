package com.ua.nure.TestHelper.repository;

import com.ua.nure.TestHelper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);


    @Query(value = "SELECT * FROM users,groups WHERE users.id_user = groups.id_student AND groups.link =?1", nativeQuery = true)
    List<User> findAllStudentsByLink(String Link);

    @Query(value = "SELECT * FROM users WHERE users.position = 0", nativeQuery = true)
    List<User> getAllByPosition_Student();

    @Query(value = "SELECt id_user, firstName, lastName,activity, email, password, position  FROM users, groups WHERE groups.enabled = 1 AND groups.link in (select link FROM links WHERE id_teacher = ?1 and link = ?2) AND groups.id_student = users.id_user;", nativeQuery = true)
    List<User> getAllActivatedStudents(String idTeacher, String link);

    User getByIdUser(String idUser);
}
