package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.ProspectOldPolicyDetails;
import com.maan.crm.bean.ProspectOldPolicyDetailsId;


public interface ProspectOldPolicyDetailsRepository  extends JpaRepository<ProspectOldPolicyDetails, ProspectOldPolicyDetailsId>  , JpaSpecificationExecutor<ProspectOldPolicyDetails>{

	List<ProspectOldPolicyDetails> findAllByOrderByOldPolicyIdDesc();

	List<ProspectOldPolicyDetails> findByProspectId(int prospectId);

}
