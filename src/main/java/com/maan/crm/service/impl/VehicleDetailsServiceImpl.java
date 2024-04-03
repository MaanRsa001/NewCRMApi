
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
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class VehicleDetailsServiceImpl implements VehicleDetailsService {

	@Autowired
	private VehicleDetailsRepository vehiclerepo;

	private Logger log = LogManager.getLogger(VehicleDetailsServiceImpl.class);

	Gson json = new Gson();

	@Override
	public List<Error> validateVehicle(VehicleDetailsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
//			if (req.getCc() == null || StringUtils.isBlank(req.getCc())) {
//				errors.add(new Error("01", "CC", "Please Enter CC"));
//			} else if (req.getCc().length() > 100) {
//				errors.add(new Error("01", "CC", "Please Enter CC within 100 Character"));
//			}
			if (req.getColorId() == null || StringUtils.isBlank(req.getColorId())) {
				errors.add(new Error("02", "Color Id", "Please Enter Color Id"));
			} else if (!StringUtils.isNumeric(req.getColorId())) {
				errors.add(new Error("02", "Color Id", "Please Enter Color Id in numbers"));
			}
			if (req.getColorVariant() == null || StringUtils.isBlank(req.getColorVariant())) {
				errors.add(new Error("03", "Color Variant", "Please Enter Color Variant"));
			} else if (req.getColorVariant().length() > 100) {
				errors.add(new Error("03", "Color Variant", "Please Enter Color Variant within 100 Character"));
			}
			if (req.getEngineNo() == null || StringUtils.isBlank(req.getEngineNo())) {
				errors.add(new Error("04", "Engine No", "Please Enter Engine No"));
			} else if (req.getEngineNo().length() > 100) {
				errors.add(new Error("04", "Engine No", "Please Enter Engine No within 100 Character"));
			}
			if (req.getFueltype() == null || StringUtils.isBlank(req.getFueltype())) {
				errors.add(new Error("05", "Fuel Type", "Please Enter Fuel Type"));
			} else if (req.getFueltype().length() > 100) {
				errors.add(new Error("05", "Fuel Type", "Please Enter Fuel Type within 100 Character"));
			}
			/*
			 * if (req.getFueltypeId() == null || StringUtils.isBlank(req.getFueltypeId()))
			 * { errors.add(new Error("06", "Fuel Type Id", "Please Enter Fuel Type Id")); }
			 */ else if (!StringUtils.isNumeric(req.getFueltypeId())) {
				errors.add(new Error("06", "Fuel Type Id", "Please Enter Fuel Type Id in numbers"));
			}
			if (req.getManufactureYear() == null || StringUtils.isBlank(req.getManufactureYear())) {
				errors.add(new Error("07", "Manufacture Year", "Please Enter Manufature Year"));
			} else if (!StringUtils.isNumeric(req.getManufactureYear())) {
				errors.add(new Error("07", "Manufature Year", "Please Enter Manufacture Year in numbers"));
			} else if (req.getManufactureYear().length() > 4) {
				errors.add(new Error("07", "Manufature Year", "Please Enter Manufature Year in numbers"));
			}
//			if (req.getPlateChar() == null || StringUtils.isBlank(req.getPlateChar())) {
//				errors.add(new Error("08", "Plate Char", "Please Enter Plate Char"));
//			} else if (req.getPlateChar().length() > 100) {
//				errors.add(new Error("08", "Plate Char", "Please Enter Plate Char within 100 Character"));
//			}
//			if (req.getPlateCharId() == null || StringUtils.isBlank(req.getPlateCharId())) {
//				errors.add(new Error("09", "Plate Char", "Please Enter Plate Char"));
//			}
//			if (req.getPlateNo() == null || StringUtils.isBlank(req.getPlateNo())) {
//				errors.add(new Error("10", "Plate No", "Please Enter Plate No"));
//			} else if (!StringUtils.isNumeric(req.getPlateNo())) {
//				errors.add(new Error("10", "Plate No", "Please Enter Plate No in numbers"));
//			}
			if (req.getSeatingCapacity() == null || StringUtils.isBlank(req.getSeatingCapacity())) {
				errors.add(new Error("11", "Seating Capacity", "Please Enter Seating Capacity"));
			} else if (!StringUtils.isNumeric(req.getSeatingCapacity())) {
				errors.add(new Error("11", "Seating Capacity", "Please Enter Seating Capacity in numbers"));
			}
			if (req.getVehBodytype() == null || StringUtils.isBlank(req.getVehBodytype())) {
				errors.add(new Error("12", "Vehicle Body Type", "Please Enter Vehicle Body Type"));
			} else if (req.getVehBodytype().length() > 100) {
				errors.add(new Error("12", "Vehicle Body Type", "Please Enter Vehicle Body Type within 100 Character"));
			}
			if (req.getVehBodytypeId() == null || StringUtils.isBlank(req.getVehBodytypeId())) {
				errors.add(new Error("13", "Vehicle Body Type Id", "Please Enter Vehicle Body Type Id"));
			} else if (!StringUtils.isNumeric(req.getVehBodytypeId())) {
				errors.add(new Error("13", "Vehicle Body Type Id", "Please Enter Vehicle Body Type Id in numbers"));
			}

			if (req.getVehClassificationId() == null || StringUtils.isBlank(req.getVehClassificationId())) {
				errors.add(new Error("14", "Veh Classification Id", "Please Enter Veh Classification Id"));
			} else if (!StringUtils.isNumeric(req.getVehClassificationId())) {
				errors.add(new Error("14", "Veh Classification Id", "Please Enter Veh Classification Id in numbers"));
			}

			if (req.getVehTypeId() == null || StringUtils.isBlank(req.getVehTypeId())) {
				errors.add(new Error("15", "Veh Type Id", "Please Enter Veh Type Id"));
			}
			if (req.getVehMakeId() == null || StringUtils.isBlank(req.getVehMakeId())) {
				errors.add(new Error("16", "Veh Make Id", "Please Enter Veh Make Id"));
			}
			if (req.getVehModelId() == null || StringUtils.isBlank(req.getVehModelId())) {
				errors.add(new Error("17", "Veh Model Id", "Please Enter Veh Model Id"));
			}
			if (req.getGvw() == null || StringUtils.isBlank(req.getGvw())) {
				errors.add(new Error("18", "Gvw", "Please Enter Gvw"));
			}
			if (req.getCarryingPassengers() == null || StringUtils.isBlank(req.getCarryingPassengers().toString())) {
				errors.add(new Error("19", "Passenger Carrying Capacity", "Please Enter Passenger Carrying Capacity"));
			}

			if (req.getVehChassisNo() == null || StringUtils.isBlank(req.getVehChassisNo())) {
				errors.add(new Error("20", "Veh Chassis No", "Please Enter Veh Chassis No"));
			} else if (req.getVehChassisNo().length() > 100) {
				errors.add(new Error("20", "Veh Chassis No", "Please Enter Veh Chassis No within 100 Character"));
			}
			if (req.getHypothication() == null || StringUtils.isBlank(req.getHypothication())) {
				errors.add(new Error("21", "Hypothication", "Please Enter Hypothication"));
			} else if (req.getHypothication().length() > 100) {
				errors.add(new Error("21", "Hypothication", "Please Enter Hypothication within 100 Character"));
			}
//			if (req.getPos() == null || StringUtils.isBlank(req.getPos())) {
//				errors.add(new Error("22", "Pos", "Please Enter Pos"));
//			} else if (req.getPos().length() > 100) {
//				errors.add(new Error("22", "Pos", "Please Enter Pos within 100 Character"));
//			}
//			if (req.getPinCode() == null || StringUtils.isBlank(req.getPinCode())) {
//				errors.add(new Error("23", "Pin Code", "Please Enter Pin Code"));
//			} else if (!StringUtils.isNumeric(req.getPinCode())) {
//				errors.add(new Error("23", "Pin Code", "Please Enter PinCode in numbers"));
//			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return errors;
		}
		return errors;
	}
	
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
			String leadId = req.getLeadId();
			VehicleDetails data = vehiclerepo.findByLeadId(leadId);

			res = mapper.map(data, VehicleDetailsRes.class);

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}

}
