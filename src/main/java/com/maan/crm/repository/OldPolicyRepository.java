package com.maan.crm.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.CityMaster;
import com.maan.crm.bean.CityMasterId;
import com.maan.crm.bean.OccupationMaster;
import com.maan.crm.bean.OccupationMasterId;
import com.maan.crm.bean.OldPolicyDetails;
import com.maan.crm.bean.VehicleDetails;

public interface OldPolicyRepository extends JpaRepository<OldPolicyDetails, String> , JpaSpecificationExecutor<OldPolicyDetails>{




	OldPolicyDetails findByOldPolicyNo(String oldPolicyNo);

	List<OldPolicyDetails> OrderByEntryDateAsc();

	Page<OldPolicyDetails> findByCompanyIdAndBranchCode(Pageable paging, String insId, String branchCode);







}
