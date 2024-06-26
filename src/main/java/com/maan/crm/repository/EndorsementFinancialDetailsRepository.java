/*
 * Java domain class for entity "ClaimLoginMaster" 
 * Created on 2022-05-14 ( Date ISO 2022-05-14 - Time 15:00:24 )
 * Generated by Telosys Tools Generator ( version 3.3.0 )
 */
 
 /*
 * Created on 2022-05-14 ( 15:00:24 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */


package com.maan.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.EndorsementFinancialDetails;
import com.maan.crm.bean.EndorsementFinancialDetailsId;
import com.maan.crm.bean.EndorsementGeneralDetails;
import com.maan.crm.bean.EndorsementGeneralDetailsId;
import com.maan.crm.bean.PolicyNomineeDetails;
import com.maan.crm.bean.PolicyNomineeDetailsId;
import com.maan.crm.bean.ProspectDetails;
import com.maan.crm.bean.ProspectDetailsId;
/**
 * <h2>ClaimLoginMasterRepository</h2>
 *
 * createdAt : 2022-05-14 - Time 15:00:24
 * <p>
 * Description: "ClaimLoginMaster" Repository
 */
 
 
 
public interface EndorsementFinancialDetailsRepository  extends JpaRepository<EndorsementFinancialDetails,EndorsementFinancialDetailsId> , JpaSpecificationExecutor<EndorsementFinancialDetails> {


	List<EndorsementFinancialDetails> findAllByOrderByEndorsmentFinancialIdDesc();




	
	
}
