package com.testapp1202.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.testapp1202.dao.GenericDAO;
import com.testapp1202.service.GenericService;
import com.testapp1202.service.impl.GenericServiceImpl;
import com.testapp1202.dao.DocumentStoreDAO;
import com.testapp1202.domain.DocumentStore;
import com.testapp1202.dto.DocumentStoreDTO;
import com.testapp1202.dto.DocumentStoreSearchDTO;
import com.testapp1202.dto.DocumentStorePageDTO;
import com.testapp1202.dto.DocumentStoreConvertCriteriaDTO;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import com.testapp1202.service.DocumentStoreService;
import com.testapp1202.util.ControllerUtils;





@Service
public class DocumentStoreServiceImpl extends GenericServiceImpl<DocumentStore, Integer> implements DocumentStoreService {

    private final static Logger logger = LoggerFactory.getLogger(DocumentStoreServiceImpl.class);

	@Autowired
	DocumentStoreDAO documentStoreDao;

	


	@Override
	public GenericDAO<DocumentStore, Integer> getDAO() {
		return (GenericDAO<DocumentStore, Integer>) documentStoreDao;
	}
	
	public List<DocumentStore> findAll () {
		List<DocumentStore> documentStores = documentStoreDao.findAll();
		
		return documentStores;	
		
	}

	public ResultDTO addDocumentStore(DocumentStoreDTO documentStoreDTO, RequestDTO requestDTO) {

		DocumentStore documentStore = new DocumentStore();

		documentStore.setDocumentStoreId(documentStoreDTO.getDocumentStoreId());


		documentStore.setDocumentPath(documentStoreDTO.getDocumentPath());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		documentStore = documentStoreDao.save(documentStore);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<DocumentStore> getAllDocumentStores(Pageable pageable) {
		return documentStoreDao.findAll(pageable);
	}

	public Page<DocumentStore> getAllDocumentStores(Specification<DocumentStore> spec, Pageable pageable) {
		return documentStoreDao.findAll(spec, pageable);
	}

	public ResponseEntity<DocumentStorePageDTO> getDocumentStores(DocumentStoreSearchDTO documentStoreSearchDTO) {
	
			Integer documentStoreId = documentStoreSearchDTO.getDocumentStoreId(); 
 			String documentPath = documentStoreSearchDTO.getDocumentPath(); 
 			String sortBy = documentStoreSearchDTO.getSortBy();
			String sortOrder = documentStoreSearchDTO.getSortOrder();
			String searchQuery = documentStoreSearchDTO.getSearchQuery();
			Integer page = documentStoreSearchDTO.getPage();
			Integer size = documentStoreSearchDTO.getSize();

	        Specification<DocumentStore> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, documentStoreId, "documentStoreId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, documentPath, "documentPath"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("documentPath")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<DocumentStore> documentStores = this.getAllDocumentStores(spec, pageable);
		
		//System.out.println(String.valueOf(documentStores.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(documentStores.getTotalPages()));
		
		List<DocumentStore> documentStoresList = documentStores.getContent();
		
		DocumentStoreConvertCriteriaDTO convertCriteria = new DocumentStoreConvertCriteriaDTO();
		List<DocumentStoreDTO> documentStoreDTOs = this.convertDocumentStoresToDocumentStoreDTOs(documentStoresList,convertCriteria);
		
		DocumentStorePageDTO documentStorePageDTO = new DocumentStorePageDTO();
		documentStorePageDTO.setDocumentStores(documentStoreDTOs);
		documentStorePageDTO.setTotalElements(documentStores.getTotalElements());
		return ResponseEntity.ok(documentStorePageDTO);
	}

	public List<DocumentStoreDTO> convertDocumentStoresToDocumentStoreDTOs(List<DocumentStore> documentStores, DocumentStoreConvertCriteriaDTO convertCriteria) {
		
		List<DocumentStoreDTO> documentStoreDTOs = new ArrayList<DocumentStoreDTO>();
		
		for (DocumentStore documentStore : documentStores) {
			documentStoreDTOs.add(convertDocumentStoreToDocumentStoreDTO(documentStore,convertCriteria));
		}
		
		return documentStoreDTOs;

	}
	
	public DocumentStoreDTO convertDocumentStoreToDocumentStoreDTO(DocumentStore documentStore, DocumentStoreConvertCriteriaDTO convertCriteria) {
		
		DocumentStoreDTO documentStoreDTO = new DocumentStoreDTO();
		
		documentStoreDTO.setDocumentStoreId(documentStore.getDocumentStoreId());

	
		documentStoreDTO.setDocumentPath(documentStore.getDocumentPath());

	

		
		return documentStoreDTO;
	}

	public ResultDTO updateDocumentStore(DocumentStoreDTO documentStoreDTO, RequestDTO requestDTO) {
		
		DocumentStore documentStore = documentStoreDao.getById(documentStoreDTO.getDocumentStoreId());

		documentStore.setDocumentStoreId(ControllerUtils.setValue(documentStore.getDocumentStoreId(), documentStoreDTO.getDocumentStoreId()));

		documentStore.setDocumentPath(ControllerUtils.setValue(documentStore.getDocumentPath(), documentStoreDTO.getDocumentPath()));



        documentStore = documentStoreDao.save(documentStore);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DocumentStoreDTO getDocumentStoreDTOById(Integer documentStoreId) {
	
		DocumentStore documentStore = documentStoreDao.getById(documentStoreId);
			
		
		DocumentStoreConvertCriteriaDTO convertCriteria = new DocumentStoreConvertCriteriaDTO();
		return(this.convertDocumentStoreToDocumentStoreDTO(documentStore,convertCriteria));
	}







}
