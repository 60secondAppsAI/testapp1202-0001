package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.UserAccount;
import com.testapp1202.dto.UserAccountDTO;
import com.testapp1202.dto.UserAccountSearchDTO;
import com.testapp1202.dto.UserAccountPageDTO;
import com.testapp1202.dto.UserAccountConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface UserAccountService extends GenericService<UserAccount, Integer> {

	List<UserAccount> findAll();

	ResultDTO addUserAccount(UserAccountDTO userAccountDTO, RequestDTO requestDTO);

	ResultDTO updateUserAccount(UserAccountDTO userAccountDTO, RequestDTO requestDTO);

    Page<UserAccount> getAllUserAccounts(Pageable pageable);

    Page<UserAccount> getAllUserAccounts(Specification<UserAccount> spec, Pageable pageable);

	ResponseEntity<UserAccountPageDTO> getUserAccounts(UserAccountSearchDTO userAccountSearchDTO);
	
	List<UserAccountDTO> convertUserAccountsToUserAccountDTOs(List<UserAccount> userAccounts, UserAccountConvertCriteriaDTO convertCriteria);

	UserAccountDTO getUserAccountDTOById(Integer userAccountId);







}





