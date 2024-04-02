package com.maan.crm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.ProductDetails;
import com.maan.crm.bean.ProductDetailsId;

public interface ProductDetailsRepository
		extends JpaRepository<ProductDetails, ProductDetailsId>, JpaSpecificationExecutor<ProductDetails> {

	List<ProductDetails> findByProductId(Integer productId);

	List<ProductDetails> findByAmendIdAndEffectiveDateAndProductId(Integer amend, Date eff, Integer pro);

	List<ProductDetails> findByCategoryName(String categoryName);

	List<ProductDetails> findByAmendIdAndEffectiveDateAndProductIdOrderByCategoryIdAsc(Integer amend, Date eff,
			Integer pro);

}
