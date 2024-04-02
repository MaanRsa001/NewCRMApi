package com.maan.crm.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

import com.maan.crm.bean.NotifTemplateMaster;
import com.maan.crm.repository.NotifTemplateMasterRepository;
import com.maan.crm.req.NotifTemplateMasterReq;
import com.maan.crm.res.NotifTemplateMasterRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.NotificationTemplateMasterService;

@Service
@Transactional
public class NotificationTemplateMasterServiceImpl implements NotificationTemplateMasterService {

	private Logger log = LogManager.getLogger(NotificationTemplateMasterServiceImpl.class);

	@Autowired
	private NotifTemplateMasterRepository repository;

	@Override
	public List<NotifTemplateMasterRes> getall(NotifTemplateMasterReq req) {

		List<NotifTemplateMasterRes> resList = new ArrayList<NotifTemplateMasterRes>();
		try {
			List<NotifTemplateMaster> entlist = repository.findByInsIdOrderByEntryDateDesc(req.getInsId());
			
			entlist = entlist.stream().filter(distinctByKey(o -> Arrays.asList(o.getSno()))).collect(Collectors.toList());
			
			for (NotifTemplateMaster notifTemplateMaster : entlist) {
				
				ModelMapper modelMapper = new ModelMapper();
				NotifTemplateMasterRes res = modelMapper.map(notifTemplateMaster, NotifTemplateMasterRes.class);
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
	public NotifTemplateMasterRes get(NotifTemplateMasterReq req) {

		NotifTemplateMasterRes res = new NotifTemplateMasterRes();
		try {

			Optional<NotifTemplateMaster> ent = repository.findBySnoAndInsIdAndQueryKeyOrderByEntryDateDesc(req.getSno(),req.getInsId(),req.getQueryKey());

			ModelMapper modelMapper = new ModelMapper();
			res = modelMapper.map(ent.get(), NotifTemplateMasterRes.class);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public SuccessRes insert(NotifTemplateMasterReq req) {

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
			

			List<NotifTemplateMaster> opt = repository.findBySnoAndInsIdAndQueryKeyAndEffectiveDateBetweenOrderByAmendidDesc(req.getSno(),req.getInsId(),req.getQueryKey(),effectiveDatefrom,effectiveDateto);


			if (opt.size()!=0) {

				ModelMapper modelMapper = new ModelMapper();
				NotifTemplateMaster ent = modelMapper.map(req, NotifTemplateMaster.class);
				
				ent.setEffectiveDate(req.getEffectiveDate());
				ent.setAmendid(opt.get(0).getAmendid() + 1);
				
				repository.save(ent);
				
			} else {

				Integer code = repository.countByInsIdOrderByEntryDateDesc(req.getInsId());
				code = code + 1;

				ModelMapper modelMapper = new ModelMapper();
				NotifTemplateMaster ent = modelMapper.map(req, NotifTemplateMaster.class);
				ent.setEntryDate(new Date());
				ent.setEffectiveDate(req.getEffectiveDate());
				ent.setAmendid(0);
				ent.setSno(code);
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
