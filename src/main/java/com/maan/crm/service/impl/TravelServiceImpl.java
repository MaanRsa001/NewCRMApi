package com.maan.crm.service.impl;

import java.util.Arrays;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.maan.crm.req.LoginRequest;
import com.maan.crm.req.RequestProposerInfo;
import com.maan.crm.service.TravelService;

@Service
@Transactional
public class TravelServiceImpl implements TravelService {

	private Logger log = LogManager.getLogger(TravelServiceImpl.class);
	
	@Value(value = "${TravelInfoList}")
	private String TravelInfoList;

	@Value(value = "${TravelInfo}")
	private String TravelInfo;
	
	@Value(value = "${TravelLogin}")
	private String TravelLogin;

	private String getToken() {
		
		LoginRequest request = new LoginRequest();
		request.setBranchCode("01");
		request.setLoginType("Broker");
		request.setPassword("Admin@01");
		request.setUserId("maanmotor");
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> entityReq = new HttpEntity<>(request, headers);
	 
		ResponseEntity<Object> response = restTemplate.postForEntity(TravelLogin, entityReq, Object.class);
		
		LinkedHashMap<String, Object> object = (LinkedHashMap<String, Object>) response.getBody();
		LinkedHashMap<String, Object> result = (LinkedHashMap<String, Object>) object.get("LoginResponse");
		String Token = result.get("Token").toString();
		
		return Token;

		
	}
	
	@Override
	public Object GetTravelPolicyDetails(RequestProposerInfo req) {

		Object res = null;
		
		try {

				String Token = getToken();
			
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.set("Authorization", "Bearer " + Token);
				HttpEntity<Object> entityReq = new HttpEntity<>(req, headers);
			 
				ResponseEntity<Object> response = restTemplate.postForEntity(TravelInfo, entityReq, Object.class);
				res = response.getBody();
			
				
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;

	}

	@Override
	public Object GettravelInfoList(RequestProposerInfo req) {

		Object res = null;
		
		try {

				String Token = getToken();
			
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.set("Authorization", "Bearer " + Token);
				HttpEntity<Object> entityReq = new HttpEntity<>(req, headers);
			 
				ResponseEntity<Object> response = restTemplate.postForEntity(TravelInfoList, entityReq, Object.class);
				res = response.getBody();
			
				
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;

	
	}


}
