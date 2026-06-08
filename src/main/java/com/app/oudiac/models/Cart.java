package com.app.oudiac.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart extends BaseModel{
    @ManyToOne
    private User user;
}
