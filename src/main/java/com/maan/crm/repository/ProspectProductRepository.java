package com.maan.crm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.ProspectProduct;
import com.maan.crm.bean.ProspectProductId;

@Repository
public interface ProspectProductRepository extends JpaRepository<ProspectProduct, ProspectProductId> {

	ProspectProduct findByProspectId(String prospectId);

	Page<ProspectProduct> findByInsCompanyIdAndBranchCode(Pageable paging, String insId, String branchCode);

	Long countByInsCompanyIdAndBranchCode(String insId, String branchCode);

}
