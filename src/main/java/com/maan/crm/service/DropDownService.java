package com.maan.crm.service;

import java.util.List;

import com.maan.crm.notification.mail.dto.TemplatesDropDownReq;
import com.maan.crm.req.BranchMasterDropdownReq;
import com.maan.crm.req.BroughtDropDownReq;
import com.maan.crm.req.UnderwriterReq;
import com.maan.crm.res.AssigntoGroupRes;
import com.maan.crm.res.AssigntoUserRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.DropDownResA;

public interface DropDownService {

	List<DropDownRes> getClientTypes();

	List<DropDownRes> getTitleTypes();

	List<DropDownRes> getgender();

	List<DropDownRes> getmaritalStatus();

	List<DropDownRes> getgroupclient();

	List<DropDownRes> getannualincome();

	List<DropDownRes> getbusinesstype();

	List<DropDownRes> getfollowupdate();

	List<DropDownRes> getfollowupnextyear();

	List<DropDownRes> getfollowupconcluded();

	List<DropDownRes> getIndividualAddress();

	List<DropDownRes> getCorporateAddress();

	List<DropDownRes> getWillProvideReference();

	List<DropDownRes> getClassification();

	List<DropDownRes> getManufactureYear();

	List<DropDownRes> getFuelType();

	List<DropDownRes> getProspectSummary();

	List<DropDownRes> getSummaryPeriod();

	List<DropDownRes> getProspectSummaryDayWise();

	List<DropDownRes> getVehicelClassification();

	List<DropDownRes> getProspectStatus();

	List<DropDownRes> getProspectLost();

	List<DropDownRes> getPaymentType();

	List<DropDownRes> getProductDetails();

	List<DropDownRes> getLocation();

	List<AssigntoGroupRes> getAssignToGroup();

	List<AssigntoUserRes> getAssignToUser(String userTypeId);

	List<AssigntoUserRes> getProspectOwner();

	List<DropDownRes> getCompanyTypes();

	List<DropDownRes> getZones();

	List<DropDownRes> prospectReason();

	List<DropDownRes> getMailTemplates(TemplatesDropDownReq req);

	List<DropDownRes> getSmsTemplates(TemplatesDropDownReq req);

	List<DropDownRes> getRelationType();

	List<DropDownRes> getUserTypes();

	List<DropDownResA> getCampaignClientType();

	List<DropDownResA> getCampaignTemplateType();

	List<DropDownResA> getticketstatus();

	List<DropDownResA> getticketissuer();

	List<DropDownResA> getunderwriter(UnderwriterReq req);

	List<DropDownResA> getinsurancemaster();

	List<DropDownResA> getbranchmaster(BranchMasterDropdownReq req);

	List<DropDownResA> getBroughtDropDown(BroughtDropDownReq req);

	List<DropDownRes> getMobileCodes();

	List<DropDownResA> getPolicyStatusDropDown();

	List<DropDownResA> getFileTypeDropDown();

	List<DropDownResA> getrenewableFlagDropDown();

	List<DropDownResA> getLIVDropDown(String itemType);

}
