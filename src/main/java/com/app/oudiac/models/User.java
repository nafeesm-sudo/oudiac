package com.app.oudiac.models;

import com.app.oudiac.models.enums.EmailStatus;
import com.app.oudiac.models.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_tbl")
@Getter
@Setter
public class User extends BaseModel{
    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String mobileNumber;
//    private String password;

    @Enumerated(value = EnumType.ORDINAL)
    private EmailStatus emailStatus;

    @Enumerated(value = EnumType.ORDINAL)
    private Role role;
}
