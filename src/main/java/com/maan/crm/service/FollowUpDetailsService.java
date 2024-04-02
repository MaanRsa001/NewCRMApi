package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.CrmClientSuccessRes;
import com.maan.crm.req.FollowUpDetailsSaveReq;
import com.maan.crm.res.FollowUpDetailsRes;
import com.maan.crm.res.GetAllFollowUpDetailsRes;

public interface FollowUpDetailsService {

	CrmClientSuccessRes save(FollowUpDetailsSaveReq req);

	GetAllFollowUpDetailsRes getfollowupDetails(FollowUpDetailsSaveReq req);

	FollowUpDetailsRes getclientdetailsid(FollowUpDetailsSaveReq req);

	List<FollowUpDetailsRes> getfollowupbypage(FollowUpDetailsSaveReq req);

}
