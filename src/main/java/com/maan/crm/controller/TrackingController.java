package com.maan.crm.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpsRedirectSpec;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.maan.crm.req.TrackingGetReq;
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
import com.maan.crm.res.TrackingGetRes;
import com.maan.crm.res.TrackingGetallRes;
import com.maan.crm.service.CampaignService;
import com.maan.crm.service.TrackingService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "TRACKING : TRACKING MASTER ")
@RequestMapping("/api")
public class TrackingController {

	@Autowired
	private TrackingService Service;
	
	
	@GetMapping("/getalltracking")
	@ApiOperation(value = "This Method is to display all tracking")
	public ResponseEntity<CommonCrmRes> getalltracking() {
		CommonCrmRes data = new CommonCrmRes();
		
		List<TrackingGetallRes> res = Service.getalltracking();
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
		
	@PostMapping("/gettracking")
	@ApiOperation(value= "This method is to display get tracking")
	public ResponseEntity<CommonCrmRes> gettracking(@RequestBody TrackingGetReq req){
		CommonCrmRes data = new CommonCrmRes();
		List<TrackingGetRes> res = Service.gettracking(req);
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