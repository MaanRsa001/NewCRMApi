package com.maan.crm.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.OccupationMaster;
import com.maan.crm.bean.OccupationMasterId;
import com.maan.crm.bean.SourceMaster;
import com.maan.crm.bean.SourceMasterId;

@Repository
public interface SourceMasterRepository extends JpaRepository<SourceMaster, SourceMasterId> , JpaSpecificationExecutor<SourceMaster>{

	List<SourceMaster> findBySourceIdAndEffectiveDateOrderByAmendIdDesc(Integer sourceId, Date parse);






}
