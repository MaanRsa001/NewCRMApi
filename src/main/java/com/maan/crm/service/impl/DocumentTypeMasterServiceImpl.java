package com.maan.crm.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maan.crm.bean.DocumentTypeMaster;
import com.maan.crm.repository.DocumentTypeMasterRepository;
import com.maan.crm.req.DocumentTypeMasterReq;
import com.maan.crm.res.DocumentTypeMasterRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.DocumentTypeMasterService;

@Service
@Transactional
public class DocumentTypeMasterServiceImpl implements DocumentTypeMasterService {

	private Logger log = LogManager.getLogger(DocumentTypeMasterServiceImpl.class);

	@Autowired
	private DocumentTypeMasterRepository repository;

	@Override
	public List<DocumentTypeMasterRes> getall(DocumentTypeMasterReq req) {

		List<DocumentTypeMasterRes> resList = new ArrayList<DocumentTypeMasterRes>();
		try {
			List<DocumentTypeMaster> entlist = repository.findByInsCompanyIdOrderByEntryDate(req.getInsCompanyId());
			
			entlist = entlist.stream().filter(distinctByKey(o -> Arrays.asList(o.getDocumentid()))).collect(Collectors.toList());
			
			for (DocumentTypeMaster documentTypeMaster : entlist) {
				
				ModelMapper modelMapper = new ModelMapper();
				DocumentTypeMasterRes res = modelMapper.map(documentTypeMaster, DocumentTypeMasterRes.class);
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;

	}

	private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
 		 Map<Object, Boolean> seen = new ConcurrentHashMap<>();
 		 return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
	@Override
	public DocumentTypeMasterRes get(DocumentTypeMasterReq req) {

		DocumentTypeMasterRes res = new DocumentTypeMasterRes();
		try {

			List<DocumentTypeMaster> ent = repository.findByDocumentidAndInsCompanyIdOrderByEntryDate(req.getDocumentid(),req.getInsCompanyId());

			ModelMapper modelMapper = new ModelMapper();
			res = modelMapper.map(ent.get(0), DocumentTypeMasterRes.class);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public SuccessRes insert(DocumentTypeMasterReq req) {

		SuccessRes res = new SuccessRes();
		try {
			
			Calendar calendar = Calendar.getInstance(); 
		 	calendar.setTime(req.getEffectiveDate());
		    calendar.set(Calendar.HOUR_OF_DAY, 0);
		    calendar.set(Calendar.MINUTE, 0);
		    calendar.set(Calendar.SECOND, 0);
		    calendar.set(Calendar.MILLISECOND, 0);
		    
		    Calendar c = Calendar.getInstance();
		    c.setTime(req.getEffectiveDate());
		    c.set(Calendar.HOUR_OF_DAY, 23);
		    c.set(Calendar.MINUTE, 59);
		    c.set(Calendar.SECOND, 59);
		    c.set(Calendar.MILLISECOND, 59);


			Date effectiveDatefrom = calendar.getTime();
			Date effectiveDateto = c.getTime();
			
			List<DocumentTypeMaster> opt = repository.findByDocumentidAndInsCompanyIdAndEffectiveDateBetweenOrderByAmendIdDesc(req.getDocumentid(),req.getInsCompanyId(),effectiveDatefrom,effectiveDateto);


			if (opt.size()!=0) {

				ModelMapper modelMapper = new ModelMapper();
				DocumentTypeMaster ent = modelMapper.map(req, DocumentTypeMaster.class);
				
				ent.setEntryDate(new Date());
				ent.setEffectiveDate(req.getEffectiveDate());
				ent.setAmendId(opt.get(0).getAmendId()+1);
				
				repository.save(ent);
				
			} else {

				Integer code = repository.countByInsCompanyIdOrderByEntryDate(req.getInsCompanyId());
				code = code + 1;

				ModelMapper modelMapper = new ModelMapper();
				DocumentTypeMaster ent = modelMapper.map(req, DocumentTypeMaster.class);
				ent.setEntryDate(new Date());
				ent.setDocumentid(code);
				ent.setEffectiveDate(req.getEffectiveDate());
				ent.setAmendId(0);

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
