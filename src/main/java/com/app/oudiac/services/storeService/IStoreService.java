package com.app.oudiac.services.storeService;

import com.app.oudiac.dtos.storeDtos.StoreRequestDto;
import com.app.oudiac.dtos.storeDtos.StoreResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IStoreService {
    ResponseEntity<StoreResponseDto> addStore(StoreRequestDto requestDto, MultipartFile[] images) throws IOException;

    ResponseEntity<List<StoreResponseDto>> getStores();
}
