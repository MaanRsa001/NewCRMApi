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
import com.maan.crm.repository.CompanyTypeMasterRepository;
import com.maan.crm.req.CompanyTypeMasterReq;
import com.maan.crm.res.CompanyTypeMasterRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.CompanyTypeMasterService;

@Service
@Transactional
public class CompanyTypeMasterServiceImpl implements CompanyTypeMasterService {

	private Logger log = LogManager.getLogger(CompanyTypeMasterServiceImpl.class);

	@Autowired
	private CompanyTypeMasterRepository repository;

	@Override
	public List<CompanyTypeMasterRes> getall(CompanyTypeMasterReq req) {

		List<CompanyTypeMasterRes> resList = new ArrayList<CompanyTypeMasterRes>();
		try {
			List<CompanyTypeMaster> entlist = repository.findByInsCompanyIdOrderByEntryDate(req.getInsCompanyId());
			for (CompanyTypeMaster companyTypeMaster : entlist) {
				
				ModelMapper modelMapper = new ModelMapper();
				CompanyTypeMasterRes res = modelMapper.map(companyTypeMaster, CompanyTypeMasterRes.class);
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
	public CompanyTypeMasterRes get(CompanyTypeMasterReq req) {

		CompanyTypeMasterRes res = new CompanyTypeMasterRes();
		try {

			CompanyTypeMasterId id = new CompanyTypeMasterId();
			id.setInsCompanyId(req.getInsCompanyId());
			id.setSNo(req.getSNo());
			
			Optional<CompanyTypeMaster> ent = repository.findById(id );

			ModelMapper modelMapper = new ModelMapper();
			res = modelMapper.map(ent.get(), CompanyTypeMasterRes.class);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public SuccessRes insert(CompanyTypeMasterReq req) {

		SuccessRes res = new SuccessRes();
		try {
			CompanyTypeMasterId id = new CompanyTypeMasterId();
			id.setInsCompanyId(req.getInsCompanyId());
			id.setSNo(req.getSNo());
			
			Optional<CompanyTypeMaster> opt = repository.findById(id );


			if (opt.isPresent()) {

				ModelMapper modelMapper = new ModelMapper();
				CompanyTypeMaster ent = modelMapper.map(req, CompanyTypeMaster.class);
				repository.save(ent);
				res.setResponse("Updated Successful");
				res.setSucessId(req.getSNo().toString());
				
			} else {

				Long branchcode = repository.countByInsCompanyIdOrderByEntryDate(req.getInsCompanyId());
				branchcode = branchcode + 1L;

				ModelMapper modelMapper = new ModelMapper();
				CompanyTypeMaster ent = modelMapper.map(req, CompanyTypeMaster.class);
				ent.setEntryDate(new Date());
				ent.setSNo(Integer.valueOf(String.valueOf(branchcode)));
				repository.save(ent);
				res.setResponse("Inserted Successful");
				res.setSucessId(branchcode.toString());
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;

	}

}
