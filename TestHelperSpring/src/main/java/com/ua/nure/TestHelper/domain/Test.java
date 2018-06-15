package com.ua.nure.TestHelper.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Table(name = "tests")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(Test.class)
public class Test implements Serializable {

    @Id
    @Column(name = "id_test")
    @NotNull
    private String idTest;

    @Id
    @NotNull
    @Column(name = "id_template")
    private long idTemplate;

    @NotBlank
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public Test setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdTest() {
        return idTest;
    }

    public void setIdTest(String idTest) {
        this.idTest = idTest;
    }

    public long getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(long idTemplate) {
        this.idTemplate = idTemplate;
    }
}
