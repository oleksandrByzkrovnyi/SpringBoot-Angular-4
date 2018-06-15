package com.ua.nure.TestHelper.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Table(name = "templates")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Template {

    @Id
    @Column(name = "id_template")
    private long idTemplate;


    @NotBlank
    @Column(name = "id_teacher")
    private String idTeacher;

    @NotBlank
    private String questionNum;
    @NotBlank
    private String answer;


    public long getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(long idTemplate) {
        this.idTemplate = idTemplate;
    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(String questionNum) {
        this.questionNum = questionNum;

    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
