package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.ClientCorContact;
import com.maan.crm.bean.ClientCorContactId;





public interface ClientCorContactRepository  extends JpaRepository<ClientCorContact, ClientCorContactId>  , JpaSpecificationExecutor<ClientCorContact>{


	ClientCorContact findByClientRefNoAndClientcorContactId(String clientRefNo, Integer contactId);

	List<ClientCorContact> findByClientRefNoOrderByClientcorContactIdAsc(String clientRefNo);

	List<ClientCorContact> findByClientRefNo(String clientRefNo);
	
	List<ClientCorContact> findByMobileNoLike(String mobileNo);
	
	List<ClientCorContact> findByEmailLike(String email);

}
