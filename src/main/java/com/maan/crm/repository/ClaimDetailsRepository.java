package com.maan.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.ClaimDetails;
import com.maan.crm.bean.ClaimDetailsId;


public interface ClaimDetailsRepository  extends JpaRepository<ClaimDetails, ClaimDetailsId>  , JpaSpecificationExecutor<ClaimDetails>{


}
