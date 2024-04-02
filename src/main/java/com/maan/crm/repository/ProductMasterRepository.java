package com.maan.crm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.ProductMaster;
import com.maan.crm.bean.ProductMasterId;

public interface ProductMasterRepository
		extends JpaRepository<ProductMaster, ProductMasterId>, JpaSpecificationExecutor<ProductMaster> {

	List<ProductMaster> findByProductIdAndEffectiveDateOrderByAmendIdDesc(Integer productId, Date effectvieDate);

}
