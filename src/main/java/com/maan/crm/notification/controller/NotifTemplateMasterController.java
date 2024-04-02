package com.maan.crm.notification.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maan.crm.notification.mail.dto.GetMailTemplateReq;
import com.maan.crm.notification.mail.dto.NotifTemplateRes;
import com.maan.crm.notification.mail.dto.TemplatesDropDownReq;
import com.maan.crm.notification.service.NotificationService;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.service.DropDownService;
import com.maan.crm.service.PrintReqService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "NOTIFIACTION TEMPLATES : Notifiaction Templates", description = "API's")
@RequestMapping("/notification")
public class NotifTemplateMasterController {
	
	@Autowired
	private DropDownService dropDownService;

	@Autowired
	private NotificationService mailservice;
	
	@Autowired
	private PrintReqService reqPrinter;
	
	// Client Type
	@PostMapping("/dropdown/mailtemplates")
	@ApiOperation(value = "This method is to Mail Templates Drop Down")

	public ResponseEntity<CommonCrmRes> getMailTemplates(@RequestBody  TemplatesDropDownReq req ) {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getMailTemplates(req);
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
	
	
	@PostMapping("/dropdown/smstemplates")
	@ApiOperation(value = "This method is to Sms Templates Drop Down")
	public ResponseEntity<CommonCrmRes> getSmsTemplates(@RequestBody TemplatesDropDownReq req) {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = dropDownService.getSmsTemplates(req);
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
	
	
	@PostMapping("/getGetMailTemplate")
	@ApiOperation(value = "This method is to Get Email Templates")
	public ResponseEntity<CommonCrmRes> getGetMailTemplate(@RequestBody GetMailTemplateReq req) {

		reqPrinter.reqPrint("Printer Request --->" + req);
		CommonCrmRes data = new CommonCrmRes();

		// Save
		NotifTemplateRes res = mailservice.getGetMailTemplate(req);
		
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
	
	
	
}
