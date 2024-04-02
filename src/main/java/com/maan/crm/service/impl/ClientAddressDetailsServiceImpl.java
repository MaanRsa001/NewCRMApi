
package com.maan.crm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.maan.crm.bean.ClaimLoginMaster;
import com.maan.crm.bean.ClientAddressDetails;
import com.maan.crm.bean.ClientDetails;
import com.maan.crm.bean.InsuranceCompanyMaster;
import com.maan.crm.notification.mail.dto.MailFramingReq;
import com.maan.crm.notification.service.impl.MailThreadServiceImpl;
import com.maan.crm.repository.ClaimLoginMasterRepository;
import com.maan.crm.repository.ClientAddressDetailsRepository;
import com.maan.crm.repository.ClientDetailsRepository;
import com.maan.crm.repository.InsuranceCompanyMasterRepository;
import com.maan.crm.req.ClientAddressDetailsGetAllReq;
import com.maan.crm.req.ClientAddressDetailsGetReq;
import com.maan.crm.req.ClientAddressDetailsListSaveReq;
import com.maan.crm.req.ClientAddressDetailsSaveReq;
import com.maan.crm.req.ClientDetailsJsonTemReq;
import com.maan.crm.res.ClientAddressDetailsGetRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.ClientAddressDetailsService;

@Service
@Transactional
public class ClientAddressDetailsServiceImpl implements ClientAddressDetailsService {

	@Autowired
	private ClientAddressDetailsRepository repository;
	
	@Autowired
	private InsuranceCompanyMasterRepository insRepo;
	
	@Autowired
	private ClientAddressDetailsRepository clientAddrRepo;
	
	@Autowired
	private MailThreadServiceImpl mailThreadService ;
	
	@Autowired
	private ClaimLoginMasterRepository loginRepo;

	@Autowired
	private ClientDetailsRepository clientrepo;
	
	private Logger log = LogManager.getLogger(ClientAddressDetailsServiceImpl.class);

	Gson json = new Gson();

	// SAVE CLIENT ADDRESS DETAILS
	@Override
	@Transactional
	public SuccessRes saveClientAddressDetails(ClientDetailsJsonTemReq req1) {
		SuccessRes res = new SuccessRes();

		//ClientAddressDetailsListSaveReq req = req1.getClientAddressDetails();
		
		ClientAddressDetails entity = new ClientAddressDetails();

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		Integer addressId = 0;
		Date entryDate = null;
		try {
			List<ClientAddressDetails> findDatas = repository.findByClientRefNo(req1.getClientDetails().getClientRefNo());

			for (ClientAddressDetailsSaveReq data : req1.getClientAddressDetails()) {
				if (StringUtils.isBlank(data.getClientAddressId())) {
					// Save
					Long totalCount = repository.count();

					addressId = Integer.valueOf(totalCount.toString()) + 10001;
					entryDate = new Date();

					res.setResponse("Saved Successfully ");

				} else {
					// Update

					addressId = Integer.valueOf(data.getClientAddressId());
					List<ClientAddressDetails> getData = findDatas.stream()
							.filter(o -> o.getClientAddressId().equals(Integer.valueOf(data.getClientAddressId())))
							.collect(Collectors.toList());
					if (getData == null) {
						entryDate = new Date();
					} else {
						entryDate = getData.get(0).getEntryDate();
					}
					res.setResponse("Updated Successfully ");

				}
				entity = mapper.map(data, ClientAddressDetails.class);
				entity.setEntryDate(entryDate);
				entity.setClientAddressId(addressId);
				entity.setStatus("Y");
				entity.setClientRefNo(req1.getClientDetails().getClientRefNo());
				repository.saveAndFlush(entity);
				log.info("Saved Details is ---> " + json.toJson(entity));
				
				
			}
			

		} catch (Exception ex) {
			log.error(ex);
			return null;
		}
		return res;
	}

	// GET ALL CLIENT ADDRESS DETAILS
	@Override
	public List<ClientAddressDetailsGetRes> getAllClientAddressDetails(ClientAddressDetailsGetAllReq req) {
		List<ClientAddressDetailsGetRes> resList = new ArrayList<ClientAddressDetailsGetRes>();

		ModelMapper mapper = new ModelMapper();
		try {

			// Find
			List<ClientAddressDetails> clientAddress = repository
					.findByClientRefNoOrderByAddresTypeIdAsc(req.getClientRefNo());

			// Map
			for (ClientAddressDetails data : clientAddress) {
				ClientAddressDetailsGetRes res = new ClientAddressDetailsGetRes();

				res = mapper.map(data, ClientAddressDetailsGetRes.class);
				mapper.getConfiguration().setAmbiguityIgnored(true);
				res.setClientAddressId(data.getClientAddressId().toString());
				resList.add(res);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return resList;
	}

	// Get Client ADDRESS Details By ID
	@Override
	public ClientAddressDetailsGetRes getClientAddressDetailsById(ClientAddressDetailsGetReq req) {
		ClientAddressDetailsGetRes res = new ClientAddressDetailsGetRes();

		ModelMapper mapper = new ModelMapper();
		try {
			String clientRefNo = req.getClientRefNo();
			Integer addressId = Integer.valueOf(req.getClientAddressId());
			ClientAddressDetails data = repository.findByClientRefNoAndClientAddressId(clientRefNo, addressId);

			res = mapper.map(data, ClientAddressDetailsGetRes.class);
			res.setClientAddressId(addressId.toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}

}
