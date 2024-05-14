package com.maan.crm.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProspectProductCoverId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prospectId;
    private String leadId;
    private String clientRefNo;
    private int sequenceNo;
    private int coverId;

    // Add getters and setters
}
