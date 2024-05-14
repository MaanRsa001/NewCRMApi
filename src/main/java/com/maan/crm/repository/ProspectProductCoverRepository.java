package com.maan.crm.repository;

import com.maan.crm.bean.ProspectProductCover;
import com.maan.crm.bean.ProspectProductCoverId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProspectProductCoverRepository extends JpaRepository<ProspectProductCover, ProspectProductCoverId> {

	List<ProspectProductCover> findByProspectId(String prospectId);
}
