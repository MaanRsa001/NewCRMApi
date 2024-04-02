package com.maan.crm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.DocumentTypeMaster;
import com.maan.crm.bean.DocumentTypeMasterId;
 
public interface DocumentTypeMasterRepository  extends JpaRepository<DocumentTypeMaster,DocumentTypeMasterId> , JpaSpecificationExecutor<DocumentTypeMaster> {

	List<DocumentTypeMaster> findByInsCompanyIdOrderByEntryDate(String insCompanyId);

	List<DocumentTypeMaster> findByDocumentidAndInsCompanyIdAndEffectiveDateBetweenOrderByAmendIdDesc(Integer documentid, String insCompanyId,Date effectiveDatefrom,Date effectiveDateto);
	
	List<DocumentTypeMaster> findByDocumentidAndInsCompanyIdOrderByEntryDate(Integer documentid, String insCompanyId);

	Integer countByInsCompanyIdOrderByEntryDate(String insCompanyId);

}
