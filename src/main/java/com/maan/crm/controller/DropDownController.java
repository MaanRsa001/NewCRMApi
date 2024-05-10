package com.maan.crm.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maan.crm.req.BranchMasterDropdownReq;
import com.maan.crm.req.BroughtDropDownReq;
import com.maan.crm.req.LivDropDownReq;
import com.maan.crm.req.UnderwriterReq;
import com.maan.crm.res.AssigntoGroupRes;
import com.maan.crm.res.AssigntoUserRes;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.DropDownResA;
import com.maan.crm.service.DropDownService;
import com.maan.crm.service.PrintReqService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "DROP DOWN : Normal Drop Down ", description = "API's")
@RequestMapping("/dropdown")
public class DropDownController {

	@Autowired
	private DropDownService dropDownService;
	@Autowired
	private PrintReqService reqPrinter;

	// Client Type
	@GetMapping("/clienttypes")
	@ApiOperation(value = "This method is to Client Types Drop Down")

	public ResponseEntity<CommonCrmRes> getClientTypes() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getClientTypes();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}
	
	// Client Type
		@GetMapping("/mobilecodes")
		@ApiOperation(value = "This method is to Client Types Drop Down")

		public ResponseEntity<CommonCrmRes> getMobileCodes() {

			CommonCrmRes data = new CommonCrmRes();

			// Save
			List<DropDownRes> res = dropDownService.getMobileCodes();
			data.setCommonResponse(res);
			data.setIsError(false);
			data.setErrorMessage(Collections.emptyList());
			data.setMessage("Success");

			if (res != null) {
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

		}

	// Client Type
	@GetMapping("/companytypes")
	@ApiOperation(value = "This method is to Client Types Drop Down")

	public ResponseEntity<CommonCrmRes> getCompanyTypes() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getCompanyTypes();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Name Title

	@GetMapping("/titletypes")
	@ApiOperation(value = "This method is to Name Title Types Drop Down")

	public ResponseEntity<CommonCrmRes> gettitleTypes() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getTitleTypes();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Gender

	@GetMapping("/gender")
	@ApiOperation(value = "This method is to Gender Types Drop Down")

	public ResponseEntity<CommonCrmRes> getgender() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getgender();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Marital Status
	@GetMapping("/maritalstatus")
	@ApiOperation(value = "This method is to Marital Status Types Drop Down")

	public ResponseEntity<CommonCrmRes> getmaritalStatus() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getmaritalStatus();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Is Group Client

	@GetMapping("/isgroupclient")
	@ApiOperation(value = "This method is to Is Group Client Types Drop Down")

	public ResponseEntity<CommonCrmRes> getgroupclient() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getgroupclient();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Annual Income
	@GetMapping("/annualincome")
	@ApiOperation(value = "This method is to Annual Income Types Drop Down")

	public ResponseEntity<CommonCrmRes> getannualincome() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getannualincome();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Business Type

	@GetMapping("/businesstype")
	@ApiOperation(value = "This method is to Business Types Drop Down")

	public ResponseEntity<CommonCrmRes> getbusinesstype() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getbusinesstype();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Set Follow Up Date

	@GetMapping("/followupdate")
	@ApiOperation(value = "This method is to Follow Up Date Drop Down")

	public ResponseEntity<CommonCrmRes> getfollowupdate() {
		CommonCrmRes data = new CommonCrmRes();
		List<DropDownRes> res = dropDownService.getfollowupdate();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");
		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// Follow Up Next Year

	@GetMapping("/followupnextyear")
	@ApiOperation(value = "This method is to Follow Up Next Year Drop Down")

	public ResponseEntity<CommonCrmRes> getfollowupnextyear() {
		CommonCrmRes data = new CommonCrmRes();
		List<DropDownRes> res = dropDownService.getfollowupnextyear();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");
		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// Follow Up Concluded

	@GetMapping("/followupconcluded")
	@ApiOperation(value = "This method is to Follow Up Concluded Drop Down")

	public ResponseEntity<CommonCrmRes> getfollowupconcluded() {
		CommonCrmRes data = new CommonCrmRes();
		List<DropDownRes> res = dropDownService.getfollowupconcluded();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");
		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// Individual Address
	@GetMapping("/individualaddress")
	public ResponseEntity<CommonCrmRes> getIndividualAddress() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getIndividualAddress();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Corporate Address
	@GetMapping("/corporateaddress")
	public ResponseEntity<CommonCrmRes> getCorporateAddress() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getCorporateAddress();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Will Provide Reference
	@GetMapping("/willprovidereference")
	public ResponseEntity<CommonCrmRes> getWillProvideReference() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getWillProvideReference();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Classification
	@GetMapping("/classification")
	public ResponseEntity<CommonCrmRes> getClassification() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getClassification();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Manufacture_Year

	@GetMapping("/manufactureyear")
	public ResponseEntity<CommonCrmRes> getManufactureYear() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getManufactureYear();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Fuel Type
	@GetMapping("/fueltype")
	public ResponseEntity<CommonCrmRes> getFuelType() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getFuelType();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Prospect Summary
	@GetMapping("/prospectsummary")
	public ResponseEntity<CommonCrmRes> getProspectSummary() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getProspectSummary();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Summary Period

	@GetMapping("/summaryperiod")
	public ResponseEntity<CommonCrmRes> getSummaryPeriod() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getSummaryPeriod();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Prospect Summary Day Wise

	@GetMapping("/prospectsummarydaywise")
	public ResponseEntity<CommonCrmRes> getProspectSummaryDayWise() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getProspectSummaryDayWise();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Vehicle Classification
	@GetMapping("/vehicleclassification")
	@ApiOperation(value = "This method is to Vehicel Classification Drop Down")

	public ResponseEntity<CommonCrmRes> getVehicelClassification() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getVehicelClassification();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Prospect Status

	@GetMapping("/prospectstatus")
	public ResponseEntity<CommonCrmRes> getProspectStatus() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getProspectStatus();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Prospect Lost Reason

	@GetMapping("/prospectlostreason")
	public ResponseEntity<CommonCrmRes> getProspectLost() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getProspectLost();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Payment Type
	@GetMapping("/paymenttype")
	public ResponseEntity<CommonCrmRes> getPaymentType() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getPaymentType();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Product Details
	@GetMapping("/productdetails")
	public ResponseEntity<CommonCrmRes> getProductDetails() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getProductDetails();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Location
	@GetMapping("/location")
	public ResponseEntity<CommonCrmRes> getLocation() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getLocation();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Assign to Group
	@GetMapping("/assigntogroup")
	public ResponseEntity<CommonCrmRes> getAssignToGroup() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<AssigntoGroupRes> res = dropDownService.getAssignToGroup();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Assign to User
	@GetMapping("/assigntouser/{usertypeid}")
	public ResponseEntity<CommonCrmRes> getAssignToUser(@PathVariable("usertypeid") String userTypeId) {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<AssigntoUserRes> res = dropDownService.getAssignToUser(userTypeId);
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Prospect Owner
	@GetMapping("/prospectowner")
	public ResponseEntity<CommonCrmRes> getProspectOwner() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<AssigntoUserRes> res = dropDownService.getProspectOwner();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Client Type
	@GetMapping("/zone")
	@ApiOperation(value = "This method is to Client Types Drop Down")

	public ResponseEntity<CommonCrmRes> getZones() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getZones();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Prospect Reason
	@GetMapping("/prospectreason")
	@ApiOperation(value = "This method is to Prospect Reason Drop Down")

	public ResponseEntity<CommonCrmRes> prospectReason() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.prospectReason();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Prospect Reason
	@GetMapping("/relationtype")
	@ApiOperation(value = "This method is to Prospect Reason Drop Down")

	public ResponseEntity<CommonCrmRes> getRelationType() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getRelationType();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Campaign Client Type

	@GetMapping("/campaignclienttype")
	@ApiOperation(value = "This method is to Campaign Client Type Drop Down")

	public ResponseEntity<CommonCrmRes> getCampaignClientType() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownResA> res = dropDownService.getCampaignClientType();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Campaign Template

	@GetMapping("/campaigntemplatetype")
	@ApiOperation(value = "This method is to Campaign Template Type Drop Down")

	public ResponseEntity<CommonCrmRes> getCampaignTemplateType() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownResA> res = dropDownService.getCampaignTemplateType();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Ticket Status

	@GetMapping("/ticketstatus")
	@ApiOperation(value = "This method is to Ticket Status Drop Down")

	public ResponseEntity<CommonCrmRes> getticketstatus() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownResA> res = dropDownService.getticketstatus();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Ticket Issuer

	@GetMapping("/ticketissuer")
	@ApiOperation(value = "This method is to Ticket Issuer Drop Down")

	public ResponseEntity<CommonCrmRes> getticketissuer() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownResA> res = dropDownService.getticketissuer();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Under Writer

	@PostMapping("/underwritter")
	@ApiOperation(value = "This method is to Underwiter Drop Down")

	public ResponseEntity<CommonCrmRes> getunderwriter(@RequestBody UnderwriterReq req) {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownResA> res = dropDownService.getunderwriter(req);
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// Insurance Company Master

	@GetMapping("/companies")
	@ApiOperation(value = "This method is to Insurance Master Drop Down")

	public ResponseEntity<CommonCrmRes> getinsurancemaster() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownResA> res = dropDownService.getinsurancemaster();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/branches")
	@ApiOperation(value="This method is to branch master drop down")
	public ResponseEntity<CommonCrmRes> getbranchmaster(@RequestBody BranchMasterDropdownReq req){
		CommonCrmRes data = new CommonCrmRes();
		List<DropDownResA> res = dropDownService.getbranchmaster(req);
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
	
	@PostMapping("/broughtby")
	@ApiOperation(value="This method is to branch master drop down")
	public ResponseEntity<CommonCrmRes> getBroughtDropDown(@RequestBody BroughtDropDownReq req){
		CommonCrmRes data = new CommonCrmRes();
		List<DropDownResA> res = dropDownService.getBroughtDropDown(req);
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
	
	@GetMapping("/policystatus")
	@ApiOperation(value="This method is to get policy status")
	public ResponseEntity<CommonCrmRes> getPolicyStatusDropDown(){
		CommonCrmRes data = new CommonCrmRes();
		List<DropDownResA> res = dropDownService.getPolicyStatusDropDown();
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
	
	@GetMapping("/filetype")
	@ApiOperation(value="This method is to get file type")
	public ResponseEntity<CommonCrmRes> getfileTypeDropDown(){
		CommonCrmRes data = new CommonCrmRes();
		List<DropDownResA> res = dropDownService.getFileTypeDropDown();
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
	
	@GetMapping("/renewableflag")
	@ApiOperation(value="This method is to get renewable flag")
	public ResponseEntity<CommonCrmRes> getrenewableFlagDropDown(){
		CommonCrmRes data = new CommonCrmRes();
		List<DropDownResA> res = dropDownService.getrenewableFlagDropDown();
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
	
	@PostMapping("/livdropdown")
	@ApiOperation(value="This method is to list item value drop down ")
	public ResponseEntity<CommonCrmRes> getLIVDropDown(@RequestBody LivDropDownReq req){
		CommonCrmRes data = new CommonCrmRes();
		List<DropDownResA> res = dropDownService.getLIVDropDown(req.getItemType());
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

