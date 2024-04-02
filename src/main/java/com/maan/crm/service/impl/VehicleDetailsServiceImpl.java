
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.maan.crm.bean.VehicleDetails;
import com.maan.crm.repository.VehicleDetailsRepository;
import com.maan.crm.req.VehicleDetailsGetAllReq;
import com.maan.crm.req.VehicleDetailsGetReq;
import com.maan.crm.req.VehicleDetailsSaveReq;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehicleDetailsGridRes;
import com.maan.crm.res.VehicleDetailsRes;
import com.maan.crm.service.VehicleDetailsService;

@Service
@Transactional
public class VehicleDetailsServiceImpl implements VehicleDetailsService {

	@Autowired
	private VehicleDetailsRepository vehiclerepo;

	private Logger log = LogManager.getLogger(VehicleDetailsServiceImpl.class);

	Gson json = new Gson();

	// SAVE Vehicle DETAILS
	@Override
	@Transactional
	public SuccessRes saveVehDetail(VehicleDetailsSaveReq req) {
		SuccessRes res = new SuccessRes();
		ModelMapper mapper = new ModelMapper();

		try {
			VehicleDetails saveVehicleDetails = new VehicleDetails();

			Integer vehCode = 0;
			String chassisNo = req.getVehChassisNo();
			Date entryDate = null;
			VehicleDetails findData = vehiclerepo.findByVehChassisNo(chassisNo);
			if (findData==null) {

				// Save
				Integer totalCount = Integer.valueOf(String.valueOf(vehiclerepo.count()));
				vehCode = totalCount + 1000;
				entryDate = new Date();
				res.setResponse("Saved Succesfully");

			} else { 
				// Update
				vehCode = findData.getVehCode();
				entryDate = findData.getEntryDate();
				res.setResponse("Updated Succesfully");
			}

			// Mapper
			saveVehicleDetails = mapper.map(req, VehicleDetails.class);
			saveVehicleDetails.setVehChassisNo(chassisNo);
			saveVehicleDetails.setEntryDate(entryDate);
			saveVehicleDetails.setVehCode(Integer.valueOf(vehCode));

			vehiclerepo.save(saveVehicleDetails);
			log.info("Saved Details is ---> " + json.toJson(saveVehicleDetails));
			res.setSucessId(String.valueOf(vehCode));
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}

	// GET ALL Vehicle DETAILS
	@Override
	public List<VehicleDetailsGridRes> getAllVehicleInfo(VehicleDetailsGetAllReq req) {
		List<VehicleDetailsGridRes> resList = new ArrayList<VehicleDetailsGridRes>();

		ModelMapper mapper = new ModelMapper();
		try {
			// Limit , Offset
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 0 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset, Sort.by("entryDate").descending());

			// Find
			Page<VehicleDetails> VehicleDetails = vehiclerepo.findByCompanyIdAndBranchCode(paging ,req.getInsId(),req.getBranchCode());

			// Map
			for (VehicleDetails data : VehicleDetails.getContent()) {
				VehicleDetailsGridRes res = new VehicleDetailsGridRes();

				res = mapper.map(data, VehicleDetailsGridRes.class);

				resList.add(res);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return resList;
	}

	// Get Vehicle Details By ID
	@Override
	public VehicleDetailsRes getVehDetailsById(VehicleDetailsGetReq req) {
		VehicleDetailsRes res = new VehicleDetailsRes();

		ModelMapper mapper = new ModelMapper();
		try {
			String VehicleRefNo = req.getVehChassisNo();
			VehicleDetails data = vehiclerepo.findByVehChassisNo(VehicleRefNo);

			res = mapper.map(data, VehicleDetailsRes.class);

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}

}
