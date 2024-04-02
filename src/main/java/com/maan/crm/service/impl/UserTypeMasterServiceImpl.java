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

import com.maan.crm.bean.CompanyTypeMaster;
import com.maan.crm.bean.CompanyTypeMasterId;
import com.maan.crm.bean.UsertypeMaster;
import com.maan.crm.bean.UsertypeMasterId;
import com.maan.crm.repository.CompanyTypeMasterRepository;
import com.maan.crm.repository.UserTypeMasterRepository;
import com.maan.crm.req.CompanyTypeMasterReq;
import com.maan.crm.req.UsertypeMasterReq;
import com.maan.crm.res.CompanyTypeMasterRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.UsertypeMasterRes;
import com.maan.crm.service.CompanyTypeMasterService;
import com.maan.crm.service.UserTypeMasterService;

@Service
@Transactional
public class UserTypeMasterServiceImpl implements UserTypeMasterService {

	private Logger log = LogManager.getLogger(UserTypeMasterServiceImpl.class);

	@Autowired
	private UserTypeMasterRepository repository;

	@Override
	public List<UsertypeMasterRes> getall(UsertypeMasterReq req) {

		List<UsertypeMasterRes> resList = new ArrayList<UsertypeMasterRes>();
		try {
			List<UsertypeMaster> entlist = repository.findByCompanyIdOrderByEntryDate(req.getCompanyId());
			for (UsertypeMaster companyTypeMaster : entlist) {
				
				ModelMapper modelMapper = new ModelMapper();
				UsertypeMasterRes res = modelMapper.map(companyTypeMaster, UsertypeMasterRes.class);
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;

	}

	@Override
	public UsertypeMasterRes get(UsertypeMasterReq req) {

		UsertypeMasterRes res = new UsertypeMasterRes();
		try {

			UsertypeMasterId id = new UsertypeMasterId();
			id.setCompanyId(req.getCompanyId());
			id.setUsertypeId(req.getUsertypeId());

			Optional<UsertypeMaster> ent = repository.findById(id);

			ModelMapper modelMapper = new ModelMapper();
			res = modelMapper.map(ent.get(), UsertypeMasterRes.class);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;

	}

	@Override
	public SuccessRes insert(UsertypeMasterReq req) {

		SuccessRes res = new SuccessRes();
		try {

			UsertypeMasterId id = new UsertypeMasterId();
			id.setCompanyId(req.getCompanyId());
			id.setUsertypeId(req.getUsertypeId());

			Optional<UsertypeMaster> opt = repository.findById(id);

			if (opt.isPresent()) {

				ModelMapper modelMapper = new ModelMapper();
				UsertypeMaster ent = modelMapper.map(req, UsertypeMaster.class);
				repository.save(ent);
				
			} else {

				Integer code = repository.countByCompanyIdOrderByEntryDate(req.getCompanyId());
				code = code + 1;

				ModelMapper modelMapper = new ModelMapper();
				UsertypeMaster ent = modelMapper.map(req, UsertypeMaster.class);
				ent.setEntryDate(new Date());
				ent.setUsertypeId(code);
				repository.save(ent);

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;

	
	
	}

}
