package com.maan.crm.notification.service;

import java.util.List;

import com.maan.crm.notification.mail.dto.GetMailTemplateReq;
import com.maan.crm.notification.mail.dto.MailTriggerReq;
import com.maan.crm.notification.mail.dto.NotifTemplateRes;
import com.maan.crm.notification.mail.dto.ReadMailReq;
import com.maan.crm.notification.mail.dto.ReadMailRes;
import com.maan.crm.notification.sms.dto.SmsReq;
import com.maan.crm.req.ReplayMailTriggerReq;
import com.maan.crm.req.SmsGetReq;
import com.maan.crm.res.SmsGetRes;
import com.maan.crm.res.SuccessRes;

public interface NotificationService {

	public SuccessRes SendMail(MailTriggerReq req);

	public NotifTemplateRes getGetMailTemplate(GetMailTemplateReq req);

	public List<ReadMailRes> ReadMail(ReadMailReq req);

	public SuccessRes SendSms(SmsReq req);

	public List<ReadMailRes> ReadSentMail(ReadMailReq req);

	public SuccessRes replayMail(ReplayMailTriggerReq req);

//	public SuccessRes SendAndSaveMail(MailTriggerReq req);

	public SuccessRes SaveAndSendSms(SmsReq req);

	public List<SmsGetRes> getSms(SmsGetReq req);


}
