package com.maan.crm.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpsRedirectSpec;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.maan.crm.res.ClientDetailsGetAllRes;
import com.maan.crm.res.ClientReferenceGetAllRes;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.InviteDefaultFilterRes;
import com.maan.crm.res.InviteMailRes;
import com.maan.crm.service.CampaignService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "CAMPAIGN : CAMPAIGN MASTER ")
@RequestMapping("/api")
public class CampaignController {

	@Autowired
	private CampaignService Service;
	
	
	// Insert Campaign 
	
	@PostMapping("/savecampaignmaster")
	@ApiOperation(value="This method is to insert Campaign master")
	public ResponseEntity<CommonCrmRes> saveCampaignMaster(@RequestBody CampaignMasterSaveReq req)
	{
		CommonCrmRes data = new CommonCrmRes();
		// Validation
		List<Error> validation = Service.validateCampaignMaster(req);
		if (validation != null && validation.size() != 0) {

			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");

			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {

		CampaignSaveRes res = Service.saveCampaignMaster(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");
		
		if(res!=null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	}
	
	
	// Get All Campaign Master
	
	@PostMapping("/getallcampaignmaster")
	@ApiOperation(value="This Method is to display campaign master")
	public ResponseEntity<CommonCrmRes> campaignDetails(@RequestBody CampaignDetailsReq req){
		CommonCrmRes data = new CommonCrmRes();
		List<CampaignDetailsRes> res = Service.campaignDetails(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");
		
		if(res!=null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
				
	}
	
	
	
	
	// Get Campaign Master
	
	@PostMapping("/getcampaignmaster")
	@ApiOperation(value="This Method is to display campaign master")
	public ResponseEntity<CommonCrmRes> campaign(@RequestBody CampaignDetailsGetReq req){
		CommonCrmRes data = new CommonCrmRes();
		CampaignDetailsRes res = Service.campaign(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");
		
		if(res!=null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
				
	}
	
	// Insert Campaign Details
	
	@PostMapping("/savecampaigndetails")
	@ApiOperation(value="This method is to insert Campaign Details")
	public ResponseEntity<CommonCrmRes> saveCampaignDetails(@RequestBody CampaignDetailsSaveReq req)
	{
		CommonCrmRes data = new CommonCrmRes();
		// Validation
		List<Error> validation = Service.validateCampaignDetails(req);
		if (validation != null && validation.size() != 0) {

			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");

			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {

		CampaignMasterSaveRes res = Service.saveCampaignDetails(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");
		
		if(res!=null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	}
	
	
	// Get Campaign Details
	
	@PostMapping("/getcampaigndetails")
	@ApiOperation(value="This Method is to Get campaign details")
	public ResponseEntity<CommonCrmRes> getcampaign(@RequestBody CampaignGetReq req){
		CommonCrmRes data = new CommonCrmRes();
		CampaignRes res = Service.getcampaign(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");
		
		if(res!=null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
				
	}
	
	//Get All Campaign Details
	
	@PostMapping("/getallcampaigndetails")
	@ApiOperation(value="This Method is to display campaign details")
	public ResponseEntity<CommonCrmRes> getallcampaign(@RequestBody CampaignDetailsGetAllReq req){
		CommonCrmRes data = new CommonCrmRes();
		CampaignRes res = Service.getallcampaign(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");
		
		if(res!=null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
				
	}
	
	// Filter Option
	
	@PostMapping("/filteroption")
	@ApiOperation(value="This Method is to filter Campaign Details")
	public ResponseEntity<CommonCrmRes> filter(@RequestBody CampaignFilterReq req){
		CommonCrmRes data = new CommonCrmRes();
		CampaignFilterRes res = Service.filter(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");
	
		if(res!=null)
		{
			return new ResponseEntity<CommonCrmRes>(data,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	// Reference Clients
	@PostMapping("/getallreferenceclients")
	@ApiOperation(value = "This Method is to Get All Reference Clients")
	public ResponseEntity<CommonCrmRes> getallReferenceClients(@RequestBody ClientGetAllReq req){
		CommonCrmRes data = new CommonCrmRes();
	    List<ClientReferenceGetAllRes> res = Service.getallReferenceClients(req);
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");
		if(res!=null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// Get All Campaign Template
	
		@PostMapping("/getallcampaigntemplate")
		@ApiOperation(value="This Method is to display campaign master")
		public ResponseEntity<CommonCrmRes> campaignTemplate(@RequestBody CampaignTemplateReq req){
			CommonCrmRes data = new CommonCrmRes();
			List<CampaignTemplateRes> res = Service.campaignTemplate(req);
			data.setCommonResponse(res);
			data.setErrorMessage(Collections.emptyList());
			data.setIsError(false);
			data.setMessage("Success");
			
			if(res!=null) {
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
					
		}
		
		// Campaign Invite Save
		
		@PostMapping("/campaignregister")
		@ApiOperation(value="This Method is to invite campaign master")
		public ResponseEntity<CommonCrmRes> invitecampaign(@RequestBody CampaignInviteReq req) {
			
			CommonCrmRes data = new CommonCrmRes();
			CampaignInviteRes res = Service.invitecampaign(req);
			data.setCommonResponse(res);
			data.setErrorMessage(Collections.emptyList());
			data.setIsError(false);
			data.setMessage("Success");
			
			if(res!=null) {
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
			}
		}
		
		
		// Invite Client List Load By Default Filters
		
		@PostMapping("/invitefilters")
		@ApiOperation(value="This Method is to Invite Default Filters")
		public ResponseEntity<CommonCrmRes> invitefilter(@RequestBody InviteDefaultFilterReq req){
			CommonCrmRes data = new CommonCrmRes();
			List<InviteDefaultFilterRes> res = Service.invitefilter(req);
			data.setCommonResponse(res);
			data.setErrorMessage(Collections.emptyList());
			data.setIsError(false);
			data.setMessage("Success");
			if(res!=null) {
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}

		// Invite Mail Sent
		
		@PostMapping("/invitemail")
		@ApiOperation(value="This Method is to Send Invite Mail")
		public ResponseEntity<CommonCrmRes> invitemail(@RequestBody InviteMailReq req){
			CommonCrmRes data = new CommonCrmRes();
			InviteMailRes res = Service.invitemail(req);
			data.setCommonResponse(res);
			data.setErrorMessage(Collections.emptyList());
			data.setIsError(false);
			data.setMessage("Success");
			if(res!=null) {
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}

}
