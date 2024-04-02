package com.maan.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.maan.crm.bean.ClaimOtherDetails;
import com.maan.crm.bean.ClaimOtherDetailsId;
import com.maan.crm.bean.CommissionMotorDetails;
import com.maan.crm.bean.CommissionMotorDetailsId;
import com.maan.crm.bean.CommissionNonMotorDetails;
import com.maan.crm.bean.CommissionNonMotorDetailsId;
import com.maan.crm.bean.EndorsementGeneralDetails;
import com.maan.crm.bean.EndorsementGeneralDetailsId;
import com.maan.crm.repository.ClaimOtherDetailsRepository;
import com.maan.crm.repository.CommissionMotorDetailsRepository;
import com.maan.crm.repository.CommissionNonMotorDetailsRepository;
import com.maan.crm.req.ClaimOtherDetailsGetReq;
import com.maan.crm.req.ClaimOtherDetailsSaveReq;
import com.maan.crm.req.CommissionMotorDetailsGetReq;
import com.maan.crm.req.CommissionMotorDetailsSaveReq;
import com.maan.crm.req.CommissionNonMotorDetailsGetReq;
import com.maan.crm.req.CommissionNonMotorDetailsSaveReq;
import com.maan.crm.res.ClaimOtherDetailsRes;
import com.maan.crm.res.ClaimSuccessRes;
import com.maan.crm.res.CommissionMotorDetailsRes;
import com.maan.crm.res.CommissionNonMotorDetailsRes;
import com.maan.crm.res.CommissionSuccessRes;
import com.maan.crm.res.EndorsementGeneralDetailsRes;
import com.maan.crm.res.EndorsementSuccessRes;
import com.maan.crm.service.ClaimService;
import com.maan.crm.service.CommissionService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class ClaimServiceImpl implements ClaimService {

	
	
	@Autowired
	private ClaimOtherDetailsRepository otherdetailsrepository;
	
	
	
	Gson json = new Gson();

	private Logger log = LogManager.getLogger(ClaimServiceImpl.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	// Claim Other Details Validation
	@Override
	public List<Error> validateClaimOtherDetails(ClaimOtherDetailsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		try {
			
			if (req.getClaimSettlementDate() .toString() == null || StringUtils.isBlank(req.getClaimSettlementDate().toString())) {
				errors.add(new Error("01", "Claim Settlement Date", "Please Enter Claim Settlement Date"));
			} 
			if(req.getClaimFileType() ==null || StringUtils.isBlank(req.getClaimFileType())) {
				errors.add(new Error("02","Claim File Type","Please Enter Claim File Type"));
			}
			if(Integer.valueOf(req.getClaimFileTypeId()) ==null || StringUtils.isBlank(Integer.toString(req.getClaimFileTypeId()))) {
				errors.add(new Error("03","Claim File Type Id","Please Enter Claim File Type Id"));
			}
			if(Integer.valueOf(req.getClaimid())==null || StringUtils.isBlank(Integer.toString(req.getClaimid()))) {
				errors.add(new Error("04","Claim  Id","Please Enter Claim Id"));
			}
			if(req.getClaimPaymentDetails() ==null || StringUtils.isBlank(req.getClaimPaymentDetails())) {
				errors.add(new Error("05","Claim  Payment Details","Please Enter Claim Payment Details"));
			}
			if(req.getClaimProcessingStatus()==null || StringUtils.isBlank(req.getClaimProcessingStatus())) {
				errors.add(new Error("06","Claim Processing Status","Please Enter Claim Processing Status"));
			}
			if(req.getClaimStatus()==null || StringUtils.isBlank(req.getClaimStatus())) {
				errors.add(new Error("07","Claim Status","Please Enter Claim Status"));
			}
			if(Integer.valueOf(req.getClaimProcessingStatusId())==null || StringUtils.isBlank(Integer.toString(req.getClaimProcessingStatusId()))) {
				errors.add(new Error("08","Claim Processing Status Id","Please Enter Claim Processing Status Id"));
			}
			if(Integer.valueOf(req.getClaimStatusId())==null || StringUtils.isBlank(Integer.toString(req.getClaimStatusId()))) {
				errors.add(new Error("09","Claim Status Id","Please Enter Claim Status Id"));
			}
			if(req.getDocumentsRequiredYN()==null || StringUtils.isBlank(req.getDocumentsRequiredYN())) {
				errors.add(new Error("10","Documents Required YN","Please Enter Documents Required YN"));
			}
			if(req.getDocumentsSubmittedYN() ==null || StringUtils.isBlank(req.getDocumentsSubmittedYN())) {
				errors.add(new Error("11","Documents Submitted YN","Please Enter Documents Submitted YN"));
			}
			if(req.getReferenceNumber()==null || StringUtils.isBlank(req.getReferenceNumber())) {
				errors.add(new Error("12","Reference Number","Please Enter Reference Number"));
			}
			if(req.getRemarks()==null || StringUtils.isBlank(req.getRemarks())) {
				errors.add(new Error("13","Remarks","Please Enter Remarks"));
			}
			if(Double.valueOf(req.getNetClaimAssessed()) ==null || StringUtils.isBlank(Double.toString(req.getNetClaimAssessed()))) {
				errors.add(new Error("14","Net Claim Assessed","Please Enter Net Claim Assessed"));
			}
			if(Double.valueOf(req.getOnAccountAmount()) ==null || StringUtils.isBlank(Double.toString(req.getOnAccountAmount()))) {
				errors.add(new Error("15","On Account Amount","Please Enter On Account Amount"));
			}
			if(Double.valueOf(req.getSalvageAmount()) ==null || StringUtils.isBlank(Double.toString(req.getSalvageAmount()))) {
				errors.add(new Error("16","Salvage Amount","Please Enter Salvage Amount"));
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return errors;
		}
		return errors;
	}
	
	// Claim Other Details Insert 
	@Override
	public ClaimSuccessRes saveClaimOtherDetails(ClaimOtherDetailsSaveReq req) {
		ClaimSuccessRes res = new ClaimSuccessRes();

		ModelMapper mapper = new ModelMapper();
		try {
			ClaimOtherDetailsId id = new ClaimOtherDetailsId();
			id.setClaimid(req.getClaimid());
			id.setClaimOtherDetailsId(req.getClaimOtherDetailsId());
			
			Optional<ClaimOtherDetails> data = otherdetailsrepository.findById(id);
			if (data.isPresent()) {
				// Update
				ClaimOtherDetails ent = mapper.map(req, ClaimOtherDetails.class);
				ent.setClaimOtherDetailsId(req.getClaimOtherDetailsId());
				otherdetailsrepository.save(ent);
				res.setResponse("Updated Successfully ");

			} else {

				// Insert
				List<ClaimOtherDetails> list = otherdetailsrepository.findAllByOrderByClaimOtherDetailsIdDesc();
				Integer claimOtherDetailsId = 1000;

				if (list.size() != 0) {
					claimOtherDetailsId = list.get(0).getClaimOtherDetailsId() + 1;
				}
				ClaimOtherDetails ent = mapper.map(req, ClaimOtherDetails.class);
				ent.setClaimOtherDetailsId(claimOtherDetailsId);
				otherdetailsrepository.save(ent);
 
				res.setResponse("Inserted Successfully ");
			}

		} catch (Exception ex) {
			log.error(ex);
			return null;
		}
		return res;
	}	
	
	// Claim Other Details Get
	@Override
	public ClaimOtherDetailsRes getClaimOtherDetails(ClaimOtherDetailsGetReq req) {
		ClaimOtherDetailsRes res = new ClaimOtherDetailsRes();
		try {

			ClaimOtherDetailsId id = new ClaimOtherDetailsId();
			id.setClaimid(req.getClaimid());
			id.setClaimOtherDetailsId(req.getClaimOtherDetailsId());
							
			Optional<ClaimOtherDetails> opt = otherdetailsrepository.findById(id);

			if (opt.isPresent()) {

				ModelMapper modelMapper = new ModelMapper();
				res = modelMapper.map(opt.get(), ClaimOtherDetailsRes.class);

			}
		} catch (Exception e) {
			res = null;
			log.info("Exception Error", e.getMessage());
		}
		return res;
	}



	


		
}
