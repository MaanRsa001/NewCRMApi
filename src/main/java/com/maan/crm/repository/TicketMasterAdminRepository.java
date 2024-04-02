package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.TicketMasterAdmin;
import com.maan.crm.bean.TicketMasterAdminId;
import com.maan.crm.bean.TicketMasterDetails;
import com.maan.crm.bean.TicketMasterDetailsId;

public interface TicketMasterAdminRepository extends JpaRepository<TicketMasterAdmin, TicketMasterAdminId>  , JpaSpecificationExecutor<TicketMasterAdmin>{


	List<TicketMasterAdmin> findByStatusIdAndStatus(Integer statusId, String status);


}
