package com.maan.crm.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.ManuYearMaster;
import com.maan.crm.bean.ManuYearMasterId;
import com.maan.crm.bean.VehicleTypeMaster;
import com.maan.crm.bean.VehicleTypeMasterId;

public interface ManuYearMasterRepository extends JpaRepository<ManuYearMaster, ManuYearMasterId> , JpaSpecificationExecutor<ManuYearMaster>{

	List<ManuYearMaster> findByYearAndEffectiveDateOrderByAmendIdDesc(Integer year, Date effectiveDate);



}
