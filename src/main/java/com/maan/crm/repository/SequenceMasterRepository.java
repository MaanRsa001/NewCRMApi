package com.maan.crm.repository;

import com.maan.crm.bean.SequenceMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SequenceMasterRepository extends JpaRepository<SequenceMaster, Integer> {

	SequenceMaster findBySequenceName(String string);
    
}
