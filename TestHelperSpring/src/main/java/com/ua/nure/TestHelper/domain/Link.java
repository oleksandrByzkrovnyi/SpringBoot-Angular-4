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

@Table(name = "links")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Link {
    @Id
    @NotBlank
    private String link;

    @Column(name = "id_teacher")
    @NotBlank
    private String idTeacher;


    @Column(name = "name")
    @NotBlank
    private String name;

    public String getLink() {
        return link;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
    }
}
