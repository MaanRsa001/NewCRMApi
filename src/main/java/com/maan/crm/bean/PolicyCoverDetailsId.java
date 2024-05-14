package com.maan.crm.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyCoverDetailsId implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String policyId;
    private String clientRefNo;
    private int sequenceNo;
    private int coverId;
}
