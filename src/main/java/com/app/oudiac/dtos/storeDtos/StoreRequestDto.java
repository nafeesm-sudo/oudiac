package com.app.oudiac.dtos.storeDtos;

import com.app.oudiac.models.Store;
import com.app.oudiac.models.User;
import com.app.oudiac.models.enums.StoreStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class StoreRequestDto {

    @NotEmpty
    private String storeName;
    @NotEmpty
    private String managerEmail;
    @NotEmpty
    private String storePhone;
    @NotEmpty
    private String storeEmail;
    private String address;
    private String city;

    private String pincode;

    private Double latitude;

    private Double longitude;
//    private String openTime;

    private StoreStatus storeStatus;
    @NotEmpty
    private String storeSku;

    public static Store convertStoreRequestDtoToStore(StoreRequestDto requestDto){
        Store newStore=new Store();
        newStore.setName(requestDto.getStoreName());
        newStore.setAddress(requestDto.getAddress());
        newStore.setCity(requestDto.city);
        newStore.setPincode(requestDto.getPincode());
        newStore.setPhone(requestDto.getStorePhone());
        newStore.setStoreStatus(requestDto.getStoreStatus());
        newStore.setEmail(requestDto.getStoreEmail());
        newStore.setStoreSku(requestDto.getStoreSku());

        newStore.setCreated_at(new Date());
        newStore.setUpdated_at(new Date());
        newStore.setIsDeleted(false);
        return newStore;
    }
}
