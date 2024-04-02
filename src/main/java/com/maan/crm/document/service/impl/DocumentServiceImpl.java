package com.maan.crm.document.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.maan.crm.bean.DocumentTypeMaster;
import com.maan.crm.bean.DocumentUploadDetails;
import com.maan.crm.bean.DocumentUploadDetailsId;
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
import com.maan.crm.repository.ClientDetailsRepository;
import com.maan.crm.repository.DocumentTypeDetailsRepository;
import com.maan.crm.repository.DocumentTypeMasterRepository;
import com.maan.crm.repository.DocumentUploadDetailsRepository;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.util.error.Error;


@Service
@Transactional
public class DocumentServiceImpl implements DocumentService{
	
	private Logger log = LogManager.getLogger(DocumentServiceImpl.class);

	@Value("${file.directoryPath}")
	private String directoryPath;
	
	@Value("${file.backuppath}")
	private String backuppath;
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Autowired
	private DocumentUploadDetailsRepository  documentuploaddetailsrepository;
	@Autowired
	private DocumentTypeMasterRepository docRepo;
	@Autowired
	private DocumentTypeDetailsRepository  docTypedetailsRepo;
	@Autowired
	private ClientDetailsRepository clientDetailsRepo;
	
	public static  Map<String,String> ALLOWED_CONTENTTYPE;
	  static {
		  ALLOWED_CONTENTTYPE = new HashMap<String, String>();
		  //Image
		  ALLOWED_CONTENTTYPE.put(".bmp","image/bmp");
		  ALLOWED_CONTENTTYPE.put(".jpg","image/jpeg");
		  ALLOWED_CONTENTTYPE.put(".png","image/png");
		  ALLOWED_CONTENTTYPE.put(".jpeg","image/jpeg");
		  //Doc
		  ALLOWED_CONTENTTYPE.put(".doc","application/msword");
		  ALLOWED_CONTENTTYPE.put(".docx","application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		  ALLOWED_CONTENTTYPE.put(".pdf","application/pdf");		  
		  ALLOWED_CONTENTTYPE.put(".xlsx","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		  ALLOWED_CONTENTTYPE.put(".xls","application/vnd.ms-excel");
		  //Vid
		  ALLOWED_CONTENTTYPE.put(".avi","video/x-msvideo");
		  ALLOWED_CONTENTTYPE.put(".3gp","video/3gpp"); 
		  ALLOWED_CONTENTTYPE.put(".mpeg","video/mpeg");
		  ALLOWED_CONTENTTYPE.put(".mp4","video/mp4");
		  ALLOWED_CONTENTTYPE.put(".m4v","video/m4v");
		  ALLOWED_CONTENTTYPE.put(".flv","video/x-flv");
		  ALLOWED_CONTENTTYPE.put(".mp4","video/mp4");
		  ALLOWED_CONTENTTYPE.put(".m3u8","application/x-mpegURL");
		  ALLOWED_CONTENTTYPE.put(".ts","video/MP2T");
		  ALLOWED_CONTENTTYPE.put(".3gp","video/3gpp");
		  ALLOWED_CONTENTTYPE.put(".mov","video/quicktime");
		  ALLOWED_CONTENTTYPE.put(".avi","video/x-msvideo");
		  ALLOWED_CONTENTTYPE.put(".wmv","video/x-ms-wmv");

	  }
	//Upload
	@Override
	public List<Error> docvalidation(DocumentUploadReq req,MultipartFile file) {

		List<Error> errorList = new ArrayList<Error>();
		
		log.info(req);


			if(StringUtils.isNotBlank(req.getUploadedBy()) ) {
				boolean containsValue = ALLOWED_CONTENTTYPE.containsValue(file.getContentType());
				if (!containsValue) {
					errorList.add(new Error("01", "ReferenceNo", file.getContentType() + " is Not Allowed for "
							+ req.getOrginalFileName()));
				}

				long fileSizeInBytes = file.getSize();
				double size_kb = fileSizeInBytes / 1024;
				double size_mb = size_kb / 1024;

				if (size_mb > 25) {
					errorList.add(new Error("01", "FileSize", "File Size Must be 25Mb Current file value is" + size_mb
							+ "MB for " + req.getOrginalFileName()));
				}
			}
			
		
		
		return errorList;

	}

	//Detete
	@Override
	public CommonCrmRes fileupload(DocumentUploadReq DocumentUploadDetails,MultipartFile file) {
		
		CommonCrmRes res = new CommonCrmRes();
		
		try {
				DocumentUploadDetails doc = new DocumentUploadDetails();
				
				Random random = new Random();
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				if(StringUtils.isNotBlank(DocumentUploadDetails.getUploadedBy())  ) {
					String newfilename  = "";
					String newfilename1 = "";
					//OrginalFile
					Path destination = Paths.get(directoryPath) ;
					newfilename= random.nextInt(100) + timestamp.toString().replace(":", "T").replace(" ", "S").replace("-", "H").replace(".", "D") +"."+FilenameUtils.getExtension(file.getOriginalFilename());
					Files.copy(file.getInputStream(),destination.resolve(newfilename));
					
					Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
					//BackupFile
					Path destination1 = Paths.get(backuppath) ; 
					newfilename1= random.nextInt(100) + timestamp1.toString().replace(":", "T").replace(" ", "S").replace("-", "H").replace(".", "D") +"."+FilenameUtils.getExtension(file.getOriginalFilename());
					Files.copy(file.getInputStream(),destination1.resolve(newfilename1));
				
					doc.setFileName(newfilename);
					doc.setFilePathOrginal(directoryPath+newfilename);
					doc.setFilePathBackup(backuppath+newfilename1);
					doc.setOrginalFileName(file.getOriginalFilename());
					doc.setUploadedTime(new Date());
					doc.setUploadedBy(DocumentUploadDetails.getUploadedBy());
				}
				
				doc.setClientid(DocumentUploadDetails.getClientId());
				doc.setCreatedby(DocumentUploadDetails.getCreatedby());
				doc.setDocTypeDescription(DocumentUploadDetails.getDocTypeDescription());
				doc.setDocTypeId(DocumentUploadDetails.getDocTypeId());	
				doc.setDocApplicable(DocumentUploadDetails.getDocApplicable());
				doc.setDocApplicableId(DocumentUploadDetails.getDocApplicableId());
				doc.setInsCompanyId(DocumentUploadDetails.getInsCompanyId());
				doc.setEntryDate(new Date());
				doc.setRequestedBy(DocumentUploadDetails.getRequesteBy());
				
				//RefRunning number
				if(StringUtils.isBlank(DocumentUploadDetails.getDocumentRef())   ) {
					Random rnd = new Random();
					int number = rnd.nextInt(10000);
					String randomNo = String.format("%04d", number);
					doc.setDocumentRef(Integer.valueOf(randomNo));
				} else {
					doc.setDocumentRef(Integer.valueOf(DocumentUploadDetails.getDocumentRef()));
				}
				
				documentuploaddetailsrepository.save(doc);
			
			res.setCommonResponse("File Upload Sucessfully");
			res.setIsError(false);						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public CommonCrmRes deleteFile(DocumentDeleteReq req) {

		CommonCrmRes res = new CommonCrmRes();
		try {
			DocumentUploadDetailsId id = new DocumentUploadDetailsId();
			id.setClientid(req.getClientId());
			id.setDocTypeDescription(req.getDocTypeDescription());
			id.setDocTypeId(req.getDocTypeId());
			id.setDocumentRef(req.getDocumentRef());
			id.setInsCompanyId(req.getInsCompanyId());
			id.setDocApplicable(req.getDocApplicable());
			id.setDocApplicableId(req.getDocApplicableId());
			
			documentuploaddetailsrepository.deleteById(id);
			
			res.setMessage("Document Deleted Sucessfully");
			res.setIsError(false);
		} catch (Exception e) {
			res.setMessage("Document Deleted Failed");
			res.setIsError(true);
		}
		return res;
	}
	
	// Drop|Down
		@Override
		public List<DocTypeRes> getDocTypeDropDowns(DocTypeDropDownReq req) {
			List<DocTypeRes> resList = new ArrayList<DocTypeRes>();
			try {
				// Criteria
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<DocumentTypeMaster> query = cb.createQuery(DocumentTypeMaster.class);
				List<DocumentTypeMaster> docTypeList = new ArrayList<DocumentTypeMaster>();

				Root<DocumentTypeMaster> login = query.from(DocumentTypeMaster.class);

				// Order By
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(cb.asc(login.get("documentid")));
				
				Predicate p1 = cb.equal(login.get("status"), "Y");
				
				Predicate p2 = null ;
				
				if(  req.getDocApplicable() !=null && req.getDocApplicable().equalsIgnoreCase("Client_Info")  ) {
					String docApplicable = "PERSONAL_DETAILS";
					p2 = cb.like(login.get("docCons"), "%" + docApplicable + "%" );
				} else if(req.getDocApplicable() !=null &&  req.getDocApplicable().equalsIgnoreCase("Lead_Info")  ) {
					String docApplicable = "QUOTATION_DETAILS";
					p2 = cb.like(login.get("docCons"), "%" + docApplicable + "%" );
				} else {
					p2 = cb.equal(login.get("docApplicableId"),req.getDocApplicableId() );
				}
				

				query.select(login).where(p1, p2).orderBy(orderList) ;

				TypedQuery<DocumentTypeMaster> result = em.createQuery(query);
				docTypeList = result.getResultList();

				for (DocumentTypeMaster data : docTypeList ) {

					DocTypeRes res = new DocTypeRes();
					
					res.setCode(data.getDocumentid().toString());
					res.setCodeDesc(data.getDocumentDescription());
					resList.add(res);
				}

			} catch (Exception e) {
				e.printStackTrace();
				log.info("Exception is ---> " + e.getMessage());
			}
			return resList;
		}
	
	
	@Override
	public List<DocCategoryRes> getCategoryDropDowns(DocTypeDropDownReq req) {
		List<DocCategoryRes> resList = new ArrayList<DocCategoryRes>();
		try {
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<DocumentTypeMaster> query = cb.createQuery(DocumentTypeMaster.class);
			List<DocumentTypeMaster> docTypeList = new ArrayList<DocumentTypeMaster>();

			Root<DocumentTypeMaster> d = query.from(DocumentTypeMaster.class);

			List<String> DocTypesNot = new ArrayList<String>(); 
			DocTypesNot.add("CAMP_INFO");
			//In 
			Expression<String>e0=d.get("docApplicable");
			
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(d.get("docApplicableId")));
			
			Predicate p1 = cb.equal(d.get("status"), "Y");
			Predicate p2 = e0.in(DocTypesNot).not();
			//Predicate p2 = cb.like(login.get("docCons"), "%" + req.getDocApplicable() + "%");

			query.select(d ).distinct(true).where(p1,p2).orderBy(orderList) ;

			TypedQuery<DocumentTypeMaster> result = em.createQuery(query);
			docTypeList = result.getResultList();
			docTypeList = docTypeList.stream().filter(distinctByKey(o -> Arrays.asList( o.getDocApplicableId()))).collect(Collectors.toList());
			for (DocumentTypeMaster data : docTypeList ) {
		  
				DocCategoryRes res = new DocCategoryRes();
				res.setCode(data.getDocApplicableId().toString());
				res.setCodeDesc(data.getDocApplicable());
				resList.add(res);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return resList;
	}

	private static <T> java.util.function.Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
	    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
		// Get Doc List
		@Override
		public List<ClientDocListRes> getTotalDocList(GetDocListReq req) {

			List<ClientDocListRes> totalDocResList = new ArrayList<ClientDocListRes>();

			try {
				List<DocumentUploadDetails> list = documentuploaddetailsrepository.findByClientidOrderByEntryDateAsc(req.getClientRefNo());

				for (DocumentUploadDetails data : list) {

					ModelMapper mapper = new ModelMapper();
					mapper.getConfiguration().setAmbiguityIgnored(true);
					ClientDocListRes res = mapper.map(data, ClientDocListRes.class);
					res.setFilename(data.getFileName());
					res.setDocDesc(data.getDocTypeDescription());
					totalDocResList.add(res);
				}

			} catch (Exception e) {
				e.printStackTrace();
				log.info(e.getMessage());
				return null;
			}
			return totalDocResList;
		}


		@Override
		public FilePathRes getFilePath(FilePathReq req) {
			FilePathRes res = new FilePathRes();
			try {
				DocumentUploadDetails getFile = documentuploaddetailsrepository.findByClientidAndDocumentRefAndDocTypeId(req.getClientRefNo(), Integer.valueOf(req.getReqrefno()),
						Integer.valueOf(req.getDoctypeid()) );
				ModelMapper mapper = new ModelMapper();
				mapper.getConfiguration().setAmbiguityIgnored(true);
				res = mapper.map(getFile, FilePathRes.class);
				res.setDocTypeId(getFile.getDocTypeId()==null?"":getFile.getDocTypeId().toString());
				res.setDocApplicable(getFile.getDocApplicable());
				res.setDocDesc(getFile.getDocTypeDescription());
				res.setFilename(getFile.getFileName());
				res.setFilepathname(getFile.getFilePathOrginal() );
				if(StringUtils.isNotBlank(res.getFilepathname()) && new File(res.getFilepathname()).exists()) {
							res.setImgurl(new GetFileFromPath(res.getFilepathname()).call().getImgUrl());
							res.setReqrefno(getFile.getDocumentRef().toString());
					}else
						 System.out.println("File is Not found!!"+res.getFilepathname());
				} catch (Exception e) {
					e.printStackTrace();
					//Log.info("Exception Is --->" + e.getMessage());
					return null;
				}
			return res;
		}
		
		@Override
		public FilePathRes getCompressedImages(FilePathReq req) {
			FilePathRes res = new FilePathRes();
			try {
				DocumentUploadDetails getFile = documentuploaddetailsrepository.findByClientidAndDocumentRefAndDocTypeId(req.getClientRefNo(), Integer.valueOf(req.getReqrefno()),
						Integer.valueOf(req.getDoctypeid()) );
				ModelMapper mapper = new ModelMapper();
				mapper.getConfiguration().setAmbiguityIgnored(true);
				res = mapper.map(getFile, FilePathRes.class);
				res.setDocTypeId(getFile.getDocTypeId()==null?"":getFile.getDocTypeId().toString());
				res.setDocApplicable(getFile.getDocApplicable());
				res.setDocDesc(getFile.getDocTypeDescription());
				res.setFilename(getFile.getFileName());
				res.setFilepathname(getFile.getFilePathBackup());
				res.setCommonfilepath(getFile.getFilePathBackup() );
			//	res.setVehChassisNo(getFile.getClaimNo());
				if(StringUtils.isNotBlank(res.getCommonfilepath()) && new File(res.getCommonfilepath()).exists()) {
							res.setImgurl(new GetFileFromPath(res.getCommonfilepath()).call().getImgUrl());
							res.setReqrefno(getFile.getDocumentRef().toString());
					}else
						 System.out.println("File is Not found!!"+res.getFilepathname());
				} catch (Exception e) {
					e.printStackTrace();
					//Log.info("Exception Is --->" + e.getMessage());
					return null;
				}
			return res;
		}
	/*	@Override
		public List<DocGroupRes> getGroupOfDocuments(DocGroupReq req) {
			List<DocGroupRes> resList = new ArrayList<DocGroupRes>();
			try {
				// Criteria
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<DocumentTypeMaster> query = cb.createQuery(DocumentTypeMaster.class);
				List<DocumentTypeMaster> docTypeList = new ArrayList<DocumentTypeMaster>();

				List<String> DocTypesNot = new ArrayList<String>(); 
				DocTypesNot.add("CAMP_INFO");
				
				Root<DocumentTypeMaster> d = query.from(DocumentTypeMaster.class);
				//In 
				Expression<String>e0=d.get("docApplicable");
				
				Predicate p1 = cb.equal(d.get("status"), "Y");
				Predicate p2 = e0.in(DocTypesNot).not();

				// Order By
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(cb.asc(d.get("docApplicableId")));
				query.orderBy(orderList);
				
				query.select(d).where(p1, p2).orderBy(orderList) ;

				TypedQuery<DocumentTypeMaster> result = em.createQuery(query);
				docTypeList = result.getResultList();

				// Group By DocApplicable
				Map<String, List<DocumentTypeMaster>> groupByDocType = docTypeList.stream().collect(Collectors.groupingBy(DocumentTypeMaster::getDocApplicable) );	
				
				for (String docType : groupByDocType.keySet() ) {
					DocGroupRes   groupRes = new DocGroupRes();
					
					List<DocumentTypeMaster> groupData = groupByDocType.get(docType);
					
					List<DocTypeRes2> docList = new ArrayList<DocTypeRes2>();
					for (DocumentTypeMaster data : groupData ) {
						DocTypeRes2 res = new DocTypeRes2();
							res.setCode(data.getDocumentid().toString());
							res.setCodeDesc(data.getDocumentDescription());
							docList.add(res);
					}
					
					groupRes.setDocApplicable(groupData.get(0).getDocApplicable()) ;
					groupRes.setDocApplicableId(groupData.get(0).getDocApplicableId()==null?"":groupData.get(0).getDocApplicableId().toString() );
					groupRes.setDocTypes(docList);
					resList.add(groupRes);
				} 
				

			} catch (Exception e) {
				e.printStackTrace();
				log.info("Exception is ---> " + e.getMessage());
			}
			return resList;
		}

		@Override
		public SuccessRes saveDocTypes(DocTypeSaveReq req) {
			SuccessRes res = new SuccessRes();
			try {
				List<DocumentTypeDetails> oldDocTypes =  docTypedetailsRepo.findByRefNo(req.getRefNo());
				if(oldDocTypes.size()>0 ) {
					docTypedetailsRepo.deleteAll(oldDocTypes);
				}
				
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				log.info("Exception is ---> " + e.getMessage());
			}
			return res;
		} */

}
	

