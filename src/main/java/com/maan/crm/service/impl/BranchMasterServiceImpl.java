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
import com.maan.crm.repository.BranchMasterRepository;
import com.maan.crm.req.BranchMasterReq;
import com.maan.crm.res.BranchMasterRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.BranchMasterService;

@Service
@Transactional
public class BranchMasterServiceImpl implements BranchMasterService {

	private Logger log = LogManager.getLogger(BranchMasterServiceImpl.class);

	@Autowired
	private BranchMasterRepository repository;

	@Override
	public List<BranchMasterRes> getall(BranchMasterReq req) {

		List<BranchMasterRes> resList = new ArrayList<BranchMasterRes>();
		try {
			List<BranchMaster> entlist = repository.findByInsCompanyIdOrderByEntryDate(req.getInsCompanyId());
			for (BranchMaster branchMaster : entlist) {
				ModelMapper modelMapper = new ModelMapper();
				BranchMasterRes res = modelMapper.map(branchMaster, BranchMasterRes.class);
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
	public BranchMasterRes get(BranchMasterReq req) {

		BranchMasterRes res = new BranchMasterRes();
		try {
			BranchMasterId id = new BranchMasterId();
			id.setBranchCode(req.getBranchCode());
			id.setInsCompanyId(req.getInsCompanyId());
			id.setRegionCode(req.getRegionCode());
			Optional<BranchMaster> ent = repository.findById(id);

			ModelMapper modelMapper = new ModelMapper();
			res = modelMapper.map(ent.get(), BranchMasterRes.class);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public SuccessRes insertBranch(BranchMasterReq req) {

		SuccessRes res = new SuccessRes();
		try {
			BranchMasterId id = new BranchMasterId();
			id.setBranchCode(req.getBranchCode());
			id.setInsCompanyId(req.getInsCompanyId());
			id.setRegionCode(req.getRegionCode());
			Optional<BranchMaster> opt = repository.findById(id);

			if (opt.isPresent()) {

				ModelMapper modelMapper = new ModelMapper();
				BranchMaster ent = modelMapper.map(req, BranchMaster.class);
				repository.save(ent);

			} else {

				String code = "";
				Long branchcode = repository.countByInsCompanyIdOrderByEntryDate(req.getInsCompanyId());
				branchcode = branchcode + 1L;
				if (branchcode < 9) {
					code = "0" + branchcode.toString();
				} else {
					code = branchcode.toString();
				}

				ModelMapper modelMapper = new ModelMapper();
				BranchMaster ent = modelMapper.map(req, BranchMaster.class);
				ent.setBranchCode(code);
				ent.setEntryDate(new Date());
				repository.save(ent);
				res.setSucessId(code);
				res.setResponse("Insert Successful");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;

	}

}
