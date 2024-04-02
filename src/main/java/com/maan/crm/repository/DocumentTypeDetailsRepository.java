package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.DocumentTypeDetails;
import com.maan.crm.bean.DocumentTypeDetailsId;
 
public interface DocumentTypeDetailsRepository  extends JpaRepository<DocumentTypeDetails,DocumentTypeDetailsId> , JpaSpecificationExecutor<DocumentTypeDetails> {

	List<DocumentTypeDetails> findByRefNo(String refNo);

	

}
