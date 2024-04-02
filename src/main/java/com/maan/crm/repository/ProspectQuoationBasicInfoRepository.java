package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.ProspectQuotationBasicInfo;
import com.maan.crm.bean.ProspectQuotationBasicInfoId;

@Repository
public interface ProspectQuoationBasicInfoRepository extends JpaRepository<ProspectQuotationBasicInfo, ProspectQuotationBasicInfoId>, JpaSpecificationExecutor<ProspectQuotationBasicInfo>{

	List<ProspectQuotationBasicInfo> findByProspectId(Integer prospectId);

	ProspectQuotationBasicInfo findByQuotationId(Integer quotationId);





}
