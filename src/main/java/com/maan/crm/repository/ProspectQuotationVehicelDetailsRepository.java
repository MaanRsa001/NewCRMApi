package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.ProspectQuotationInsurerDetails;
import com.maan.crm.bean.ProspectQuotationInsurerDetailsId;
import com.maan.crm.bean.ProspectQuotationVehicleDetails;
import com.maan.crm.bean.ProspectQuotationVehicleDetailsId;

@Repository
public interface ProspectQuotationVehicelDetailsRepository extends JpaRepository<ProspectQuotationVehicleDetails, ProspectQuotationVehicleDetailsId>, JpaSpecificationExecutor<ProspectQuotationVehicleDetails>{

	List<ProspectQuotationVehicleDetails> findAllByOrderByQuotationVechidDesc();

	List<ProspectQuotationVehicleDetails> findByProspectId(int prospectId);




}
