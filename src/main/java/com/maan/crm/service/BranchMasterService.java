package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.BranchMasterReq;
import com.maan.crm.res.BranchMasterRes;
import com.maan.crm.res.SuccessRes;

public interface BranchMasterService {

	List<BranchMasterRes> getall(BranchMasterReq req);

	BranchMasterRes get(BranchMasterReq req);

	SuccessRes insertBranch(BranchMasterReq req);


}
