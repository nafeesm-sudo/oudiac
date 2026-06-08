package com.app.oudiac.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Store extends BaseModel{

    private String name;

    private String address;

    private String city;

    private Double latitude;

    private Double longitude;

    private boolean active;
}
