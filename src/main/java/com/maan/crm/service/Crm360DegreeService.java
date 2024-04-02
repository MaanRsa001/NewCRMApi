package com.maan.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maan.crm.req.Client360DegreeReq;
import com.maan.crm.req.FollowUpDetailsReq;
import com.maan.crm.req.Lead360DegreeReq;
import com.maan.crm.req.LeadQuoteDetailsGetReq;
import com.maan.crm.req.Prospect360DegreeReq;
import com.maan.crm.req.ProspectBulkEditReq;
import com.maan.crm.req.ProspectDetailsGetAllReq;
import com.maan.crm.req.ProspectDetailsSaveReq;
import com.maan.crm.req.ProspectOldPolicyDetailsSaveReq;
import com.maan.crm.req.ProspectPaymentGetAllReq;
import com.maan.crm.req.ProspectPaymentGetReq;
import com.maan.crm.req.ProspectPaymentSaveReq;
import com.maan.crm.req.ProspectQuotaionGetReq;
import com.maan.crm.req.ProspectQuotationAddOnSaveReq;
import com.maan.crm.req.ProspectQuotationInsurerGetReq;
import com.maan.crm.req.ProspectQuotationInsurerSaveReq;
import com.maan.crm.req.ProspectQuotationPolicyAccountsSaveReq;
import com.maan.crm.req.ProspectQuotationSaveReq;
import com.maan.crm.req.ProspectQuotationVehicleDetailsGetReq;
import com.maan.crm.req.ProspectQuotationVehicleDetailsSaveReq;
import com.maan.crm.req.ProspectReq;
import com.maan.crm.req.ProspectSearchReq;
import com.maan.crm.req.ProspectsQuotationOtherDetailsSaveReq;
import com.maan.crm.res.Client360DegreeRes;
import com.maan.crm.res.FollowUpDetailsRes;
import com.maan.crm.res.Lead360DegreeRes;
import com.maan.crm.res.LeadQuoteDetailsGetRes;
import com.maan.crm.res.LeadSearchCountRes;
import com.maan.crm.res.Prospect360DegreeRes;
import com.maan.crm.res.ProspectBulkEditRes;
import com.maan.crm.res.ProspectDetailsRes;
import com.maan.crm.res.ProspectGetAllCountRes;
import com.maan.crm.res.ProspectOldPolicyDetailsRes;
import com.maan.crm.res.ProspectPaymentRes;
import com.maan.crm.res.ProspectPaymentSuccessRes;
import com.maan.crm.res.ProspectQuoationRes;
import com.maan.crm.res.ProspectQuotationAddOnRes;
import com.maan.crm.res.ProspectQuotationInsurerRes;
import com.maan.crm.res.ProspectQuotationInsurerSuccessRes;
import com.maan.crm.res.ProspectQuotationPolicyAccountsRes;
import com.maan.crm.res.ProspectQuotationSuccessRes;
import com.maan.crm.res.ProspectQuotationVehicleDetailsRes;
import com.maan.crm.res.ProspectRes;
import com.maan.crm.res.ProspectSearchRes;
import com.maan.crm.res.ProspectsQuotationOtherDetailsRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.util.error.Error;

@Service
public interface Crm360DegreeService {

	Client360DegreeRes clientView(Client360DegreeReq req);

	List<Lead360DegreeRes> leadview(Lead360DegreeReq req);

	List<Prospect360DegreeRes> prospectview(Prospect360DegreeReq req);

	List<FollowUpDetailsRes> getfollowup(FollowUpDetailsReq req);

	}
