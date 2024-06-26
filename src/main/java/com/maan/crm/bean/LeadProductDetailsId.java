package com.maan.crm.bean;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LeadProductDetailsId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String leadId;

    private String clientRefNo;

    private int sequenceNo;
}
