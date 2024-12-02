package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.DocumentStore;
import com.testapp1202.dto.DocumentStoreDTO;
import com.testapp1202.dto.DocumentStoreSearchDTO;
import com.testapp1202.dto.DocumentStorePageDTO;
import com.testapp1202.dto.DocumentStoreConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DocumentStoreService extends GenericService<DocumentStore, Integer> {

	List<DocumentStore> findAll();

	ResultDTO addDocumentStore(DocumentStoreDTO documentStoreDTO, RequestDTO requestDTO);

	ResultDTO updateDocumentStore(DocumentStoreDTO documentStoreDTO, RequestDTO requestDTO);

    Page<DocumentStore> getAllDocumentStores(Pageable pageable);

    Page<DocumentStore> getAllDocumentStores(Specification<DocumentStore> spec, Pageable pageable);

	ResponseEntity<DocumentStorePageDTO> getDocumentStores(DocumentStoreSearchDTO documentStoreSearchDTO);
	
	List<DocumentStoreDTO> convertDocumentStoresToDocumentStoreDTOs(List<DocumentStore> documentStores, DocumentStoreConvertCriteriaDTO convertCriteria);

	DocumentStoreDTO getDocumentStoreDTOById(Integer documentStoreId);







}





