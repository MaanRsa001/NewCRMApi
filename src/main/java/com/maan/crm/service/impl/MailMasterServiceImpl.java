package com.maan.crm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maan.crm.bean.BranchMaster;
import com.maan.crm.bean.BranchMasterId;
import com.maan.crm.bean.MailMaster;
import com.maan.crm.repository.BranchMasterRepository;
import com.maan.crm.repository.MailMasterRepository;
import com.maan.crm.req.BranchMasterReq;
import com.maan.crm.req.MailMasterReq;
import com.maan.crm.res.BranchMasterRes;
import com.maan.crm.res.MailMasterRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.BranchMasterService;
import com.maan.crm.service.MailMasterService;

@Service
@Transactional
public class MailMasterServiceImpl implements MailMasterService {

	private Logger log = LogManager.getLogger(MailMasterServiceImpl.class);

	@Autowired
	private MailMasterRepository repository;

	@Override
	public MailMasterRes get(MailMasterReq req) {

		MailMasterRes res = new MailMasterRes();
		try {
			Optional<MailMaster> ent = repository.findById(req.getInsCompanyId());

			ModelMapper modelMapper = new ModelMapper();
			res = modelMapper.map(ent.get(), MailMasterRes.class);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public SuccessRes insert(MailMasterReq req) {

		SuccessRes res = new SuccessRes();
		try {

			MailMaster ent = new MailMaster();
			Optional<MailMaster> opt = repository.findById(req.getInsCompanyId());
			if (opt.isPresent()) {

				ModelMapper modelMapper = new ModelMapper();
				ent = modelMapper.map(req, MailMaster.class);

			} else {

				ModelMapper modelMapper = new ModelMapper();
				ent.setEntrydate(new Date());
				ent = modelMapper.map(req, MailMaster.class);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;

	}

}
