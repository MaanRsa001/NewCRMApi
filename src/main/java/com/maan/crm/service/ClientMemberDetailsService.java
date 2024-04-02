package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.ClientDetailsJsonTemReq;
import com.maan.crm.req.ClientMemberDetailsGetAllReq;
import com.maan.crm.req.ClientMemberDetailsGetReq;
import com.maan.crm.req.ClientMemberDetailsListSaveReq;
import com.maan.crm.res.ClientMemberDetailsGetRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.util.error.Error;


public interface ClientMemberDetailsService {

	List<Error> validateClientMemberDetails(ClientDetailsJsonTemReq req);

	SuccessRes saveClientMemberDetails(ClientDetailsJsonTemReq req);

	ClientMemberDetailsGetRes getClientMemDetailsById(ClientMemberDetailsGetReq req);

	List<ClientMemberDetailsGetRes> getAllClientMemDetails(ClientMemberDetailsGetAllReq req);




}
