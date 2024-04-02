package com.maan.crm.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProspectsQuotationOtherDetailsId implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY KEY ATTRIBUTES 
	private int prospectId;

	private int otherDetailsId;
}
