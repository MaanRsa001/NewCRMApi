package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.CampaignDetailsGetAllReq;
import com.maan.crm.req.CampaignDetailsGetReq;
import com.maan.crm.req.CampaignDetailsReq;
import com.maan.crm.req.CampaignDetailsSaveReq;
import com.maan.crm.req.CampaignFilterReq;
import com.maan.crm.req.CampaignGetReq;
import com.maan.crm.req.CampaignInviteReq;
import com.maan.crm.req.CampaignMasterSaveReq;
import com.maan.crm.req.CampaignTemplateReq;
import com.maan.crm.req.ClientGetAllReq;
import com.maan.crm.req.InviteDefaultFilterReq;
import com.maan.crm.req.InviteMailReq;
import com.maan.crm.res.CampaignDetailsRes;
import com.maan.crm.res.CampaignFilterRes;
import com.maan.crm.res.CampaignInviteRes;
import com.maan.crm.res.CampaignMasterSaveRes;
import com.maan.crm.res.CampaignRes;
import com.maan.crm.res.CampaignSaveRes;
import com.maan.crm.res.CampaignTemplateRes;
import com.maan.crm.res.ClientReferenceGetAllRes;
import com.maan.crm.res.InviteDefaultFilterRes;
import com.maan.crm.res.InviteMailRes;
import com.maan.crm.util.error.Error;

public interface CampaignService {

	
	// Campaign Master
	
	List<CampaignDetailsRes> campaignDetails(CampaignDetailsReq req);

	List<Error> validateCampaignMaster(CampaignMasterSaveReq req);

	CampaignSaveRes saveCampaignMaster(CampaignMasterSaveReq req);

	CampaignDetailsRes campaign(CampaignDetailsGetReq req);

	
	// Campaign Details
	
	List<Error> validateCampaignDetails(CampaignDetailsSaveReq req);

	CampaignMasterSaveRes saveCampaignDetails(CampaignDetailsSaveReq req);

	CampaignRes getcampaign(CampaignGetReq req);

	CampaignRes getallcampaign(CampaignDetailsGetAllReq req);

	
	// Filter
	
	CampaignFilterRes filter(CampaignFilterReq req);

	// Reference Clients
	
	List<ClientReferenceGetAllRes> getallReferenceClients(ClientGetAllReq req);

	// Campaign Template
	
	List<CampaignTemplateRes> campaignTemplate(CampaignTemplateReq req);

	CampaignInviteRes invitecampaign(CampaignInviteReq req);

	// Filter Based on Client Type
	List<InviteDefaultFilterRes> invitefilter(InviteDefaultFilterReq req);

	InviteMailRes invitemail(InviteMailReq req);


	

}
