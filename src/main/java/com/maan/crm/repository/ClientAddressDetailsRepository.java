package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.ClientAddressDetails;
import com.maan.crm.bean.ClientAddressDetailsId;
import com.maan.crm.bean.ClientCorContact;



@Repository
public interface ClientAddressDetailsRepository  extends JpaRepository<ClientAddressDetails, ClientAddressDetailsId>  , JpaSpecificationExecutor<ClientAddressDetails>{

	

	List<ClientAddressDetails> findByClientRefNo(String clientRefNo);

	ClientAddressDetails findByClientRefNoAndClientAddressId(String clientRefNo, Integer addressId);

	List<ClientAddressDetails> findByClientRefNoOrderByAddresTypeIdAsc(String clientRefNo);

	List<ClientAddressDetails> findByMobileNoLike(String searchValue);
	
	List<ClientAddressDetails> findByEmailIdLike(String emailId);

	ClientAddressDetails findByClientRefNoAndAddresTypeId(String clientRefNo, Integer addresTypeId);

}
