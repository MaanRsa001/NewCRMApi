package com.maan.crm.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maan.crm.req.CrmClientSuccessRes;
import com.maan.crm.req.TicketAdminSaveReq;
import com.maan.crm.req.TicketMasterGetAllReq;
import com.maan.crm.req.TicketMasterGetReq;
import com.maan.crm.req.TicketMasterReq;
import com.maan.crm.req.TicketViewReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.TicketAdminRes;
import com.maan.crm.res.TicketMasterRes;
import com.maan.crm.service.TicketMasterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(tags = "TICKET: TICKET MASTER DETAILS", description = "API's")
@RequestMapping("/api")
public class TicketMasterController {

	@Autowired
	private TicketMasterService service;
	
	@PostMapping("/ticketmaster")
	@ApiOperation(value="This method is to Insert Ticketmaster")
	public ResponseEntity<CommonCrmRes> ticketmaster(@RequestBody TicketMasterReq req)
	{
		CommonCrmRes data = new CommonCrmRes();
		SuccessRes res = service.ticketmaster(req);
		
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
	
	@PostMapping("/getticketmaster")
	@ApiOperation(value="This method is to Get By Ticket Id")
	public ResponseEntity<CommonCrmRes> getticketmaster(@RequestBody TicketMasterGetReq req)
	{
		CommonCrmRes data = new CommonCrmRes();
		TicketMasterRes res = service.getticketmaster(req);
		
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
	
	

	@PostMapping("/getallticketmaster")
	@ApiOperation(value="This method is to Get All Ticket Master")
	public ResponseEntity<CommonCrmRes> getallticketmaster(@RequestBody TicketMasterGetAllReq req)
	{
		CommonCrmRes data = new CommonCrmRes();
		List<TicketMasterRes> res = service.getallticketmaster(req);
		
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
	
	
	// From Admin Side Insert 
	
	@PostMapping("/ticketadmin")
	@ApiOperation(value="This method is to Insert Ticket Admin")
	public ResponseEntity<CommonCrmRes> ticketadmin(@RequestBody TicketAdminSaveReq req)
	{
		CommonCrmRes data = new CommonCrmRes();
		SuccessRes res = service.ticketadmin(req);
		
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

	// From Admin Side Getall based on Status 
	
		@PostMapping("/ticketsview")
		@ApiOperation(value="This method is to Get All Tickets ")
		public ResponseEntity<CommonCrmRes> ticketview(@RequestBody TicketViewReq req)
		{
			CommonCrmRes data = new CommonCrmRes();
			List<TicketAdminRes> res = service.ticketview(req);
			
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
