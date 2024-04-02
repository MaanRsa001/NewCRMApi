package com.maan.crm.service.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maan.crm.bean.SmsConfigMaster;
import com.maan.crm.repository.SmsConfigMasterRepository;
import com.maan.crm.req.SmsConfigMasterReq;
import com.maan.crm.res.SmsConfigMasterRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.SmsConfigMasterService;

@Service
@Transactional
public class SmsConfigMasterServiceImpl implements SmsConfigMasterService {

	private Logger log = LogManager.getLogger(SmsConfigMasterServiceImpl.class);

	@Autowired
	private SmsConfigMasterRepository repository;

	@Override
	public SmsConfigMasterRes get(SmsConfigMasterReq req) {

		SmsConfigMasterRes res = new SmsConfigMasterRes();
		try {
			Optional<SmsConfigMaster> ent = repository.findById(req.getInsId());

			ModelMapper modelMapper = new ModelMapper();
			res = modelMapper.map(ent.get(), SmsConfigMasterRes.class);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public SuccessRes insert(SmsConfigMasterReq req) {

		SuccessRes res = new SuccessRes();
		try {

			SmsConfigMaster ent = new SmsConfigMaster();
			Optional<SmsConfigMaster> opt = repository.findById(req.getInsId());
			if (opt.isPresent()) {

				ModelMapper modelMapper = new ModelMapper();
				ent = modelMapper.map(req, SmsConfigMaster.class);

			} else {

				ModelMapper modelMapper = new ModelMapper();
				ent.setEntryDate(new Date());
				ent = modelMapper.map(req, SmsConfigMaster.class);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;

	}

}
