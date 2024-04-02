package com.maan.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maan.crm.req.StatusMasterReq;
import com.maan.crm.req.UpdateEnquiryStatusReq;
import com.maan.crm.req.UpdateQuoteStatusReq;
import com.maan.crm.res.DropDownResA;
import com.maan.crm.res.EnquirySuccessRes;
import com.maan.crm.res.QuoteSuccessRes;
import com.maan.crm.res.SuccessRes;

@Service
public interface StatusMasterService {

	List<DropDownResA> getstatusmaster(StatusMasterReq req);

	EnquirySuccessRes updateEnquiryStatus(UpdateEnquiryStatusReq req);

	QuoteSuccessRes updateQuoteStatus(UpdateQuoteStatusReq req);

}
