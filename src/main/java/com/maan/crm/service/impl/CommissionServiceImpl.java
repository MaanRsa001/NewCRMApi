package com.maan.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.maan.crm.bean.CommissionMotorDetails;
import com.maan.crm.bean.CommissionMotorDetailsId;
import com.maan.crm.bean.CommissionNonMotorDetails;
import com.maan.crm.bean.CommissionNonMotorDetailsId;
import com.maan.crm.repository.CommissionMotorDetailsRepository;
import com.maan.crm.repository.CommissionNonMotorDetailsRepository;
import com.maan.crm.req.CommissionMotorDetailsGetReq;
import com.maan.crm.req.CommissionMotorDetailsSaveReq;
import com.maan.crm.req.CommissionNonMotorDetailsGetReq;
import com.maan.crm.req.CommissionNonMotorDetailsSaveReq;
import com.maan.crm.res.CommissionMotorDetailsRes;
import com.maan.crm.res.CommissionNonMotorDetailsRes;
import com.maan.crm.res.CommissionSuccessRes;
import com.maan.crm.service.CommissionService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class CommissionServiceImpl implements CommissionService {

	
	
	@Autowired
	private CommissionMotorDetailsRepository commissionmotor;
	
	@Autowired
	private CommissionNonMotorDetailsRepository commissionnonmotor;
	
	
	Gson json = new Gson();

	private Logger log = LogManager.getLogger(CommissionServiceImpl.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	// Commission Motor Details Validation
		@Override
		public List<Error> validateCommissionMotor(CommissionMotorDetailsSaveReq req) {
			List<Error> errors = new ArrayList<Error>();
			try {
				
				if (req.getCommGenerationDate() .toString() == null || StringUtils.isBlank(req.getCommGenerationDate().toString())) {
					errors.add(new Error("01",  "Comm Generation Date", "Please Enter Comm Generation Date"));
				}
				if (req.getCommProcessedDate() .toString() == null || StringUtils.isBlank(req.getCommProcessedDate().toString())) {
					errors.add(new Error("02",  "Comm Processed Date", "Please Enter Comm Processed Date"));
				}
				if(Double.valueOf(req.getInsFlat() )==null || StringUtils.isBlank(Double.toString(req.getInsFlat()))) {
					errors.add(new Error("03","Ins Flat","Please Enter Ins Flat"));
				}
				if(Double.valueOf(req.getInsGstAmount() )==null || StringUtils.isBlank(Double.toString(req.getInsGstAmount()))) {
					errors.add(new Error("04","Ins Gst Amount","Please Enter Ins Gst Amount"));
				}
				if(Double.valueOf(req.getInsGstPer() )==null || StringUtils.isBlank(Double.toString(req.getInsGstPer()))) {
					errors.add(new Error("05","Ins Gst Per","Please Enter Ins Gst Per"));
				}
				if(Double.valueOf(req.getInsNetAmount())==null || StringUtils.isBlank(Double.toString(req.getInsNetAmount()))) {
					errors.add(new Error("06","Ins Net Amount","Please Enter Ins Net Amount"));
				}
				if(Double.valueOf(req.getInsNetPer() )==null || StringUtils.isBlank(Double.toString(req.getInsNetPer()))) {
					errors.add(new Error("07","Ins Net Per","Please Enter Ins Net Per"));
				}
				if(Double.valueOf(req.getInsOwnDamageAddOnAmount())==null || StringUtils.isBlank(Double.toString(req.getInsOwnDamageAddOnAmount()))) {
					errors.add(new Error("08","Ins Own Damage Add On Amount","Please Enter Ins Own Damage AddOn Amount"));
				}
				if(Double.valueOf(req.getInsOwnDamageAddOnPer())==null || StringUtils.isBlank(Double.toString(req.getInsOwnDamageAddOnPer()))) {
					errors.add(new Error("09","Ins Own Damage Add On Per","Please Enter Ins Own Damage Add On Per"));
				}
				if(Double.valueOf(req.getInsThirdPartyAmount())==null || StringUtils.isBlank(Double.toString(req.getInsThirdPartyAmount()))) {
					errors.add(new Error("10","Ins Third Party Amount","Please Enter Ins Third Party Amount"));
				}
				if(Double.valueOf(req.getInsThirdPartyPer())==null || StringUtils.isBlank(Double.toString(req.getInsThirdPartyPer()))) {
					errors.add(new Error("11","Ins Third Party Per","Please Enter Ins Third Party Per"));
				}
				if(Double.valueOf(req.getPayoutTotal())==null || StringUtils.isBlank(Double.toString(req.getPayoutTotal()))) {
					errors.add(new Error("12","Payout Total","Please Enter Payout Total"));
				}
				if(req.getPayoutTotalWithGST()==null|| StringUtils.isBlank(Double.toString(req.getPayoutTotalWithGST()))) {
					errors.add(new Error("13","Payout Total with GST","Please Enter Payout Total with GST"));
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				log.info("Exception is --->" + e.getMessage());
				return errors;
			}
			return errors;
		}
		// Commission Motor Details Save
		@Override
		public CommissionSuccessRes saveCommissionMotor(CommissionMotorDetailsSaveReq req) {
			
			CommissionSuccessRes res = new CommissionSuccessRes();
			ModelMapper mapper = new ModelMapper();
			Date entryDate = null;
			try {

				List<CommissionMotorDetails> data = commissionmotor.findByCommissionIdAndPolicyIdOrderByAmendIdDesc(req.getCommissionId(), req.getPolicyId());
				if (data.size()>0) {
					// Update					
					CommissionMotorDetails ent = mapper.map(req, CommissionMotorDetails.class);
					ent.setAmendId(data.get(0).getAmendId() + 1);
					ent.setEntryDate(req.getEntryDate());;
					commissionmotor.save(ent);
					res.setResponse("Updated Successfully ");

				} else {

					// Insert
					List<CommissionMotorDetails> list = commissionmotor.findAllByOrderByCommissionIdDesc();
					Integer commissionId = 1000;

					if (list.size() != 0) {
						commissionId = list.get(0).getCommissionId() + 1;
					}
					
					CommissionMotorDetails ent = mapper.map(req, CommissionMotorDetails.class);
					ent.setAmendId(0);
					ent.setCommissionId(commissionId);
					ent.setEntryDate(new Date());
					commissionmotor.save(ent);

					res.setResponse("Inserted Successfully ");
				}

			} catch (Exception ex) {
				log.error(ex);
				return null;
			}
			return res;
		}	


		// Commission Motor Details Get

		@Override
		public CommissionMotorDetailsRes getCommissionMotor(CommissionMotorDetailsGetReq req) {
			CommissionMotorDetailsRes res = new CommissionMotorDetailsRes();
			try {

				CommissionMotorDetailsId id = new CommissionMotorDetailsId();
				id.setAmendId(req.getAmendId());
				id.setCommissionId(req.getCommissionId());
				id.setPolicyId(req.getPolicyId());
				List<CommissionMotorDetails> data = commissionmotor.findByCommissionIdAndPolicyIdOrderByAmendIdDesc(req.getCommissionId(),req.getPolicyId());
				ModelMapper modelMapper = new ModelMapper();
				res = modelMapper.map(data.get(0), CommissionMotorDetailsRes.class);

				
			} catch (Exception e) {
				res = null;
				log.info("Exception Error", e.getMessage());
			}
			return res;
		}


// Commission Non Motor Validation
		@Override
		public List<Error> validateCommissionNonMotor(CommissionNonMotorDetailsSaveReq req) {
			List<Error> errors = new ArrayList<Error>();
			try {
				
				if (Integer.valueOf(req.getPolicyId()) == null || StringUtils.isBlank(Integer.toString(req.getPolicyId()))) {
					errors.add(new Error("01",  "Policy Id", "Please Enter Policy Id"));
				}
				if (req.getCommGenerationDate() .toString() == null || StringUtils.isBlank(req.getCommGenerationDate().toString())) {
					errors.add(new Error("02",  "Comm Generation Date", "Please Enter Comm Generation Date"));
				}

				if (req.getCommProcessedDate() .toString() == null || StringUtils.isBlank(req.getCommProcessedDate().toString())) {
					errors.add(new Error("03",  "Comm Processed Date", "Please Enter Comm Processed Date"));
				}
				if (Double.valueOf(req.getCommissionAmount()) == null || StringUtils.isBlank(Double.toString(req.getCommissionAmount()))) {
					errors.add(new Error("04",  "Commission Amount", "Please Enter Commission Amount"));
				}
				if (Double.valueOf(req.getCommissionBasePremium()) == null || StringUtils.isBlank(Double.toString(req.getCommissionBasePremium()))) {
					errors.add(new Error("05",  "Commission Base Premium", "Please Enter Commission Base Premium"));
				}
				if (Double.valueOf(req.getCommissionGST() ) == null || StringUtils.isBlank(Double.toString(req.getCommissionGST()))) {
					errors.add(new Error("06",  "Commission GST", "Please Enter Commission GST"));
				}
				if (Double.valueOf(req.getFlatCommissionAmount() ) == null || StringUtils.isBlank(Double.toString(req.getFlatCommissionAmount()))) {
					errors.add(new Error("07",  "Flat Commission Amount", "Please Enter Flat Commission Amount"));
				}
				if (Double.valueOf(req.getFlatPayoutAmount()) == null || StringUtils.isBlank(Double.toString(req.getFlatPayoutAmount()))) {
					errors.add(new Error("08",  "Flat Payout Amount", "Please Enter Flat Payout Amount"));
				}
				if (Double.valueOf(req.getPayoutAmount()) == null || StringUtils.isBlank(Double.toString(req.getPayoutAmount()))) {
					errors.add(new Error("09",  "Payout Amount", "Please Enter Payout Amount"));
				}
				if (Double.valueOf(req.getPayoutAmountPer() ) == null || StringUtils.isBlank(Double.toString(req.getPayoutAmountPer()))) {
					errors.add(new Error("10",  "Payout Amount Per", "Please Enter Payout Amount Per"));
				}
				if (Double.valueOf(req.getPayoutBasePremium() ) == null || StringUtils.isBlank(Double.toString(req.getPayoutBasePremium()))) {
					errors.add(new Error("11",  "Payout Base Premium", "Please Enter Payout Base Premium"));
				}
				if (Double.valueOf(req.getPayoutGST() ) == null || StringUtils.isBlank(Double.toString(req.getPayoutGST()))) {
					errors.add(new Error("12",  "Payout GST", "Please Enter Payout GST"));
				}
				if (Double.valueOf(req.getTotalCommissionAmount() ) == null || StringUtils.isBlank(Double.toString(req.getTotalCommissionAmount()))) {
					errors.add(new Error("13",  "Total Commission Amount", "Please Enter Total Commission Amount"));
				}
				if (Double.valueOf(req.getTotalCommissionWithGST() ) == null || StringUtils.isBlank(Double.toString(req.getTotalCommissionWithGST()))) {
					errors.add(new Error("14",  "Total Commission With GST", "Please Enter Total Commission With GST"));
				}
				if (Double.valueOf(req.getTotalPayoutAmount() ) == null || StringUtils.isBlank(Double.toString(req.getTotalPayoutAmount()))) {
					errors.add(new Error("15",  "Total Payout Amount", "Please Enter Total Payout Amount"));
				}
				if (Double.valueOf(req.getTotalPayoutWithGST() ) == null || StringUtils.isBlank(Double.toString(req.getTotalPayoutWithGST()))) {
					errors.add(new Error("16",  "Total Payout With GST", "Please Enter Total Payout With GST"));
				}
				
					
			}
			catch (Exception e) {
				e.printStackTrace();
				log.info("Exception is --->" + e.getMessage());
				return errors;
			}
			return errors;
		}

// Commission Non Motor Insert
		@Override
		public CommissionSuccessRes saveCommissionNonMotor(CommissionNonMotorDetailsSaveReq req) {
			// TODO Auto-generated method stub
			CommissionSuccessRes res = new CommissionSuccessRes();
			ModelMapper mapper = new ModelMapper();
			Date entryDate = null;
			try {

				List<CommissionNonMotorDetails> data = commissionnonmotor.findByCommissionIdAndPolicyIdOrderByAmendIdDesc(req.getCommissionId(), req.getPolicyId());
				if (data.size()>0) {
					// Update					
					CommissionNonMotorDetails ent = mapper.map(req, CommissionNonMotorDetails.class);
					ent.setAmendId(data.get(0).getAmendId() + 1);
					ent.setEntryDate(req.getEntryDate());
					commissionnonmotor.save(ent);
					res.setResponse("Updated Successfully ");

				} else {

					// Insert
					List<CommissionNonMotorDetails> list = commissionnonmotor.findAllByOrderByCommissionIdDesc();
					Integer commissionId = 1000;

					if (list.size() != 0) {
						commissionId = list.get(0).getCommissionId() + 1;
					}
					
					CommissionNonMotorDetails ent = mapper.map(req, CommissionNonMotorDetails.class);
					ent.setAmendId(0);
					ent.setCommissionId(commissionId);
					ent.setEntryDate(new Date());
					commissionnonmotor.save(ent);

					res.setResponse("Inserted Successfully ");
				}

			} catch (Exception ex) {
				log.error(ex);
				return null;
			}
			return res;
		}	

         //Commission Non Motor Get
		@Override
		public CommissionNonMotorDetailsRes getCommissionNonMotor(CommissionNonMotorDetailsGetReq req) {
			// TODO Auto-generated method stub
			CommissionNonMotorDetailsRes res = new CommissionNonMotorDetailsRes();
			try {

				CommissionNonMotorDetailsId id = new CommissionNonMotorDetailsId();
				id.setAmendId(req.getAmendId());
				id.setCommissionId(req.getCommissionId());
				id.setPolicyId(req.getPolicyId());
				List<CommissionNonMotorDetails> data = commissionnonmotor.findByCommissionIdAndPolicyIdOrderByAmendIdDesc(req.getCommissionId(),req.getPolicyId());
				ModelMapper modelMapper = new ModelMapper();
				res = modelMapper.map(data.get(0), CommissionNonMotorDetailsRes.class);
				res.setEntryDate(data.get(0).getEntryDate());;
				
			} catch (Exception e) {
				res = null;
				log.info("Exception Error", e.getMessage());
			}
			return res;
		}




		
}
