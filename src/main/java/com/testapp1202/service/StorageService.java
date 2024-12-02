package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.Storage;
import com.testapp1202.dto.StorageDTO;
import com.testapp1202.dto.StorageSearchDTO;
import com.testapp1202.dto.StoragePageDTO;
import com.testapp1202.dto.StorageConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface StorageService extends GenericService<Storage, Integer> {

	List<Storage> findAll();

	ResultDTO addStorage(StorageDTO storageDTO, RequestDTO requestDTO);

	ResultDTO updateStorage(StorageDTO storageDTO, RequestDTO requestDTO);

    Page<Storage> getAllStorages(Pageable pageable);

    Page<Storage> getAllStorages(Specification<Storage> spec, Pageable pageable);

	ResponseEntity<StoragePageDTO> getStorages(StorageSearchDTO storageSearchDTO);
	
	List<StorageDTO> convertStoragesToStorageDTOs(List<Storage> storages, StorageConvertCriteriaDTO convertCriteria);

	StorageDTO getStorageDTOById(Integer storageId);







}





