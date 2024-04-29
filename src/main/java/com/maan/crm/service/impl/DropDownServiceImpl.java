package com.maan.crm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.maan.crm.auth.token.passwordEnc;
import com.maan.crm.bean.AgentMaster;
import com.maan.crm.bean.BranchMaster;
import com.maan.crm.bean.CampaignTemplate;
import com.maan.crm.bean.ClaimLoginMaster;
import com.maan.crm.bean.CrmListITemValue;
import com.maan.crm.bean.InsuranceCompanyMaster;
import com.maan.crm.bean.NotifTemplateMaster;
import com.maan.crm.bean.OccupationMaster;
import com.maan.crm.bean.UnderWritterRelation;
import com.maan.crm.bean.UsertypeMaster;
import com.maan.crm.notification.mail.dto.TemplatesDropDownReq;
import com.maan.crm.repository.AgentMasterRepository;
import com.maan.crm.repository.BranchMasterRepository;
import com.maan.crm.repository.CampaignTemplateRepository;
import com.maan.crm.repository.ClaimLoginMasterRepository;
import com.maan.crm.repository.CrmListItemValueRepository;
import com.maan.crm.repository.InsuranceCompanyMasterRepository;
import com.maan.crm.repository.NotifTemplateMasterRepository;
import com.maan.crm.repository.UnderwritterRelationRepository;
import com.maan.crm.repository.UserTypeMasterRepository;
import com.maan.crm.req.BranchMasterDropdownReq;
import com.maan.crm.req.BroughtDropDownReq;
import com.maan.crm.req.ClaimLoginUserDetailsReq;
import com.maan.crm.req.UnderwriterReq;
import com.maan.crm.res.AssigntoGroupRes;
import com.maan.crm.res.AssigntoUserRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.DropDownResA;
import com.maan.crm.service.DropDownService;

@Service
@Transactional
public class DropDownServiceImpl implements DropDownService {

	@Autowired
	private AgentMasterRepository agentRepo;
	
	@Autowired
	private CrmListItemValueRepository listRepo;

	@Autowired
	private UserTypeMasterRepository userType;

	@Autowired
	private NotifTemplateMasterRepository notifRepo;

	@Autowired
	private CampaignTemplateRepository campRepo;
	
	@Autowired
	private ClaimLoginMasterRepository claimrepo;
	
	@Autowired
	private UnderwritterRelationRepository uwRepo ;

	@Autowired
	private InsuranceCompanyMasterRepository insrepo;
	
	@Autowired
	private BranchMasterRepository branchrepo;
	
	@Autowired
	private ClaimLoginMasterRepository loginRepo;
	
	Gson json = new Gson();
	
	@PersistenceContext
	private EntityManager em;

	private Logger log = LogManager.getLogger(DropDownServiceImpl.class);

	// Client Type Drop Down

	@Override
	public List<DropDownRes> getClientTypes() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("CLIENT_TYPE", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Name Title Type

	@Override
	public List<DropDownRes> getTitleTypes() {

		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("NAME_TITLE", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Gender
	@Override
	public List<DropDownRes> getgender() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("GENDER", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Marital Status
	@Override
	public List<DropDownRes> getmaritalStatus() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("MARITAL_STATUS", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Is Group Client
	@Override
	public List<DropDownRes> getgroupclient() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("IS_GROUP_CLIENT", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Annual Income

	@Override
	public List<DropDownRes> getannualincome() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("ANNUAL_INCOME", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Business Type

	@Override
	public List<DropDownRes> getbusinesstype() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("BUSINESS_TYPE", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Follow Up Date
	@Override
	public List<DropDownRes> getfollowupdate() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("SET_FOLLOW-UP_DATE",
					"Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Follow up next Year

	@Override
	public List<DropDownRes> getfollowupnextyear() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("FOLLOW-UP_NEXT_YEAR",
					"Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Followup Concluded

	@Override
	public List<DropDownRes> getfollowupconcluded() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("FOLLOW-UP_CONCLUDED",
					"Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Individual Address
	@Override
	public List<DropDownRes> getIndividualAddress() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("INDUVIDUAL_ADDRESS",
					"Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Corporate Address
	@Override
	public List<DropDownRes> getCorporateAddress() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("CORPORATE_ADDRESS",
					"Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// WillProvideReference
	@Override
	public List<DropDownRes> getWillProvideReference() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo
					.findByItemTypeAndStatusOrderByItemCodeAsc("WILL_PROVIDE_REFERENCE", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Classification
	@Override
	public List<DropDownRes> getClassification() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("CLASSIFICATION", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}
	// Manufacture_Year

	@Override
	public List<DropDownRes> getManufactureYear() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("MANUFACTURE_YEAR",
					"Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// FUEL_TYPE
	@Override
	public List<DropDownRes> getFuelType() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("FUEL_TYPE", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Prospect Summary
	@Override
	public List<DropDownRes> getProspectSummary() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("PROSPECT_SUMMARY",
					"Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Summary Period
	@Override
	public List<DropDownRes> getSummaryPeriod() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("SUMMARY_PERIOD", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownRes> getProspectSummaryDayWise() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo
					.findByItemTypeAndStatusOrderByItemCodeAsc("PROSPECT_SUMMARY_DAY_WISE", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownRes> getVehicelClassification() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo
					.findByItemTypeAndStatusOrderByItemCodeAsc("VEHICAL_CLASSIFICATION", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownRes> getProspectStatus() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("PROSPECT_STATUS", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownRes> getProspectLost() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("LOST_REASON", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownRes> getPaymentType() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("PAYMENT_TYPE", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownRes> getProductDetails() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("PRODUCT_DETAILS", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownRes> getLocation() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("LOCATION", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Assign to Group
	@Override
	public List<AssigntoGroupRes> getAssignToGroup() {
		List<AssigntoGroupRes> resList = new ArrayList<AssigntoGroupRes>();
		try {
			List<UsertypeMaster> getList = userType.findByStatusOrderByUsertypeIdAsc("Y");

			for (UsertypeMaster data : getList) {
				AssigntoGroupRes res = new AssigntoGroupRes();
				res.setCode(data.getUsertypeId().toString());
				res.setCodeDesc(data.getUsertypeDescription());
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
	public List<AssigntoUserRes> getAssignToUser(String userTypeId) {
		List<AssigntoUserRes> resList = new ArrayList<AssigntoUserRes>();
		try {
			List<Map<String,Object>> list =userType.getUserDetailsList(userTypeId);
			list.forEach(p ->{
				AssigntoUserRes res = new AssigntoUserRes();
				res.setCode(p.get("AGENCY_CODE")==null?"":p.get("AGENCY_CODE").toString());
				res.setCodeDesc(p.get("LOGIN_ID")==null?"":p.get("LOGIN_ID").toString());
				resList.add(res);
			});
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	@Override
	public List<AssigntoUserRes> getProspectOwner() {
		List<AssigntoUserRes> resList = new ArrayList<AssigntoUserRes>();
		try {
		/*	List<ClaimLoginUserDetails> getList = loginrepo.findByUsertypeAndStatusOrderByInsCompanyIdAsc("LEAD", "Y");

			for (ClaimLoginUserDetails data : getList) {
				AssigntoUserRes res = new AssigntoUserRes();
				res.setCode(String.valueOf(data.getLoginUserId()));

				res.setCodeDesc(data.getLoginId());
				;
				resList.add(res);
			} */
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	@Override
	public List<DropDownRes> getCompanyTypes() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("COMPANY_TYPE", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownRes> getZones() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("ZONE", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownRes> prospectReason() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("PROPECT_REASON", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownRes> getMailTemplates(TemplatesDropDownReq req) {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<NotifTemplateMaster> getList = notifRepo
					.findByMailRequiredAndStatusAndNotificationApplicableAndInsIdOrderByMailSubjectAsc("Y", "Y",
							req.getNotifApplicable(), req.getInsId());

			for (NotifTemplateMaster data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getSno().toString());
				res.setCodeDesc(data.getMailSubject());
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
	public List<DropDownRes> getSmsTemplates(TemplatesDropDownReq req) {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<NotifTemplateMaster> getList = notifRepo
					.findBySmsRequiredAndStatusAndNotificationApplicableAndInsIdOrderBySmsSubjectAsc("Y", "Y",
							req.getNotifApplicable(), req.getInsId());

			for (NotifTemplateMaster data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getSno().toString());
				res.setCodeDesc(data.getSmsSubject());
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
	public List<DropDownRes> getRelationType() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("RELATION_TYPE", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownRes> getUserTypes() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<UsertypeMaster> getList = userType.findByStatusOrderByOrderIdAsc("Y");

			for (UsertypeMaster data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getUsertypeId().toString());
				res.setCodeDesc(data.getUsertypeDescription());
				res.setCodeStatus(data.getStatus());
				res.setInsuranceId(data.getCompanyId());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Campaign Cliet Types
	@Override
	public List<DropDownResA> getCampaignClientType() {
		List<DropDownResA> resList = new ArrayList<DropDownResA>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("CAMPAIGN_CLIENT_TYPE",
					"Y");

			for (CrmListITemValue data : getList) {
				DropDownResA res = new DropDownResA();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownResA> getCampaignTemplateType() {
		List<DropDownResA> resList = new ArrayList<DropDownResA>();
		try {
			List<CampaignTemplate> getList = campRepo.findByStatusOrderByFieldIdAsc("Y");

			for (CampaignTemplate data : getList) {
				DropDownResA res = new DropDownResA();
				res.setCode(data.getFieldId().toString());
				res.setCodeDesc(data.getFieldName());
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
	public List<DropDownResA> getticketstatus() {
		List<DropDownResA> resList = new ArrayList<DropDownResA>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("TICKET_STATUS", "Y");

			for (CrmListITemValue data : getList) {
				DropDownResA res = new DropDownResA();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownResA> getticketissuer() {
		List<DropDownResA> resList = new ArrayList<DropDownResA>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("TICKET_ISSUER", "Y");

			for (CrmListITemValue data : getList) {
				DropDownResA res = new DropDownResA();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownResA> getunderwriter(UnderwriterReq req) {

		List<DropDownResA> resList = new ArrayList<DropDownResA>();
		try {
			String sumInsured = StringUtils.isBlank(req.getSumInsured()) ? "0" : req.getSumInsured() ; 
			
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<UnderWritterRelation> query = cb.createQuery(UnderWritterRelation.class);
			List<UnderWritterRelation> list = new ArrayList<UnderWritterRelation>();
			
			// Find All
			Root<ClaimLoginMaster>    l = query.from(ClaimLoginMaster.class);
			Root<UnderWritterRelation>    uw = query.from(UnderWritterRelation.class);		
			
			// Select
			query.select(uw );
		
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(uw.get("userName")));
			
		    // Where	
			javax.persistence.criteria.Predicate n1 = cb.equal(uw.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(uw.get("classId"), req.getClassId());
			javax.persistence.criteria.Predicate n3 = cb.lessThanOrEqualTo(uw.get("suminsuredStartLimit"), sumInsured);
			javax.persistence.criteria.Predicate n4 = cb.greaterThanOrEqualTo(uw.get("suminsuredEndLimit"), sumInsured);
			javax.persistence.criteria.Predicate n5 = cb.like(l.get("branchCode"),"%" + req.getBranchCode() + "%" ) ;
			javax.persistence.criteria.Predicate n6 = cb.equal(l.get("companyId"), req.getInsCompanyId());
			javax.persistence.criteria.Predicate n7 = cb.equal(l.get("agencyCode"), uw.get("uwCode"));
			javax.persistence.criteria.Predicate n8 = cb.equal(l.get("loginId"), uw.get("loginId"));
			
			query.where(n1,n2,n3,n4,n5,n6,n7,n8).orderBy(orderList);
			
			// Get Result
			TypedQuery<UnderWritterRelation> result = em.createQuery(query);			
			list =  result.getResultList();  
			
			for (UnderWritterRelation data :   list ) {
				DropDownResA res = new DropDownResA();
				res.setCode(data.getUwCode());
				res.setCodeDesc(data.getUserName());
				resList.add(res);
			} 
		
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details" + e.getMessage());
			return null;
		}
		return resList;

	}

	@Override
	public List<DropDownResA> getinsurancemaster() {
	List<DropDownResA> resList = new ArrayList<DropDownResA>();
	ModelMapper mapper = new ModelMapper();
	try {
		List<InsuranceCompanyMaster> insurancecompanymaster = insrepo.findByStatusOrderByInsNameAsc("Y");
		for(InsuranceCompanyMaster data : insurancecompanymaster) {
			DropDownResA res = new DropDownResA();
			res.setCode(data.getInsId());
			res.setCodeDesc(data.getInsName());
			resList.add(res);
		}
	}
	catch(Exception e) {
		e.printStackTrace();
		log.info("Log Details"+e.getMessage());
		return null;
	}
	return resList;
}

	@Override
	public List<DropDownResA> getbranchmaster(BranchMasterDropdownReq req) {
		List<DropDownResA> resList = new ArrayList<DropDownResA>();
		try {
			List<BranchMaster> branchmaster = branchrepo.findByInsCompanyIdAndStatusOrderByBranchNameAsc(req.getInsCompanyId(), "Y");
			for(BranchMaster data : branchmaster) {
				DropDownResA res = new DropDownResA();
				res.setCode(data.getBranchCode());
				res.setCodeDesc(data.getBranchName());
				resList.add(res);;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info("Log Details"+e.getMessage());
			return null;
		}
		return resList;
}

	@Override
	public List<DropDownResA> getBroughtDropDown(BroughtDropDownReq req) {
		List<DropDownResA> resList = new ArrayList<DropDownResA>();
		try {
			// Limit Offset
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 100 : Integer.valueOf(req.getOffset());
			
			if( req.getBroughtBy().equalsIgnoreCase("SalesManager")  ) {
				
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<ClaimLoginMaster> query = cb.createQuery(ClaimLoginMaster.class);

				Root<ClaimLoginMaster> l = query.from(ClaimLoginMaster.class);

				Predicate p1 = cb.equal(l.get("status"), "Y");
				Predicate p2 = cb.equal(l.get("userType"), req.getBroughtBy());

				Predicate p3 = cb.like(l.get("branchCode"),"%" +  req.getBranchCode() + "%"  );

			//	Predicate p3 = cb.equal(l.get("branchCode"),"%" +  req.getBranchCode() + "%"  );

				Predicate p4 = cb.equal(l.get("companyId"),req.getInsuranceId() );

				query.select(l).where(p1, p2, p3, p4);

				TypedQuery<ClaimLoginMaster> result = em.createQuery(query);
				result.setFirstResult(limit * offset);
				result.setMaxResults(offset);
				List<ClaimLoginMaster> list = result.getResultList();
				
				for (ClaimLoginMaster loginData : list ) {
					DropDownResA res = new DropDownResA();
					res.setCode(loginData.getAgencyCode());
					res.setCodeDesc(loginData.getLoginId());
					resList.add(res);
				}
				
			}
			else if(req.getBroughtBy().equalsIgnoreCase("Agent") ) {
			Pageable paging = PageRequest.of(limit, offset, Sort.by("entryDate").descending());	
			List<AgentMaster> list = agentRepo.findByBranchCodeAndCompanyIdAndStatus(paging,req.getBranchCode(),req.getInsuranceId(),"Y");
				
				for (AgentMaster agentData : list ) {
					DropDownResA res = new DropDownResA();
					res.setCode(agentData.getAgentCode());
					res.setCodeDesc(agentData.getAgentName());
					resList.add(res);
				}
				
				/*DropDownResA res = new DropDownResA();
				res.setCode("DIRECT01");
				res.setCodeDesc("Unni Krishnan");
				
				DropDownResA res2 = new DropDownResA();
				res2.setCode("DIRECT02");
				res2.setCodeDesc("Ravi");
				
				resList.add(res);
				resList.add(res2);*/
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info("Log Details"+e.getMessage());
			return null;
		}
		return resList;
}

	@Override
	public List<DropDownRes> getMobileCodes() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("MOBILE_CODE", "Y");

			for (CrmListITemValue data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownResA> getPolicyStatusDropDown() {
		List<DropDownResA> resList = new ArrayList<DropDownResA>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("POLICY_STATUS", "Y");

			for (CrmListITemValue data : getList) {
				DropDownResA res = new DropDownResA();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownResA> getFileTypeDropDown() {
		List<DropDownResA> resList = new ArrayList<DropDownResA>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("FILE_TYPE", "Y");

			for (CrmListITemValue data : getList) {
				DropDownResA res = new DropDownResA();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownResA> getrenewableFlagDropDown() {
		List<DropDownResA> resList = new ArrayList<DropDownResA>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc("RENEWABLE_FLAG", "Y");

			for (CrmListITemValue data : getList) {
				DropDownResA res = new DropDownResA();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
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
	public List<DropDownResA> getLIVDropDown(String itemType) {
		List<DropDownResA> resList = new ArrayList<DropDownResA>();
		try {
			List<CrmListITemValue> getList = listRepo.findByItemTypeAndStatusOrderByItemCodeAsc(itemType, "Y");

			for (CrmListITemValue data : getList) {
				DropDownResA res = new DropDownResA();
				res.setCode(data.getItemCode());
				res.setCodeDesc(data.getItemValue());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}
	
}
