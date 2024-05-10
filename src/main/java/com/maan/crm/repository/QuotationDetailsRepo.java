package com.maan.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maan.crm.bean.QuotationDetails;
import com.maan.crm.bean.QuotationDetailsId;

public interface QuotationDetailsRepo extends JpaRepository<QuotationDetails, QuotationDetailsId>{

	QuotationDetails findByQuoteNo(String string);

	QuotationDetails findByQuoteNoAndClientId(String quoteNo, String clientId);

}
