package com.maan.crm.service.impl;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.maan.crm.service.CampaignTempalteFormService;

@Service
@Transactional
public class CampaignTempalteFormServiceImpl implements CampaignTempalteFormService {

	private Logger log = LogManager.getLogger(CampaignTempalteFormServiceImpl.class);

	Gson json = new Gson();

}
