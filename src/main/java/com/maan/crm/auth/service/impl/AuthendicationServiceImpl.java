package com.maan.crm.auth.service.impl;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.maan.crm.auth.dto.ChangePasswordReq;
import com.maan.crm.auth.dto.ClaimLoginResponse;
import com.maan.crm.auth.dto.ClaimLogoutResponse;
import com.maan.crm.auth.dto.CommonLoginResponse;
import com.maan.crm.auth.dto.LoginEncryptResponse;
import com.maan.crm.auth.dto.LoginRequest;
import com.maan.crm.auth.dto.LoginUserDetailsRes;
import com.maan.crm.auth.dto.LogoutRequest;
import com.maan.crm.auth.dto.MenuListRes;
import com.maan.crm.auth.dto.PaymentResUrlReq;
import com.maan.crm.auth.service.AuthendicationService;
import com.maan.crm.auth.token.EncryDecryService;
import com.maan.crm.auth.token.JwtTokenUtil;
import com.maan.crm.auth.token.passwordEnc;
import com.maan.crm.bean.BranchMaster;
import com.maan.crm.bean.ClaimLoginMaster;
import com.maan.crm.bean.ClaimLoginMasterId;
import com.maan.crm.bean.MenuMaster;
import com.maan.crm.bean.SessionDetails;
import com.maan.crm.bean.UnderWritterRelation;
import com.maan.crm.bean.UnderWritterRelationId;
import com.maan.crm.bean.UsertypeMaster;
import com.maan.crm.repository.BranchMasterRepository;
import com.maan.crm.repository.ClaimLoginMasterRepository;
import com.maan.crm.repository.MenuMasterRepository;
import com.maan.crm.repository.SessionDetailsRepository;
import com.maan.crm.repository.UnderwritterRelationRepository;
import com.maan.crm.repository.UserTypeMasterRepository;
import com.maan.crm.req.InsertLoginMasterReq;

import com.maan.crm.req.LoginGetReq;
import com.maan.crm.req.UnderWritterDetailsReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownResA;
import com.maan.crm.res.LoginGetRes;

import com.maan.crm.req.LoginDetailsGetReq;
import com.maan.crm.req.UnderWritterDetailsReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownResA;
import com.maan.crm.res.LoginDetailsGetListRes;
import com.maan.crm.res.LoginDetailsGetRes;
import com.maan.crm.res.ProductMasterGetAllRes;

import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.UnderWritterRes;


@Lazy
@Service
public class AuthendicationServiceImpl implements AuthendicationService, UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private ClaimLoginMasterRepository loginRepo;
	@Autowired
	private SessionDetailsRepository sessionRep;
	@Autowired
	private EncryDecryService endecryService;
	@Autowired
	private BranchMasterRepository branchRepo;
	@Autowired
	private ClaimLoginMasterRepository loginuserrepo;
	@Autowired
	private MenuMasterRepository menuRepo ;
	
	@Autowired
	private UserTypeMasterRepository userRepo ;
	
	@Autowired
	private UnderwritterRelationRepository uwRepo ;
	
	@PersistenceContext
	private EntityManager em;
	
	private Logger log = LogManager.getLogger(AuthendicationServiceImpl.class);
	
	@Override
	public CommonLoginResponse checkUserLogin(LoginRequest mslogin, HttpServletRequest http) {
		CommonLoginResponse res = new CommonLoginResponse();
		ClaimLoginResponse response = new ClaimLoginResponse();
		try {
			passwordEnc passEnc = new passwordEnc();
			String epass = passEnc.crypt(mslogin.getPassword().trim());
			log.info("Encrpted password "+epass);
			ClaimLoginMaster login =loginRepo.findByLoginIdAndPassword(mslogin.getUserId(),epass);
			if (login != null ) {
				http.getSession().removeAttribute(mslogin.getUserId());
				String token = jwtTokenUtil.doGenerateToken(mslogin.getUserId());
				log.info("-----token------" + token);
				SessionDetails session = new SessionDetails();
				session.setLoginId(mslogin.getUserId());
				session.setTokenId(token);
				session.setCompanyId(login.getCompanyId());
				session.setStatus("ACTIVE");
				String temptoken = bCryptPasswordEncoder.encode("CommercialClaim");
				session.setTempTokenid(temptoken);
				session.setEntryDate(new Date());
				session.setUsertype(mslogin.getUserType());
				session =sessionRep.save(session);
				response= setTokenResponse(session,login,mslogin);
				res.setLoginResponse(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	private ClaimLoginResponse setTokenResponse(SessionDetails session, ClaimLoginMaster login, LoginRequest mslogin) {
		ClaimLoginResponse r = new ClaimLoginResponse();
		ModelMapper mapper = new ModelMapper(); 
		try {
			r.setToken(session.getTempTokenid());
			r.setLoginId(login.getLoginId());
			r.setUserName(login.getUsername());
			r.setEmail(StringUtils.isBlank(login.getUserMail())?"":login.getUserMail());
			r.setMobileNo(StringUtils.isBlank(login.getMobileNumber())?"":login.getMobileNumber());
			r.setUserType(mslogin.getUserType());
			r.setAgencyCode(login.getAgencyCode());
			
			List<String> branches = new ArrayList<String>();
			branches =  new ArrayList<>(Arrays.asList(login.getBranchCode().split(",")));
			List<BranchMaster> brList = branchRepo.findByBranchCodeInOrderByBranchCodeAsc(branches);
		
			List<LoginUserDetailsRes> loginUserDetails = new ArrayList<LoginUserDetailsRes>();
			for(BranchMaster data :  brList) {
				LoginUserDetailsRes userRes = new LoginUserDetailsRes();
				userRes.setInsId(data.getInsCompanyId());
				userRes.setBranchCode(data.getBranchCode());
				userRes.setRegionCode(data.getRegionCode());
				userRes.setBranchName(data.getBranchName());;
				loginUserDetails.add(userRes);
			}
			r.setLoginUserDetails(loginUserDetails);
			
			// Menu List 
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<MenuMaster> query = cb.createQuery(MenuMaster.class);
			Root<MenuMaster> m = query.from(MenuMaster.class);		
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(m.get("menuId")));
						
			javax.persistence.criteria.Predicate n1 = cb.like(m.get("userTypes"),"%" + mslogin.getUserType() + "%" );
			query.select(m).where(n1).orderBy(orderList);
			TypedQuery<MenuMaster> result = em.createQuery(query);
			
			List<MenuMaster> getMenus = result.getResultList() ;//menuRepo.findByUserTypesContainingIgnoreCase( mslogin.getUserType() );
			List<MenuListRes> menuList = new ArrayList<MenuListRes>();
			
			// Parent 
			List<MenuMaster> filterHeader = getMenus.stream().filter( o ->  o.getParentId() == null  ) .collect(Collectors.toList());
				
			for(MenuMaster data  :  filterHeader ) {
				MenuListRes menuRes = new MenuListRes();
				menuRes  = mapper.map(data ,MenuListRes.class  );
				
				List<MenuListRes> childList = new ArrayList<MenuListRes>();
				List<MenuMaster> filterChild = getMenus.stream().filter( o ->  o.getParentId() != null && o.getParentId().equals(data.getMenuId())   ) .collect(Collectors.toList()); 
				
				Type listType = new TypeToken<List<MenuListRes>>(){}.getType();
				childList = mapper.map(filterChild ,listType);
				menuRes.setChildList(childList);
				menuList.add(menuRes);
			}
			r.setMenuList(menuList);
			
		}catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return r;
		
	}
	
	@SuppressWarnings("static-access")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ClaimLoginMaster userList = new ClaimLoginMaster();
		try {
			log.info("loadUserByUsername==>" + username);
			
			String[] split = username.split(":");
			
			ClaimLoginMasterId id = new ClaimLoginMasterId();
			id.setLoginId(split[0]);
			id.setCompanyId(split[1]);
			
			 ClaimLoginMaster userListopt = loginRepo.findByLoginId(split[0]);
			 if(userListopt!=null) {
				 userList = userListopt;
			 }
			if (userList!=null) {
				//user = userList.get(0);
				String pass = bCryptPasswordEncoder.encode(endecryService.decrypt("zQYgCo7GMZeX1tBQyzAi8Q=="));
				userList.setPassword(pass);
				Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
				grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
				log.info("loadUserByTokenEncrypt==>" + userList.getPassword());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new org.springframework.security.core.userdetails.User(userList.getLoginId(), userList.getPassword(),getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
	
	@Override
	public CommonCrmRes LoginChangePassword(ChangePasswordReq req) {

		CommonCrmRes res = new CommonCrmRes();
		try {
			passwordEnc passEnc = new passwordEnc();
			String epass = passEnc.crypt(req.getOldpassword().trim());
			String newpass = passEnc.crypt(req.getNewPassword().trim());
			ClaimLoginMaster master = new ClaimLoginMaster();
			log.info("EncryptPassword-->" + epass);

			ClaimLoginMasterId id = new ClaimLoginMasterId();
			id.setLoginId(req.getUserId());
			id.setCompanyId(req.getCompanyId());
			
			Optional<ClaimLoginMaster> model = loginRepo.findById(id);

			if (model.isPresent()) {
				master = model.get();
				
				String pass1 = master.getPassword();
				String pass2 = master.getLpass1();
				String pass3 = master.getLpass2();
				String pass4 = master.getLpass3();
				String pass5 = master.getLpass4();
				
				master.setLpass1(pass1);
				master.setLpass2(pass2);
				master.setLpass3(pass3);
				master.setLpass4(pass4);
				master.setLpass5(pass5);
				master.setPassword(newpass);
				master.setPwdCount(master.getPwdCount()+1);
				
				Instant now = Instant.now();
				Instant after = now.plus(Duration.ofDays(45));
				Date dateAfter = Date.from(after);
				master.setPassdate(dateAfter);
				ClaimLoginMaster table = loginRepo.save(master);

				if (table != null) {
					res.setMessage("SUCCESS");
					res.setIsError(false);
				} else {
					res.setMessage("FAILED");
					res.setIsError(true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error-->" + e.getMessage());
		}
		return res;

	}
	@Override
	public CommonCrmRes logout(LogoutRequest mslogin) {
		CommonCrmRes res = new CommonCrmRes();
		ClaimLogoutResponse r = new ClaimLogoutResponse();
		try {
		
			ClaimLoginMaster login = loginRepo.findByLoginId(mslogin.getUserId());
			if (login!=null) {

				SessionDetails session = sessionRep.findByTempTokenid(mslogin.getToken());
				session.setLogoutDate(new Date());
				session.setStatus("DE-ACTIVE");
				session = sessionRep.save(session);
				r.setStatus("Log Out Sucessfully");
			}else {
				r.setStatus("Log Out Failed");
			}
		} catch (Exception e) {
			r.setStatus("Log Out Failed");
			e.printStackTrace();
		}
		res.setCommonResponse(r);
		return res;
	}
	@Override
	public SuccessRes InsertLogin(InsertLoginMasterReq req) {
		SuccessRes res = new SuccessRes();
		String agencyCode = "";
		try {
			String branches = "" ;
			for (String branch : req.getBranchCode()) {
				branches = StringUtils.isBlank(branches)?branch : branches + "," + branch ;
			}
			
			
			ClaimLoginMaster login = new ClaimLoginMaster(); 
			
			if(StringUtils.isNotBlank(req.getAgencyCode())) {
				ClaimLoginMaster find = loginuserrepo.findByAgencyCode(req.getAgencyCode());
				login = find ;
				// Delete Old Data
				loginuserrepo.delete(find);
				login.setUserMail(req.getUserMail());
				login.setUsername(req.getUsername());
				login.setMobileNumber(req.getMobileNumber());
				login.setLastModifiedDate(new Date());
				login.setLastModifiedBy(req.getCreatedBy());
				login.setCompanyId(req.getCompanyId());
				login.setUserType(req.getUserType());
				login.setLoginId(req.getLoginId());
				login.setStatus(req.getStatus());
				login.setRemarks(req.getRemarks());			
				login.setBranchCode(branches);
				
				loginuserrepo.save(login);
				res.setResponse("Login Updated Successully");
				res.setSucessId(req.getLoginId());
			}else {

				passwordEnc passEnc = new passwordEnc();
				String newpass = passEnc.crypt(req.getPassword().trim());
				Instant now = Instant.now();
				Instant after = now.plus(Duration.ofDays(45));
				Date dateAfter = Date.from(after);
				
				
				Long count = loginRepo.count();
				Long countId = 20001 + count ; 
				
				UsertypeMaster  user = userRepo.findByUsertypeDescription(req.getUserType());
				agencyCode = user.getUsertypeCode() + countId ;
				
				login.setCompanyId(req.getCompanyId());
				login.setAgencyCode(agencyCode);
				login.setUserType(req.getUserType());
				login.setLoginId(req.getLoginId());
				login.setUserMail(req.getUserMail());
				login.setUsername(req.getUsername());
				login.setMobileNumber(req.getMobileNumber());
				login.setCreatedBy(req.getCreatedBy());
				login.setStatus(req.getStatus());
				login.setRemarks(req.getRemarks());			
				login.setBranchCode(branches);
				
				login.setEntryDate(new Date());
				login.setLastModifiedDate(new Date());
				login.setLastModifiedBy(req.getCreatedBy());
				
				login.setPassword(newpass);
				login.setPwdCount("0");				
				login.setPassdate(dateAfter);
				
				loginuserrepo.save(login);
				res.setResponse("Login Created Successully");
				res.setSucessId(req.getLoginId());
			//	insertLoginUserDetails(req.getLoginUserDetails());
			}
			if(  req.getUserType().equalsIgnoreCase("UnderWritter") ) {
				insertUnderWritterDetails (req.getUnderWritterList() , agencyCode ,req.getLoginId() , req.getUsername()); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private void insertUnderWritterDetails(List<UnderWritterDetailsReq> underWritterReq , String agencyCode, String loginId, String userName) {
		
		try {
			List<UnderWritterRelation> oldUw = uwRepo.findByUwCode(agencyCode);
			if(oldUw.size()>0 ) {
				uwRepo.deleteAll(oldUw);
			}
			for (UnderWritterDetailsReq req : underWritterReq) {

				UnderWritterRelation uw = new UnderWritterRelation();
				uw.setClassId(Long.valueOf(req.getClassId()));
				uw.setPolicyTypeId(Long.valueOf(req.getPolicyTypeId()));
				uw.setUwCode(agencyCode);
				uw.setPolicyType(req.getPolicyType());
				uw.setClassDesc(req.getClassDesc());
				uw.setStatus(req.getStatus());
				uw.setSuminsuredStartLimit(Double.valueOf(req.getSumInsuredStart()));
				uw.setSuminsuredEndLimit(Double.valueOf(req.getSumInsuredEnd()));
				uw.setLoginId(loginId);
				uw.setUserName(userName);
				uwRepo.save(uw)	;				
				

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/*	private void insertLoginUserDetails(List<ClaimLoginUserDetailsReq> loginUserDetails) {

		try {

			for (ClaimLoginUserDetailsReq req : loginUserDetails) {

				ClaimLoginUserDetailsId id = new ClaimLoginUserDetailsId();
				id.setBranchCode(req.getBranchCode());
				id.setInsCompanyId(req.getInsCompanyId());
				id.setLoginId(req.getLoginId());
				id.setLoginUserId(req.getLoginUserId());
				id.setRegionCode(req.getRegionCode());
				id.setUsertypeId(req.getUsertypeId());
				
				Optional<ClaimLoginUserDetails> opt = loginUserRepo.findById(id);
				if(opt.isPresent()) {
					ClaimLoginUserDetails loginuserdetails = opt.get();
					
					loginuserdetails.setRemarks(req.getRemarks());
					loginuserdetails.setStatus(req.getStatus());
					loginuserdetails.setUsertype(req.getUsertype());
					loginuserdetails.setSuminsuredEnd(req.getSuminsuredEnd());
					loginuserdetails.setSuminsuredStart(req.getSuminsuredStart());
					loginUserRepo.save(loginuserdetails);
					
				}else {

					Integer LoginUserId = 1000;
					List<ClaimLoginUserDetails> LoginUser = new ArrayList<ClaimLoginUserDetails>();
					
					LoginUser = loginUserRepo.findByLoginId(req.getLoginId());
					if(LoginUser.size()==0) {

						LoginUser = loginUserRepo.findAllByOrderByLoginUserIdDesc();
						if(LoginUser.size()!=0) {
							LoginUserId = LoginUser.get(0).getLoginUserId()+1;
						}
						
					}else{
						LoginUserId = LoginUser.get(0).getLoginUserId();
					}
					
					
					ClaimLoginUserDetails loginuserdetails = new ClaimLoginUserDetails();
					loginuserdetails.setLoginUserId(LoginUserId);					
					loginuserdetails.setBranchCode(req.getBranchCode());
					loginuserdetails.setInsCompanyId(req.getInsCompanyId());
					loginuserdetails.setLoginId(req.getLoginId());
					loginuserdetails.setRegionCode(req.getRegionCode());
					loginuserdetails.setUsertypeId(req.getUsertypeId());
					loginuserdetails.setProductId(req.getProductId());
					loginuserdetails.setRemarks(req.getRemarks());
					loginuserdetails.setStatus(req.getStatus());
					loginuserdetails.setUsertype(req.getUsertype());
					loginuserdetails.setSuminsuredEnd(req.getSuminsuredEnd());
					loginuserdetails.setSuminsuredStart(req.getSuminsuredStart());
					loginuserdetails.setEntryDate(new Date());
					loginUserRepo.save(loginuserdetails);
					
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	} */
	@Override
	public LoginEncryptResponse getLoginEncryptResponse(PaymentResUrlReq req, HttpServletRequest http) {
		LoginEncryptResponse resp=new LoginEncryptResponse();
		try {
			log.info("Req==>"+req.getEncryptValue());
			String decrypt = EncryDecryService.decrypt(URLDecoder.decode(req.getEncryptValue(),"UTF-8"));
			if(StringUtils.isNotBlank(decrypt) && decrypt.indexOf("~")!=-1){
				log.info("Encrypt==>"+decrypt);
				String[] split = decrypt.split("~");
				if(split.length>0){
					String[] username = split[0].split("=");
					String[] password =split[1].split("=");
					String[] loginType =split[2].split("=");
					String[] branchcode =split[3].split("=");
				
					if( split.length>5 ) {
						String[] clientRefNo = split[4].split("=") ;
						String[] leadId =  split[5].split("=") ;
						String[] clientTypeId =  split[6].split("=") ;
						
						resp.setLeadId(leadId[1]);
						resp.setCleintRefNo(clientRefNo[1]);
						resp.setClientTypeId(clientTypeId[1]);
					}
					
					
					resp.setBranchcode(branchcode[1]);
					resp.setUserName(username[1]);
					resp.setLoginType(loginType[1]);
					resp.setPassword(password[1]);
					
				} 
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resp;
	}
	
	// Change Passowrd
	@Override
	public CommonCrmRes LoginChangePasswordA(ChangePasswordReq req) {
		CommonCrmRes res = new CommonCrmRes();
		try {
		passwordEnc passEnc = new passwordEnc();
		String epass = passEnc.crypt(req.getOldpassword().trim());
		String newpass = passEnc.crypt(req.getNewPassword().trim());
		ClaimLoginMaster master = new ClaimLoginMaster();  
		log.info("EncryptPassword-->" + epass);
		Optional<ClaimLoginMaster> model = loginRepo.findByLoginIdAndCompanyId(req.getUserId(),req.getCompanyId());;
		if(model.isPresent() ) {
			master = model.get();
			
			String pass1 = master.getPassword();
			String pass2 = master.getLpass1();
			String pass3 = master.getLpass2();
			String pass4 = master.getLpass4();
			String pass5 = master.getLpass4();
			
			master.setLpass1(pass1);
			master.setLpass2(pass2);
			master.setLpass3(pass3);
			master.setLpass4(pass4);
			master.setLpass5(pass5);
			master.setPassword(newpass);
			master.setPwdCount(master.getPwdCount()+1);
			
			Instant now = Instant.now();
			Instant after = now.plus(Duration.ofDays(45));
			Date dateAfter = Date.from(after);
			master.setPassdate(dateAfter);
			ClaimLoginMaster table = loginRepo.save(master);
			
			if(table!=null) {
				res.setMessage("SUCCESS");
				res.setIsError(false);
			}
			else {
				res.setMessage("FAILED");
				res.setIsError(true);;
			}
		}
		
		
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error-->" + e.getMessage());
		}
		return res;

	}

	@Override
	public LoginGetRes getloginid(LoginGetReq req) {
		LoginGetRes res = new LoginGetRes();
		ModelMapper mapper = new ModelMapper();
		try {
			
			ClaimLoginMaster    data = loginRepo.findByLoginIdAndAgencyCode(req.getLoginId(),req.getAgencyCode());
			
			res = mapper.map(data,LoginGetRes.class );
			res.setInsCompanyId(data.getCompanyId());
			String agencycode = data.getAgencyCode();
			List<UnderWritterRelation> underwriter = uwRepo.findByUwCode(agencycode);
			List<UnderWritterRes> resList = new ArrayList<UnderWritterRes>();
			for(UnderWritterRelation uwdata : underwriter) {
				UnderWritterRes uwres = new UnderWritterRes();
				uwres = mapper.map(uwdata, UnderWritterRes.class);
				resList.add(uwres);
			}
			res.setUnderWritterList(resList);
			} 
	 catch (Exception e) {
		e.printStackTrace();
		log.info("Log Details" + e.getMessage());
		return null;
	}
	return res;
	
	}
	
	

	//Get Login Details
	@Override
	public List<LoginDetailsGetRes> getLogintDetails(LoginDetailsGetReq req) {
		List<LoginDetailsGetRes> resList = new ArrayList<LoginDetailsGetRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ClaimLoginMaster> query = cb.createQuery(ClaimLoginMaster.class);
			List<ClaimLoginMaster> list = new ArrayList<ClaimLoginMaster>();
			
			// Find All
			Root<ClaimLoginMaster>    l = query.from(ClaimLoginMaster.class);
			// Select
			query.select(l);
		
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(l.get("loginId")));
			
		    // Where	
			javax.persistence.criteria.Predicate n1 = cb.like(l.get("branchCode"),"%" + req.getBranchCode() + "%" ) ;
			javax.persistence.criteria.Predicate n2 = cb.equal(l.get("companyId"), req.getCompanyId());
			
			query.where(n1,n2).orderBy(orderList);
			
			// Get Result
			TypedQuery<ClaimLoginMaster> result = em.createQuery(query);	
			list =  result.getResultList();  

			// UserType 
			List<UsertypeMaster> userList=userRepo.findByStatusOrderByOrderIdAsc("Y");
			// Group By UserType
			Map<String, List<ClaimLoginMaster>> groupByUserType = list.stream().collect(Collectors.groupingBy(ClaimLoginMaster::getUserType) );	
			
			for (UsertypeMaster userData : userList ) {

				List<ClaimLoginMaster> userType = groupByUserType.get(userData.getUsertypeDescription() );
				// Model Mapper For List
				List<LoginDetailsGetListRes> loginResList = new ArrayList<LoginDetailsGetListRes>();
				if (userType != null ) {
					loginResList = userType.stream().map(loss -> mapper.map(loss, LoginDetailsGetListRes.class)).collect(Collectors.toList());
				}
				// Login Details Res
				LoginDetailsGetRes userids = new LoginDetailsGetRes();
				userids.setUserType(userData.getUsertypeDescription() ) ;
				userids.setUserTypeDetails(loginResList);
				resList.add(userids);
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details" + e.getMessage());
			return null;
		}
		return resList;
	}

}


