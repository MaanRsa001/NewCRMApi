package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.CompanyTypeMaster;
import com.maan.crm.bean.CompanyTypeMasterId;

public interface CompanyTypeMasterRepository	extends JpaRepository<CompanyTypeMaster, CompanyTypeMasterId>, JpaSpecificationExecutor<CompanyTypeMaster> {

	List<CompanyTypeMaster> findByInsCompanyIdOrderByEntryDate(String insCompanyId);

	Long countByInsCompanyIdOrderByEntryDate(String insCompanyId);


}
