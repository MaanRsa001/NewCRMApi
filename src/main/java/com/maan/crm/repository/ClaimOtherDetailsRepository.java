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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.ClaimOtherDetails;
import com.maan.crm.bean.ClaimOtherDetailsId;
import com.maan.crm.bean.CommissionMotorDetails;
import com.maan.crm.bean.CommissionMotorDetailsId;

/**
 * <h2>ClaimLoginMasterRepository</h2>
 *
 * createdAt : 2022-05-14 - Time 15:00:24
 * <p>
 * Description: "ClaimLoginMaster" Repository
 */

public interface ClaimOtherDetailsRepository
		extends JpaRepository<ClaimOtherDetails, ClaimOtherDetailsId>,
		JpaSpecificationExecutor<ClaimOtherDetails> {

	List<ClaimOtherDetails> findAllByOrderByClaimOtherDetailsIdDesc();

}
