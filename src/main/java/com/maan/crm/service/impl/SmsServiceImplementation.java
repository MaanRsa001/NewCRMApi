package com.maan.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maan.crm.bean.SmsDataDetails;
import com.maan.crm.notification.service.impl.NotificationServiceImpl;
import com.maan.crm.repository.SmsDataDetailsRepository;
import com.maan.crm.req.SmsSaveReq;
import com.maan.crm.res.SuccessRes;



@Service
@Transactional
public class SmsServiceImplementation {


	
	
	private Logger log = LogManager.getLogger(NotificationServiceImpl.class);

	public SuccessRes sms(SmsSaveReq req) {
		SuccessRes res = new SuccessRes();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmssSSS");
		try {
			SmsDataDetails data = new SmsDataDetails();
			Date today = new Date();
			String id = sdf.format(today);
			mapper.map(req, SmsDataDetails.class);
			data.setSNo(Integer.valueOf(id));
			data.setCreatedBy(req.getCreatedBy());
			res.setResponse("Successfully Inserted");
			res.setSucessId(id);

			
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details"+e.getMessage());
			return null;
			}
		return res;
	}
}
