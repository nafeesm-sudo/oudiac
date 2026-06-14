package com.app.oudiac.models;

import com.app.oudiac.models.enums.StoreStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Store extends BaseModel{

    private String name;
    private String email;

    private String address;

    private String city;
    private String pincode;

    private Double latitude;

    private Double longitude;

    @ManyToOne
    private User manager;

    private String phone;
    @Enumerated(value = EnumType.STRING)
    private StoreStatus storeStatus;
    private String storeSku;
    private String storeImageUrl;

//    ordersToday: 845,
//    revenueToday: "₹1.2L",
}
