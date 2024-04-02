//StateMasterId
package com.maan.crm.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimOtherDetailsId implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private Integer claimOtherDetailsId;
	private Integer claimid;

}