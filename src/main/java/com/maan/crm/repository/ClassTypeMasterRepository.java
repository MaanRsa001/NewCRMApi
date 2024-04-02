package com.maan.crm.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.ClassTypeMaster;
import com.maan.crm.bean.ClassTypeMasterId;



public interface ClassTypeMasterRepository extends JpaRepository<ClassTypeMaster, ClassTypeMasterId> , JpaSpecificationExecutor<ClassTypeMaster>{

	List<ClassTypeMaster> findByClassIdAndEffectiveDateOrderByAmendIdDesc(Integer classId, Date parse);




}
