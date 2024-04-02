package com.maan.crm.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.CityMaster;
import com.maan.crm.bean.CityMasterId;
import com.maan.crm.bean.OccupationMaster;
import com.maan.crm.bean.OccupationMasterId;

public interface CityMasterRepository extends JpaRepository<CityMaster, CityMasterId> , JpaSpecificationExecutor<CityMaster>{

	List<CityMaster> findByCityIdAndEffectiveDateOrderByAmendIdDesc(Integer cityId, Date parse);




}
