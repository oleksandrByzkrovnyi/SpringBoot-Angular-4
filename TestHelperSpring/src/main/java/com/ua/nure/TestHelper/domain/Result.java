package com.ua.nure.TestHelper.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Table(name = "results")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(Result.class)
public class Result implements Serializable{
    @Id
    @NotBlank
    @Column(name = "id_student")
    private String idStudent;
    @Id
    @Column(name = "id_test")
    private String idTest;

    @Id
    @Column(name = "id_template")
    private int idTemplate;

    @Column(name = "answer")
    private boolean answer;

    public int getIdTemplate() {
        return idTemplate;
    }

    public Result setIdTemplate(int idTemplate) {
        this.idTemplate = idTemplate;
        return this;
    }

    public boolean isAnswer() {
        return answer;
    }

    public Result setAnswer(boolean answer) {
        this.answer = answer;
        return this;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getIdTest() {
        return idTest;
    }

    public void setIdTest(String idTest) {
        this.idTest = idTest;
    }
}
