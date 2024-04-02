package com.maan.crm.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.ClientMemberDetails;
import com.maan.crm.bean.ClientMemberDetailsId;


public interface ClientMemberDetailsRepository  extends JpaRepository<ClientMemberDetails, ClientMemberDetailsId>  , JpaSpecificationExecutor<ClientMemberDetails>{

	List<ClientMemberDetails> findByClientRefNo(String clientRefNo);

	ClientMemberDetails findByClientRefNoAndClientMemberId(String clientRefNo, Integer memberId);

	List<ClientMemberDetails> findByClientRefNoOrderByClientMemberIdAsc(String clientRefNo);


	


}
