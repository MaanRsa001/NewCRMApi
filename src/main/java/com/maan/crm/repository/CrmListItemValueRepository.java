package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.CrmListITemValue;

@Repository
public interface CrmListItemValueRepository  extends JpaRepository<CrmListITemValue, Integer> , JpaSpecificationExecutor<CrmListITemValue>{

	List<CrmListITemValue> findByItemTypeAndStatusOrderByItemCodeAsc(String itemType , String status );
}
