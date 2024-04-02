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
import com.maan.crm.bean.ClientAddressDetails;
import com.maan.crm.bean.ClientCorContact;
import com.maan.crm.repository.ClientCorContactRepository;
import com.maan.crm.req.ClientCorContactGetAllReq;
import com.maan.crm.req.ClientCorContactReq;
import com.maan.crm.req.ClientCrConGetReq;
import com.maan.crm.req.ClientCrConListSaveReq;
import com.maan.crm.req.ClientCrContactGetAllReq;
import com.maan.crm.req.ClientDetailsJsonTemReq;
import com.maan.crm.res.ClientCrContactGetRes;
import com.maan.crm.res.ClientCorContactGetAllRes;
import com.maan.crm.res.ClientCorContactListGeAlltRes;
import com.maan.crm.res.ClientCrContactGetRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.ClientCorContactService;

@Service
@Transactional
public class ClientCorContactServiceImpl implements ClientCorContactService {

	@Autowired
	private ClientCorContactRepository repository;

	private Logger log = LogManager.getLogger(ClientCorContactServiceImpl.class);
	Gson json = new Gson();

	// Save/Insert Client Corporate Contact
	@Override
	@Transactional
	public SuccessRes saveClientCorContact(ClientDetailsJsonTemReq req1) {

		//ClientCrConListSaveReq req = req1.getClientCorDetails();
		
		SuccessRes res = new SuccessRes();

		ClientCorContact entity = new ClientCorContact();

		ModelMapper mapper = new ModelMapper();

		Integer contactId = 0;
		Date entryDate = null;
		try {
			List<ClientCorContact> findDatas = repository.findByClientRefNo(req1.getClientDetails().getClientRefNo());

			for (ClientCorContactReq data : req1.getClientCorDetails()) {
				if (StringUtils.isBlank(data.getContactId())) {
					// Save
					Long totalCount = repository.count();

					contactId = Integer.valueOf(totalCount.toString()) + 10001;
					entryDate = new Date();

					res.setResponse("Saved Successfully ");

				} else {
					// Update

					contactId = Integer.valueOf(data.getContactId());
					List<ClientCorContact> getData = findDatas.stream()
							.filter(o -> o.getClientcorContactId().equals(Integer.valueOf(data.getContactId())))
							.collect(Collectors.toList());
					if (getData == null) {
						entryDate = new Date();
					} else {
						entryDate = getData.get(0).getEntryDate();
					}
					res.setResponse("Updated Successfully ");

				}
				entity = mapper.map(data, ClientCorContact.class);
				entity.setEntryDate(entryDate);
				entity.setClientcorContactId(contactId);
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

	// GetAll Client Corporate Contact
	@Override
	public List<ClientCrContactGetRes> getAllClientCorContact(ClientCrContactGetAllReq req) {
		List<ClientCrContactGetRes> resList = new ArrayList<ClientCrContactGetRes>();
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ModelMapper mapper = new ModelMapper();
		try {

			// Find
			List<ClientCorContact> corContact = repository
					.findByClientRefNoOrderByClientcorContactIdAsc(req.getClientRefNo());

			// Map
			for (ClientCorContact data : corContact) {
				ClientCrContactGetRes res = new ClientCrContactGetRes();

				res = mapper.map(data, ClientCrContactGetRes.class);

			//	res.setEntryDate(data.getEntryDate() == null ? "" : sdf.format(data.getEntryDate()));

				resList.add(res);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return resList;
	}

	// Get Client Corporate Contact
	@Override
	public ClientCrContactGetRes getClientCorContactById(ClientCrConGetReq req) {
		ClientCrContactGetRes res = new ClientCrContactGetRes();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ModelMapper mapper = new ModelMapper();
		try {
			String clientRefNo = req.getClientRefNo();
			Integer contactId = Integer.valueOf(req.getClientcorContactId());
			ClientCorContact data = repository.findByClientRefNoAndClientcorContactId(clientRefNo, contactId);

			res = mapper.map(data, ClientCrContactGetRes.class);

			//res.setEntryDate(data.getEntryDate() == null ? "" : sdf.format(data.getEntryDate()));

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}

}
