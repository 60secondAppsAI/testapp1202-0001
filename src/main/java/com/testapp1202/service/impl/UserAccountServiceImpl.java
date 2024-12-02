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
import com.testapp1202.dao.UserAccountDAO;
import com.testapp1202.domain.UserAccount;
import com.testapp1202.dto.UserAccountDTO;
import com.testapp1202.dto.UserAccountSearchDTO;
import com.testapp1202.dto.UserAccountPageDTO;
import com.testapp1202.dto.UserAccountConvertCriteriaDTO;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import com.testapp1202.service.UserAccountService;
import com.testapp1202.util.ControllerUtils;





@Service
public class UserAccountServiceImpl extends GenericServiceImpl<UserAccount, Integer> implements UserAccountService {

    private final static Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);

	@Autowired
	UserAccountDAO userAccountDao;

	


	@Override
	public GenericDAO<UserAccount, Integer> getDAO() {
		return (GenericDAO<UserAccount, Integer>) userAccountDao;
	}
	
	public List<UserAccount> findAll () {
		List<UserAccount> userAccounts = userAccountDao.findAll();
		
		return userAccounts;	
		
	}

	public ResultDTO addUserAccount(UserAccountDTO userAccountDTO, RequestDTO requestDTO) {

		UserAccount userAccount = new UserAccount();

		userAccount.setUserAccountId(userAccountDTO.getUserAccountId());


		userAccount.setUsername(userAccountDTO.getUsername());


		userAccount.setPassword(userAccountDTO.getPassword());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		userAccount = userAccountDao.save(userAccount);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<UserAccount> getAllUserAccounts(Pageable pageable) {
		return userAccountDao.findAll(pageable);
	}

	public Page<UserAccount> getAllUserAccounts(Specification<UserAccount> spec, Pageable pageable) {
		return userAccountDao.findAll(spec, pageable);
	}

	public ResponseEntity<UserAccountPageDTO> getUserAccounts(UserAccountSearchDTO userAccountSearchDTO) {
	
			Integer userAccountId = userAccountSearchDTO.getUserAccountId(); 
 			String username = userAccountSearchDTO.getUsername(); 
 			String password = userAccountSearchDTO.getPassword(); 
 			String sortBy = userAccountSearchDTO.getSortBy();
			String sortOrder = userAccountSearchDTO.getSortOrder();
			String searchQuery = userAccountSearchDTO.getSearchQuery();
			Integer page = userAccountSearchDTO.getPage();
			Integer size = userAccountSearchDTO.getSize();

	        Specification<UserAccount> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, userAccountId, "userAccountId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, username, "username"); 
			
			spec = ControllerUtils.andIfNecessary(spec, password, "password"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("username")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("password")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<UserAccount> userAccounts = this.getAllUserAccounts(spec, pageable);
		
		//System.out.println(String.valueOf(userAccounts.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(userAccounts.getTotalPages()));
		
		List<UserAccount> userAccountsList = userAccounts.getContent();
		
		UserAccountConvertCriteriaDTO convertCriteria = new UserAccountConvertCriteriaDTO();
		List<UserAccountDTO> userAccountDTOs = this.convertUserAccountsToUserAccountDTOs(userAccountsList,convertCriteria);
		
		UserAccountPageDTO userAccountPageDTO = new UserAccountPageDTO();
		userAccountPageDTO.setUserAccounts(userAccountDTOs);
		userAccountPageDTO.setTotalElements(userAccounts.getTotalElements());
		return ResponseEntity.ok(userAccountPageDTO);
	}

	public List<UserAccountDTO> convertUserAccountsToUserAccountDTOs(List<UserAccount> userAccounts, UserAccountConvertCriteriaDTO convertCriteria) {
		
		List<UserAccountDTO> userAccountDTOs = new ArrayList<UserAccountDTO>();
		
		for (UserAccount userAccount : userAccounts) {
			userAccountDTOs.add(convertUserAccountToUserAccountDTO(userAccount,convertCriteria));
		}
		
		return userAccountDTOs;

	}
	
	public UserAccountDTO convertUserAccountToUserAccountDTO(UserAccount userAccount, UserAccountConvertCriteriaDTO convertCriteria) {
		
		UserAccountDTO userAccountDTO = new UserAccountDTO();
		
		userAccountDTO.setUserAccountId(userAccount.getUserAccountId());

	
		userAccountDTO.setUsername(userAccount.getUsername());

	
		userAccountDTO.setPassword(userAccount.getPassword());

	

		
		return userAccountDTO;
	}

	public ResultDTO updateUserAccount(UserAccountDTO userAccountDTO, RequestDTO requestDTO) {
		
		UserAccount userAccount = userAccountDao.getById(userAccountDTO.getUserAccountId());

		userAccount.setUserAccountId(ControllerUtils.setValue(userAccount.getUserAccountId(), userAccountDTO.getUserAccountId()));

		userAccount.setUsername(ControllerUtils.setValue(userAccount.getUsername(), userAccountDTO.getUsername()));

		userAccount.setPassword(ControllerUtils.setValue(userAccount.getPassword(), userAccountDTO.getPassword()));



        userAccount = userAccountDao.save(userAccount);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public UserAccountDTO getUserAccountDTOById(Integer userAccountId) {
	
		UserAccount userAccount = userAccountDao.getById(userAccountId);
			
		
		UserAccountConvertCriteriaDTO convertCriteria = new UserAccountConvertCriteriaDTO();
		return(this.convertUserAccountToUserAccountDTO(userAccount,convertCriteria));
	}







}
