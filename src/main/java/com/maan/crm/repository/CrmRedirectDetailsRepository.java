package com.maan.crm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.CityMaster;
import com.maan.crm.bean.CrmRedirectDetails;
import com.maan.crm.bean.CrmRedirectDetailsId;

public interface CrmRedirectDetailsRepository extends JpaRepository<CrmRedirectDetails, CrmRedirectDetailsId> , JpaSpecificationExecutor<CrmRedirectDetails>{

	CrmRedirectDetails findByScreenName(String screenName);


}
