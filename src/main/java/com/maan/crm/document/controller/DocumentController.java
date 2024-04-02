package com.maan.crm.document.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maan.crm.document.req.DocTypeDropDownReq;
import com.maan.crm.document.req.DocumentDeleteReq;
import com.maan.crm.document.req.DocumentUploadReq;
import com.maan.crm.document.req.FilePathReq;
import com.maan.crm.document.req.GetDocListReq;
import com.maan.crm.document.res.ClientDocListRes;
import com.maan.crm.document.res.DocCategoryRes;
import com.maan.crm.document.res.DocTypeRes;
import com.maan.crm.document.res.FilePathRes;
import com.maan.crm.document.service.DocumentService;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.CommonValidationException;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/crm/document")
@Api(tags = "DOCUMENT : Document ", description = "API's")
@RestController
public class DocumentController {

	@Autowired
	private DocumentService documentservice;
	
	@Autowired
	private PrintReqService reqPrinter;
	
	@Autowired
	private CRMValidationService validationService;
	
	private Logger log = LogManager.getLogger(DocumentController.class);
	
	@PostMapping("/upload")
	public ResponseEntity<CommonCrmRes> uploadFile(@RequestParam("File") MultipartFile file, @RequestParam("Req") String jsonString) throws CommonValidationException, JsonMappingException, JsonProcessingException{
		
		log.info(jsonString);
		
		DocumentUploadReq req =  new ObjectMapper().readValue(jsonString, DocumentUploadReq.class); 
		
    	List<Error> error = new ArrayList<Error>();
		error = documentservice.docvalidation(req,file);
		if (error != null && error.size() > 0) {
			
			CommonCrmRes res = new CommonCrmRes();
			res.setCommonResponse(null);
			res.setIsError(true);
			res.setErrorMessage(error);
			res.setMessage("File Upload Faild");
			
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(res);
			
		}else {
			CommonCrmRes res = documentservice.fileupload(req,file);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		
	}
	
	
	@PostMapping("/uploadwithoutfile")
	public ResponseEntity<CommonCrmRes> uploadWithoutFile( @RequestParam("Req") String jsonString) throws CommonValidationException, JsonMappingException, JsonProcessingException{
		
		log.info(jsonString);
		 MultipartFile file = null ;
		DocumentUploadReq req =  new ObjectMapper().readValue(jsonString, DocumentUploadReq.class); 
		
    	List<Error> error = new ArrayList<Error>();
		error = documentservice.docvalidation(req,file);
		if (error != null && error.size() > 0) {
			
			CommonCrmRes res = new CommonCrmRes();
			res.setCommonResponse(null);
			res.setIsError(true);
			res.setErrorMessage(error);
			res.setMessage("File Upload Faild");
			
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(res);
			
		}else {
			CommonCrmRes res = documentservice.fileupload(req,file);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		
	}
	
	@PostMapping("/delete")
	public ResponseEntity<CommonCrmRes> deleteFile(@RequestBody DocumentDeleteReq req)  {

		CommonCrmRes res = documentservice.deleteFile(req);
		return ResponseEntity.status(HttpStatus.OK).body(res);
		
	}
	
	//DropDown
	@PostMapping("/dropdown/doctypes")
	@ApiOperation(value = "This method is to Get Insurance Companies")
	public ResponseEntity<CommonCrmRes> getDocTypeDropDowns(@RequestBody DocTypeDropDownReq req) {
		CommonCrmRes data = new CommonCrmRes();
		List<DocTypeRes> res = documentservice.getDocTypeDropDowns(req);
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);

	}
	
	@PostMapping("/dropdown/category")
	@ApiOperation(value = "This method is to Get Insurance Companies")
	public ResponseEntity<CommonCrmRes> getCategoryDropDowns(@RequestBody DocTypeDropDownReq req) {
		CommonCrmRes data = new CommonCrmRes();
		List<DocCategoryRes> res = documentservice.getCategoryDropDowns(req);
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);

	}


	//Get Doc List
	@PostMapping("/getdoclist")
	@ApiOperation(value = "This method is to Get Document List")
	public ResponseEntity<CommonCrmRes> getdoclist(@RequestBody GetDocListReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Total Doc List
		List<ClientDocListRes> res = documentservice.getTotalDocList(req);

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

	//Get Original Image
	@RequestMapping(path = "/download", method = RequestMethod.POST)
	public ResponseEntity<Resource> download(@RequestParam("FilePath") String param) throws IOException {

		File file = new File(param);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

	    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(file.length())
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);
	}

	
	@PostMapping("/getoriginalimage" )	 
	@ApiOperation(value="This method is to Get Image File ")
	private ResponseEntity<CommonCrmRes>  getFilePath(@RequestBody FilePathReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes()  ; 
		
		FilePathRes res = documentservice.getFilePath(req);
		data .setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
		return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/getcompressedimage" )	 
	@ApiOperation(value="This method is to Get Compressed Image File ")
	private ResponseEntity<CommonCrmRes>  getCompressedImages(@RequestBody FilePathReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes()  ; 
		
		FilePathRes res = documentservice.getCompressedImages(req);
		data .setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
		return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
/*	@PostMapping("/groupofdoctypes")
	@ApiOperation(value = "This method is to Save Client Details")
	public ResponseEntity<CommonCrmRes> getGroupOfDocuments(@RequestBody DocGroupReq req) {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DocGroupRes> res = documentservice.getGroupOfDocuments(req);
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
	
	
	@PostMapping("/savedoctypes")
	@ApiOperation(value = "This method is to Save Doc Types")
	public ResponseEntity<CommonCrmRes> saveDocTypes(@RequestBody DocTypeSaveReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = validationService.validateDocTypeSaveReq(req );
		//// validation
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);


		} else {
			SuccessRes res = documentservice.saveDocTypes(req);
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
		
		
	} */
}

	
	
	

