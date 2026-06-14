package com.app.oudiac.dtos.storeDtos;

import com.app.oudiac.models.Store;
import com.app.oudiac.models.enums.StoreStatus;
import lombok.Data;

@Data
public class StoreResponseDto {

    private Long id;
    private String storeName;
    private String managerName;
    private String storePhone;
    private String storeEmail;
    private String address;
    private String city;
    private String pincode;

    private Double latitude;

    private Double longitude;
//    private String openTime;

    private StoreStatus storeStatus;
    private Long ordersToday;
    private Double revenueToday;
    private String storeSku;


    public static StoreResponseDto convertStoreToStoreResponseDto(Store store){
        StoreResponseDto response=new StoreResponseDto();
        response.setId(store.getId());
        response.setAddress(store.getAddress());
        response.setCity(store.getCity());
        response.setPincode(store.getPincode());

        response.setManagerName(store.getManager().getName());
        response.setStoreName(store.getName());
        response.setStorePhone(store.getPhone());
        response.setStoreEmail(store.getEmail());
        response.setStoreStatus(store.getStoreStatus());
        response.setStoreSku(store.getStoreSku());

        return response;
    }

    //    ordersToday: 845,
//    revenueToday: "₹1.2L",

//    storeName: "",
//    managerEmail: "",
//    storePhone: "",
//    storeEmail: "",
//    address: "",
//    city: "Bengaluru",
//    pincode: "",
//    openTime: "24/7",
//    storeStatus: "active",
}
