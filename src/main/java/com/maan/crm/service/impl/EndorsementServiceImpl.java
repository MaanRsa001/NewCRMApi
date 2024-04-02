package com.maan.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.maan.crm.bean.EndorsementFinancialDetails;
import com.maan.crm.bean.EndorsementFinancialDetailsId;
import com.maan.crm.bean.EndorsementGeneralDetails;
import com.maan.crm.bean.EndorsementGeneralDetailsId;
import com.maan.crm.bean.EndorsementPaymentDetails;
import com.maan.crm.bean.EndorsementPaymentDetailsId;
import com.maan.crm.repository.EndorsementFinancialDetailsRepository;
import com.maan.crm.repository.EndorsementGeneralDetailsRepository;
import com.maan.crm.repository.EndorsementPaymentDetailsRepository;
import com.maan.crm.req.EndorsementFinancialDetailsGetReq;
import com.maan.crm.req.EndorsementFinancialDetailsSaveReq;
import com.maan.crm.req.EndorsementGeneralDetailsGetReq;
import com.maan.crm.req.EndorsementGeneralDetailsSaveReq;
import com.maan.crm.req.EndorsementPaymentDetailsGetReq;
import com.maan.crm.req.EndorsementPaymentDetailsSaveReq;
import com.maan.crm.res.EndorsementFinancialDetailsRes;
import com.maan.crm.res.EndorsementGeneralDetailsRes;
import com.maan.crm.res.EndorsementPaymentDetailsRes;
import com.maan.crm.res.EndorsementSuccessRes;
import com.maan.crm.service.EndorsementService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class EndorsementServiceImpl implements EndorsementService {

	
	
	@Autowired
	private EndorsementGeneralDetailsRepository endorsementgeneraldetailsrepository;
	
	@Autowired
	private EndorsementPaymentDetailsRepository endorsementpaymentdetailsrepository;
	
	@Autowired
	private EndorsementFinancialDetailsRepository endorsementfinancial;
	
	Gson json = new Gson();

	private Logger log = LogManager.getLogger(EndorsementServiceImpl.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			

		// Endorsement General Details Validation
		@Override
		public List<Error> validateEndorsementGeneral(EndorsementGeneralDetailsSaveReq req) {
			List<Error> errors = new ArrayList<Error>();
			try {
				
				if (req.getEffectiveDate().toString() == null || StringUtils.isBlank(req.getEffectiveDate().toString())) {
					errors.add(new Error("01", "Effective Date", "Please Enter Effective Date"));
				} 
				if(req.getEndorsementComment()==null || StringUtils.isBlank(req.getEndorsementComment())) {
					errors.add(new Error("02","Endorsement Comment","Please Enter Endorsement Comment"));
				}
				if(req.getEndorsementDescription()==null || StringUtils.isBlank(req.getEndorsementDescription())) {
					errors.add(new Error("03","Endorsement Description","Please Enter Endorsement Description"));
				}
				
				if(req.getEndorsementReason1()==null || StringUtils.isBlank(req.getEndorsementReason1())) {
					errors.add(new Error("04","EndorsementReason 1","Please Enter EndorsementReason 1 Description"));
				}
				if(Integer.valueOf(req.getEndorsementReason1Id())==null || StringUtils.isBlank(Integer.toString(req.getEndorsementReason1Id()))) {
					errors.add(new Error("05","Endorsement Reason1 Id","Please Enter Endorsement Reason1 Id"));
				}
				if(req.getEndorsementReason2()==null || StringUtils.isBlank(req.getEndorsementReason2())) {
					errors.add(new Error("06","Endorsement Reason 2","Please Enter EndorsementReason 2 Description"));
				}
				if(Integer.valueOf(req.getEndorsementReason2Id())==null || StringUtils.isBlank(Integer.toString(req.getEndorsementReason2Id()))) {
					errors.add(new Error("07","Endorsement Reason2 Id","Please Enter Endorsement Reason 2 Id"));
				}

				if(req.getEndorsementReason3()==null || StringUtils.isBlank(req.getEndorsementReason3())) {
					errors.add(new Error("08","Endorsement Reason 3","Please Enter Endorsement Reason 3 Description"));
				}
				if(Integer.valueOf(req.getEndorsementReason3Id())==null || StringUtils.isBlank(Integer.toString(req.getEndorsementReason3Id()))) {
					errors.add(new Error("09","Endorsement Reason 3 Id","Please Enter Endorsement Reason 3 Id"));
				}
				if(req.getEndorsementNumber()==null|| StringUtils.isBlank(req.getEndorsementNumber())) {
					errors.add(new Error("10","Endorsement Number","Please Enter Endorsement Number"));
				}
				if(req.getEndorsementStatus() ==null || StringUtils.isBlank(req.getEndorsementStatus())) {
					errors.add(new Error("11","Endorsement Status","Please Enter Endorsement Status"));
				}
				if(Integer.valueOf(req.getEndorsementStatusId() )==null || StringUtils.isBlank(Integer.toString(req.getEndorsementStatusId()))) {
					errors.add(new Error("12","Endorsement Status Id","Please Enter Endorsement Status Id"));
				}

				if(req.getEndorsementType() ==null || StringUtils.isBlank(req.getEndorsementType())) {
					errors.add(new Error("13","Endorsement Type","Please Enter Endorsement Type"));
				}
				if(Integer.valueOf(req.getEndorsementTypeId() )==null || StringUtils.isBlank(Integer.toString(req.getEndorsementTypeId()))) {
					errors.add(new Error("14","Endorsement Type Id","Please Enter Endorsement Type Id"));
				}
				
				if (req.getReceivedDate().toString() == null || StringUtils.isBlank(req.getReceivedDate().toString())) {
					errors.add(new Error("15",  "Received Date", "Please Enter Received Date"));
				}
				
			}
				catch (Exception e) {
					e.printStackTrace();
					log.info("Exception is --->" + e.getMessage());
					return errors;
				}
				return errors;
			}

		// Endorsement General Details Save
		@Override
		public EndorsementSuccessRes saveEndorsementGeneral(EndorsementGeneralDetailsSaveReq req) {
			EndorsementSuccessRes res = new EndorsementSuccessRes();

			ModelMapper mapper = new ModelMapper();
			try {
				EndorsementGeneralDetailsId id = new EndorsementGeneralDetailsId();

				id.setEndorsementId(req.getEndorsementId());
				id.setPolicyId(req.getPolicyId());
				
				Optional<EndorsementGeneralDetails> data = endorsementgeneraldetailsrepository.findById(id);
				if (data.isPresent()) {
					// Update
					EndorsementGeneralDetails ent = mapper.map(req, EndorsementGeneralDetails.class);
					ent.setEndorsementId(req.getEndorsementId());
					endorsementgeneraldetailsrepository.save(ent);
					res.setResponse("Updated Successfully ");

				} else {

					// Insert
					List<EndorsementGeneralDetails> list = endorsementgeneraldetailsrepository.findAllByOrderByEndorsementIdDesc();
					Integer endorsementId = 1000;

					if (list.size() != 0) {
						endorsementId = list.get(0).getEndorsementId()+ 1;
					}
					EndorsementGeneralDetails ent = mapper.map(req, EndorsementGeneralDetails.class);
					ent.setEndorsementId(endorsementId);
					endorsementgeneraldetailsrepository.save(ent);

					res.setResponse("Inserted Successfully ");
				}

			} catch (Exception ex) {
				log.error(ex);
				return null;
			}
			return res;
		}	


// Endorsement General Details Get
		@Override
		public EndorsementGeneralDetailsRes getEndorsementGeneral(EndorsementGeneralDetailsGetReq req) {
			EndorsementGeneralDetailsRes res = new EndorsementGeneralDetailsRes();
			try {

				EndorsementGeneralDetailsId id = new EndorsementGeneralDetailsId();
				id.setEndorsementId(req.getEndorsementId());
				id.setPolicyId(req.getPolicyId());				
								
				Optional<EndorsementGeneralDetails> opt = endorsementgeneraldetailsrepository.findById(id);

				if (opt.isPresent()) {

					ModelMapper modelMapper = new ModelMapper();
					res = modelMapper.map(opt.get(), EndorsementGeneralDetailsRes.class);

				}
			} catch (Exception e) {
				res = null;
				log.info("Exception Error", e.getMessage());
			}
			return res;
		}


		
		// Endorsement Payment Details Validation
		@Override
		public List<Error> validateEndorsementPayment(EndorsementPaymentDetailsSaveReq req) {
			List<Error> errors = new ArrayList<Error>();
			try {
				
				if (req.getPaymentDate().toString() == null || StringUtils.isBlank(req.getPaymentDate().toString())) {
					errors.add(new Error("01", "Payment Date", "Please Enter Payment Date"));
				} 
				
				if(req.getBankName()==null || StringUtils.isBlank(req.getBankName())) {
					errors.add(new Error("02","Bank Name","Please Enter Bank Name"));
				}
				if(Integer.valueOf(req.getEndorsementId() )==null || StringUtils.isBlank(Integer.toString(req.getEndorsementId()))) {
					errors.add(new Error("03","Endorsement  Id","Please Enter Endorsement Id"));
				}

				if(Double.valueOf(req.getPaymentAmount() )==null || StringUtils.isBlank(Double.toString(req.getPaymentAmount()))) {
					errors.add(new Error("04","Payment Amount","Please Enter Payment Amount"));
				}
				if (req.getPaymentCollectedDate() .toString() == null || StringUtils.isBlank(req.getPaymentCollectedDate().toString())) {
					errors.add(new Error("05", "Payment Collected Date", "Please Enter Payment Collected Date"));
				}
				if(req.getPaymentRefNo()==null || StringUtils.isBlank(req.getPaymentRefNo())) {
					errors.add(new Error("06","Payment Ref No","Please Enter Payment Ref No"));
				}
				if(req.getPaymentType() ==null || StringUtils.isBlank(req.getPaymentType())) {
					errors.add(new Error("07","Payment Type","Please Enter PaymentType"));
				}
				if(Integer.valueOf(req.getPaymentTypeId() )==null || StringUtils.isBlank(Integer.toString(req.getPaymentTypeId()))) {
					errors.add(new Error("08","Payment Type Id","Please Enter Payment Type Id"));
				}
				if(req.getRemarks() ==null || StringUtils.isBlank(req.getRemarks())) {
					errors.add(new Error("09","Remarks","Please Enter Remarks"));
				}
				

			
			
			}
			catch (Exception e) {
				e.printStackTrace();
				log.info("Exception is --->" + e.getMessage());
				return errors;
			}
			return errors;
		}
		
		// Endorsement Payment Details Save

		@Override
		public EndorsementSuccessRes saveEndorsementPayment(EndorsementPaymentDetailsSaveReq req) {
			EndorsementSuccessRes res = new EndorsementSuccessRes();

			ModelMapper mapper = new ModelMapper();
			try {
				EndorsementPaymentDetailsId id = new EndorsementPaymentDetailsId();

				id.setEndorsementId(req.getEndorsementId());
				id.setEndorsementPaymentId(req.getEndorsementPaymentId());
				
				Optional<EndorsementPaymentDetails> data = endorsementpaymentdetailsrepository.findById(id);
				if (data.isPresent()) {
					// Update
					EndorsementPaymentDetails ent = mapper.map(req, EndorsementPaymentDetails.class);
					ent.setEndorsementPaymentId(req.getEndorsementPaymentId());
					endorsementpaymentdetailsrepository.save(ent);
					res.setResponse("Updated Successfully ");

				} else {

					// Insert
					List<EndorsementPaymentDetails> list = endorsementpaymentdetailsrepository.findAllByOrderByEndorsementPaymentIdDesc();
					Integer endorsementPaymentId = 1000;

					if (list.size() != 0) {
						endorsementPaymentId = list.get(0).getEndorsementPaymentId()+ 1;
					}
					EndorsementPaymentDetails ent = mapper.map(req, EndorsementPaymentDetails.class);
					ent.setEndorsementPaymentId(endorsementPaymentId);
					endorsementpaymentdetailsrepository.save(ent);

					res.setResponse("Inserted Successfully ");
				}

			} catch (Exception ex) {
				log.error(ex);
				return null;
			}
			return res;
		}	


		@Override
		public EndorsementPaymentDetailsRes getEndorsementPayment(EndorsementPaymentDetailsGetReq req) {
			EndorsementPaymentDetailsRes res = new EndorsementPaymentDetailsRes();
			try {

				EndorsementPaymentDetailsId id = new EndorsementPaymentDetailsId();
				id.setEndorsementId(req.getEndorsementId());
				id.setEndorsementPaymentId(req.getEndorsementPaymentId());				
								
				Optional<EndorsementPaymentDetails> opt = endorsementpaymentdetailsrepository.findById(id);

				if (opt.isPresent()) {

					ModelMapper modelMapper = new ModelMapper();
					res = modelMapper.map(opt.get(), EndorsementPaymentDetailsRes.class);

				}
			} catch (Exception e) {
				res = null;
				log.info("Exception Error", e.getMessage());
			}
			return res;
		}


		@Override
		public List<Error> validateEndorsementFinancial(EndorsementFinancialDetailsSaveReq req) {
			List<Error> errors = new ArrayList<Error>();
			try {
				
				if (req.getExpiryDate() .toString() == null || StringUtils.isBlank(req.getExpiryDate().toString())) {
					errors.add(new Error("01", "Expiry Date", "Please Enter Expiry Date"));
				} 
				

				if (req.getStartDate().toString() == null || StringUtils.isBlank(req.getStartDate().toString())) {
					errors.add(new Error("02", "Start Date", "Please Enter Start Date"));
				} 
				
				if(Double.valueOf(req.getCommissionBasePremium()) ==null || StringUtils.isBlank(Double.toString(req.getCommissionBasePremium()))) {
					errors.add(new Error("03","Commission Base Premium","Please Enter Commission Base Premium"));
				}
				if(Integer.valueOf(req.getEndorsementId() )==null || StringUtils.isBlank(Integer.toString(req.getEndorsementId()))) {
					errors.add(new Error("04","Endorsement  Id","Please Enter Endorsement Id"));
				}
				if(Double.valueOf(req.getGst()) ==null || StringUtils.isBlank(Double.toString(req.getGst()))) {
					errors.add(new Error("05","GST","Please Enter GST"));
				}
				if(Double.valueOf(req.getOtherPremium()) ==null || StringUtils.isBlank(Double.toString(req.getOtherPremium()))) {
					errors.add(new Error("06","Other Premium","Please Enter Other Premium"));
				}
				if(Double.valueOf(req.getPremiumWithGst()) ==null || StringUtils.isBlank(Double.toString(req.getPremiumWithGst()))) {
					errors.add(new Error("07","Premium With Gst","Please Enter Premium With Gst"));
				}

				if(Double.valueOf(req.getSumInsured()) ==null || StringUtils.isBlank(Double.toString(req.getSumInsured()))) {
					errors.add(new Error("08","Sum Insured","Please Enter Sum Insured"));
				}
				if(Double.valueOf(req.getTotalPremium()) ==null || StringUtils.isBlank(Double.toString(req.getTotalPremium()))) {
					errors.add(new Error("09","Total Premium","Please Enter Total Premium"));
				}
				

			}
			catch (Exception e) {
				e.printStackTrace();
				log.info("Exception is --->" + e.getMessage());
				return errors;
			}
			return errors;
		}
		
		
		@Override
		public EndorsementSuccessRes saveEndorsementFinancial(EndorsementFinancialDetailsSaveReq req) {
			EndorsementSuccessRes res = new EndorsementSuccessRes();

			ModelMapper mapper = new ModelMapper();
			try {
				EndorsementFinancialDetailsId id = new EndorsementFinancialDetailsId();

				id.setEndorsementId(req.getEndorsementId());
				id.setEndorsmentFinancialId(req.getEndorsmentFinancialId());				
				Optional<EndorsementFinancialDetails> data = endorsementfinancial.findById(id);
				if (data.isPresent()) {
					// Update
					EndorsementFinancialDetails ent = mapper.map(req, EndorsementFinancialDetails.class);
					ent.setEndorsmentFinancialId(req.getEndorsmentFinancialId());;
					endorsementfinancial.save(ent);
					res.setResponse("Updated Successfully ");

				} else {

					// Insert
					List<EndorsementFinancialDetails> list = endorsementfinancial.findAllByOrderByEndorsmentFinancialIdDesc();
					Integer endorsementFinancialId = 1000;

					if (list.size() != 0) {
						endorsementFinancialId = list.get(0).getEndorsmentFinancialId() + 1;
					}
					EndorsementFinancialDetails ent = mapper.map(req, EndorsementFinancialDetails.class);
					ent.setEndorsmentFinancialId(endorsementFinancialId);;
					
					endorsementfinancial.save(ent);

					res.setResponse("Inserted Successfully ");
				}

			} catch (Exception ex) {
				log.error(ex);
				return null;
			}
			return res;
		}	


		@Override
		public EndorsementFinancialDetailsRes getEndorsementFinancial(EndorsementFinancialDetailsGetReq req) {
			EndorsementFinancialDetailsRes res = new EndorsementFinancialDetailsRes();
			try {

				EndorsementFinancialDetailsId id = new EndorsementFinancialDetailsId();
				id.setEndorsementId(req.getEndorsementId());
				id.setEndorsmentFinancialId(req.getEndorsmentFinancialId());			
								
				Optional<EndorsementFinancialDetails> opt = endorsementfinancial.findById(id);

				if (opt.isPresent()) {

					ModelMapper modelMapper = new ModelMapper();
					res = modelMapper.map(opt.get(), EndorsementFinancialDetailsRes.class);

				}
			} catch (Exception e) {
				res = null;
				log.info("Exception Error", e.getMessage());
			}
			return res;
		}




		
}
