package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.TicketMasterDetails;
import com.maan.crm.bean.TicketMasterDetailsId;

public interface TicketMasterRepository extends JpaRepository<TicketMasterDetails, TicketMasterDetailsId>  , JpaSpecificationExecutor<TicketMasterDetails>{

	TicketMasterDetails findByTicketId(String ticketId);


	List<TicketMasterDetails> findByBranchCodeAndRegionCodeAndInsCompanyIdOrderByEntryDateDesc(String branchCode,
			String regionCode, String insCompanyId);




}
