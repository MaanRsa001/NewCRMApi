package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.BranchMaster;
import com.maan.crm.bean.BranchMasterId;

public interface BranchMasterRepository	extends JpaRepository<BranchMaster, BranchMasterId>, JpaSpecificationExecutor<BranchMaster> {

	BranchMaster findByBranchCode(String branchCode);
	
	List<BranchMaster> findByInsCompanyIdAndStatusOrderByEntryDate(String insCompanyId,String status);

	List<BranchMaster> findByInsCompanyIdOrderByEntryDate(String insCompanyId);
	
	Long countByInsCompanyIdOrderByEntryDate(String insCompanyId);

	List<BranchMaster> findByBranchCodeInOrderByBranchCodeAsc(List<String> branches);


	List<BranchMaster> findByInsCompanyIdAndStatusOrderByBranchNameAsc(String insCompanyId, String string);

}
