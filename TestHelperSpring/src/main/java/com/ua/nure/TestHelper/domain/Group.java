package com.ua.nure.TestHelper.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Table(name = "groups")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(Group.class)
public class Group implements Serializable {

    @Id
    @Column(name = "link")
    private String link;

    @Id
    @Column(name = "id_student")
    @NotBlank
    private String idStudent;

    @Column(name = "enabled")
    @NotNull
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }


}
