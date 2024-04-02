/*
*  Copyright (c) 2019. All right reserved
* Created on 2022-03-11 ( Date ISO 2022-03-11 - Time 16:18:38 )
* Generated by Telosys Tools Generator ( version 3.3.0 )
*/
package com.maan.crm.document.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.maan.crm.document.req.DocTypeDropDownReq;
import com.maan.crm.document.req.DocumentDeleteReq;
import com.maan.crm.document.req.DocumentUploadReq;
import com.maan.crm.document.req.FilePathReq;
import com.maan.crm.document.req.GetDocListReq;
import com.maan.crm.document.res.ClientDocListRes;
import com.maan.crm.document.res.DocCategoryRes;
import com.maan.crm.document.res.DocGroupRes;
import com.maan.crm.document.res.DocTypeRes;
import com.maan.crm.document.res.FilePathNameRes;
import com.maan.crm.document.res.FilePathRes;
import com.maan.crm.req.DocGroupReq;
import com.maan.crm.req.DocTypeSaveReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.util.error.Error;
import com.sun.net.httpserver.Authenticator.Success;

/**
 * <h2>SessionDetailsServiceimpl</h2>
 */
public interface DocumentService {

	//Validation
	List<Error> docvalidation(DocumentUploadReq req, MultipartFile file);

	//Upload
	CommonCrmRes fileupload(DocumentUploadReq req, MultipartFile file);

	//Delete
	CommonCrmRes deleteFile(DocumentDeleteReq req);

	// document type dropdowns
	List<DocTypeRes> getDocTypeDropDowns(DocTypeDropDownReq req);

	// Get total Document List
	List<ClientDocListRes> getTotalDocList(GetDocListReq req);
	List<DocCategoryRes> getCategoryDropDowns(DocTypeDropDownReq req);
	/*	List<DocGroupRes> getGroupOfDocuments(DocGroupReq req);

	SuccessRes saveDocTypes(DocTypeSaveReq req);

	 */

	FilePathRes getFilePath(FilePathReq req);

	FilePathRes getCompressedImages(FilePathReq req);

}
