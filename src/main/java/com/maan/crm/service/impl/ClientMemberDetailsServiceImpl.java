package com.maan.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import com.maan.crm.bean.ClientCorContact;
import com.maan.crm.bean.ClientMemberDetails;
import com.maan.crm.repository.ClientMemberDetailsRepository;
import com.maan.crm.req.ClientDetailsJsonTemReq;
import com.maan.crm.req.ClientMemberDetailsGetAllReq;
import com.maan.crm.req.ClientMemberDetailsGetReq;
import com.maan.crm.req.ClientMemberDetailsListSaveReq;
import com.maan.crm.req.ClientMemberDetailsReq;
import com.maan.crm.res.ClientMemberDetailsGetRes;
import com.maan.crm.res.ClientMemberDetailsGetRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.ClientMemberDetailsService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class ClientMemberDetailsServiceImpl implements ClientMemberDetailsService {

	@Autowired
	private ClientMemberDetailsRepository repository;

	Gson json = new Gson();

	private Logger log = LogManager.getLogger(ClientMemberDetailsServiceImpl.class);

	@Transactional
	@Override
	public List<Error> validateClientMemberDetails(ClientDetailsJsonTemReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	// Insert/Save Client Member Details
	// *********************************
	@Override
	public SuccessRes saveClientMemberDetails(ClientDetailsJsonTemReq req1) {
		
		//ClientMemberDetailsListSaveReq req = req1.getClientMemberDetails();
		
		SuccessRes res = new SuccessRes();

		ClientMemberDetails entity = new ClientMemberDetails();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");

		ModelMapper mapper = new ModelMapper();

		Integer memberId = 0;
		Date entryDate = null;
		try {
			List<ClientMemberDetails> findDatas = repository.findByClientRefNo(req1.getClientDetails().getClientRefNo());

			for (ClientMemberDetailsReq data : req1.getClientMemberDetails()) {
				if (StringUtils.isBlank(data.getClientMemberId())) {
					// Save
					Long totalCount = repository.count();

					memberId = Integer.valueOf(totalCount.toString()) + 10001;
					entryDate = new Date();

					res.setResponse("Saved Successfully ");

				} else {
					// Update

					memberId = Integer.valueOf(data.getClientMemberId());
					List<ClientMemberDetails> getData = findDatas.stream()
							.filter(o -> o.getClientMemberId().equals(Integer.valueOf(data.getClientMemberId())))
							.collect(Collectors.toList());
					if (getData == null) {
						entryDate = new Date();
					} else {
						entryDate = getData.get(0).getEntryDate();
					}
					res.setResponse("Updated Successfully ");

				}
				// data.setDateOfBirth(dbf.format(sdf.parse(data.getDateOfBirth())));

				entity = mapper.map(data, ClientMemberDetails.class);
				entity.setEntryDate(entryDate);
				entity.setClientMemberId(memberId);
				entity.setStatus("Y");
				entity.setClientRefNo(req1.getClientDetails().getClientRefNo());
				repository.save(entity);
				log.info("Saved Details is ---> " + json.toJson(entity));
			}
		} catch (Exception ex) {
			log.error(ex);
			return null;
		}
		return res;
	}

	// Get Client Member Details
	// *************************
	@Override
	public ClientMemberDetailsGetRes getClientMemDetailsById(ClientMemberDetailsGetReq req) {
		ClientMemberDetailsGetRes res = new ClientMemberDetailsGetRes();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ModelMapper mapper = new ModelMapper();
		try {
			String clientRefNo = req.getClientRefNo();
			Integer memberId = Integer.valueOf(req.getClientMemberId());
			ClientMemberDetails data = repository.findByClientRefNoAndClientMemberId(clientRefNo, memberId);

			res = mapper.map(data, ClientMemberDetailsGetRes.class);

			// res.setEntryDate(data.getEntryDate() == null ? "" :
			// sdf.format(data.getEntryDate()));
			// res.setDateOfBirth(data.getDateOfBirth() == null ? "" :
			// sdf.format(data.getDateOfBirth()));

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}

	// GetAll Client Member Details
	// *************************
	@Override
	public List<ClientMemberDetailsGetRes> getAllClientMemDetails(ClientMemberDetailsGetAllReq req) {
		List<ClientMemberDetailsGetRes> resList = new ArrayList<ClientMemberDetailsGetRes>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ModelMapper mapper = new ModelMapper();
		try {

			// Find
			List<ClientMemberDetails> clientMem = repository
					.findByClientRefNoOrderByClientMemberIdAsc(req.getClientRefNo());

			// Map
			for (ClientMemberDetails data : clientMem) {
				ClientMemberDetailsGetRes res = new ClientMemberDetailsGetRes();

				res = mapper.map(data, ClientMemberDetailsGetRes.class);

				// res.setEntryDate(data.getEntryDate() == null ? "" :
				// sdf.format(data.getEntryDate()));
				// res.setDateOfBirth(data.getDateOfBirth() == null ? "" :
				// sdf.format(data.getDateOfBirth()));
				resList.add(res);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return resList;
	}
}
