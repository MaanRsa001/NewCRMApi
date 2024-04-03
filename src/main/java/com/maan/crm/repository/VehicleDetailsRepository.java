package com.maan.crm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.VehicleDetails;
import com.maan.crm.bean.VehicleDetailsId;

public interface VehicleDetailsRepository extends JpaRepository<VehicleDetails, VehicleDetailsId> , JpaSpecificationExecutor<VehicleDetails>{

	VehicleDetails findByVehChassisNo(String vehChassisNo);

	Page<VehicleDetails> findByCompanyIdAndBranchCode(Pageable paging, String insId, String branchCode);

	VehicleDetails findByVehCode(Integer valueOf);

	VehicleDetails findByLeadId(String leadId);



}
