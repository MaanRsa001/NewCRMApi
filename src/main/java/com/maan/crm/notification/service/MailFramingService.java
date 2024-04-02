package com.maan.crm.notification.service;

import com.maan.crm.notification.mail.dto.MailFramingReq;

public interface MailFramingService {

	String frameMail(MailFramingReq mReq, String mailBody, String mailSubject);


}
