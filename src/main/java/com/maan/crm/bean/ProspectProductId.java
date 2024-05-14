package com.maan.crm.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProspectProductId implements Serializable {

    private static final long serialVersionUID = 1L;
    private String prospectId;
    private String leadId;
    private String clientRefNo;
}
