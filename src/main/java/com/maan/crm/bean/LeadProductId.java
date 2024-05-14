package com.maan.crm.bean;
import java.io.Serializable;

import lombok.Data;

@Data
public class LeadProductId implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String leadId;
    private String clientRefNo;

}

