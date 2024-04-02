
package com.maan.crm.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.maan.crm.bean.ClientDetails;
import com.maan.crm.bean.ClientMemberDetails;
import com.maan.crm.bean.ProductDetails;
import com.maan.crm.bean.ProductMaster;
import com.maan.crm.repository.ProductDetailsRepository;
import com.maan.crm.repository.ProductMasterRepository;
import com.maan.crm.req.ProductDetailsSaveReq;
import com.maan.crm.req.ProductMasterGetAllReq;
import com.maan.crm.req.ProductMasterGetReq;
import com.maan.crm.req.ProductMasterListSaveReq;
import com.maan.crm.res.ClientDetailsGetRes;
import com.maan.crm.res.ClientMemberDetailsGetRes;
import com.maan.crm.res.ClientViewRes;
import com.maan.crm.res.ProductDetailsRes;
import com.maan.crm.res.ProductDetailsViewRes;
import com.maan.crm.res.ProductMasterGetAllRes;
import com.maan.crm.res.ProductMasterRes;
import com.maan.crm.res.ProductMasterViewRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.ProductMasterService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class ProductMasterServiceImpl implements ProductMasterService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ProductMasterRepository productMasterrepo;

	@Autowired
	private ProductDetailsRepository productDetailsRepo;

	private Logger log = LogManager.getLogger(ProductMasterServiceImpl.class);

	Gson json = new Gson();

	// Validation
	@Override
	public List<Error> validateProductMaster(ProductMasterListSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {

			// Product Name
			if (req.getProductName() == null || StringUtils.isBlank(req.getProductName())) {
				errors.add(new Error("01", "Product Name", "Please Enter Product Name"));
			} else if (req.getProductName().length() > 50) {
				errors.add(new Error("01", "Product Name", "Please Enter Product Name within 50 Characters"));
			}

			// Price
			if (req.getPrice() == null || StringUtils.isBlank(req.getPrice())) {
				errors.add(new Error("02", "Class Type", "Please Enter Price"));
			}
			// Quantity
			if (req.getQuantity() == null || StringUtils.isBlank(req.getQuantity())) {
				errors.add(new Error("03", "Class Type", "Please Enter Quantity"));
			}
			// Category
			if (req.getCategory() == null || StringUtils.isBlank(req.getCategory())) {
				errors.add(new Error("04", "Category", "Please Enter Category"));
			} else if (req.getProductName().length() > 50) {
				errors.add(new Error("04", "Category", "Please Enter Category within 50 Characters"));
			}

			// Description
			if (req.getDescription() == null || StringUtils.isBlank(req.getDescription())) {
				errors.add(new Error("05", "Description", "Please Enter Description"));
			} else if (req.getDescription().length() > 50) {
				errors.add(new Error("05", "Description", "Please Enter Description within 50 Characters"));
			}

			// Manufacture Year
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			String yearInString = String.valueOf(year);

			if (req.getManufactureYear() == null || StringUtils.isBlank(req.getManufactureYear())) {
				errors.add(new Error("06", "Manufacture Year", "Please Enter Manufacture Year"));
			} else if (!req.getManufactureYear().equals(yearInString)) {
				errors.add(new Error("06", "Manufacture Year", "Please Enter year before as Manufacture Year"));
			} else if (req.getManufactureYear().length() > 4) {
				errors.add(new Error("06", "Manufacture Year", "Please Enter Manufacture Year within 4 Characters"));
			}

			// Class Type Id
			if (req.getClassTypeId() == null || StringUtils.isBlank(req.getClassTypeId())) {
				errors.add(new Error("07", "Class Type Id", "Please Enter Class Type Id"));
			} else if (Integer.valueOf(req.getClassTypeId()) <= 10) {
				errors.add(new Error("07", "Class Type" + " Id", "Please Enter Class Type within 50 Characters"));
			}
			// Class Type
			if (req.getClassType() == null || StringUtils.isBlank(req.getClassType())) {
				errors.add(new Error("08", "Class Type", "Please Enter Class Type"));
			} else if (req.getProductName().length() > 50) {
				errors.add(new Error("08", "Class Type", "Please Enter Class Type within 50 Characters"));
			}
			// Insurance Company Id
			if (req.getInsCompanyId() == null || StringUtils.isBlank(req.getInsCompanyId())) {
				errors.add(new Error("09", "Class Type", "Please Enter Insurance Company"));
			}

			// Effective Date
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			Date yesterday = cal.getTime();
			Date a = req.getEffectiveDate();

			if (req.getEffectiveDate() == null || StringUtils.isBlank(req.getEffectiveDate().toString())) {
				errors.add(new Error("10", "Effective Date", "Please Enter Effective Date"));
			} else if (a.before(yesterday)) {
				errors.add(new Error("10", "EffectiveDate", "Please Enter Future Date as EffectiveDate"));
			} else if (!req.getEffectiveDate().toString().matches("([0-9]{2}/([0-9]{2})/([0-9]{4})")) {
				errors.add(new Error("03", "Effective Date",
						"Effective Date format should be dd/MM/yyyy only allowed. Example :- 07/06/2022"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return errors;
		}
		return errors;
	}

	// Save Product
	@Override
	@Transactional
	public SuccessRes saveProductMaster(ProductMasterListSaveReq req) {
		SuccessRes res = new SuccessRes();
		ModelMapper mapper = new ModelMapper();
		Date entryDate = null;
		try {

			// Save Product Master

			ProductMaster saveData = new ProductMaster();

			Integer productId = 0;
			Integer amendId = 0;
			Integer subDetailsId = 0;

			if (StringUtils.isBlank(req.getProductId().toString())) {
				// Save
				// Criteria
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<ProductMaster> query = cb.createQuery(ProductMaster.class);

				// Find
				Root<ProductMaster> c = query.from(ProductMaster.class);

				// Select
				query.select(c);
				// Order By
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(cb.desc(c.get("productId")));
				query.orderBy(orderList);

				// Get Result
				TypedQuery<ProductMaster> result = em.createQuery(query);
				result.setFirstResult(0);
				result.setMaxResults(1);
				ProductMaster data = new ProductMaster();
				data = result.getResultList().get(0);
				productId = data.getProductId() + 1;
				amendId = 0;
				res.setResponse("Saved Successfully ");

			} else {
				// Update
				productId = Integer.valueOf(req.getProductId());
				List<ProductMaster> findDatas = productMasterrepo
						.findByProductIdAndEffectiveDateOrderByAmendIdDesc(productId, req.getEffectiveDate());
				if (findDatas != null & findDatas.size() > 0) {
					amendId = findDatas.get(0).getAmendId() + 1;
				} else {
					amendId = 0;
				}
				res.setResponse("Updated Successfully ");
			}
			// Mapping

			saveData = mapper.map(req, ProductMaster.class);
			saveData.setEffectiveDate(req.getEffectiveDate());
			saveData.setStatus("Y");
			saveData.setEntryDate(new Date());
			saveData.setProductId(productId);
			saveData.setAmendId(amendId);

			productMasterrepo.save(saveData);
			log.info("Saved Details is ---> " + json.toJson(saveData));

			Long totalCount = 0L;
			for (ProductDetailsSaveReq data : req.getProductDetailsSaveList()) {
				// Save Product Details
				ProductDetails productDetailsEntity = new ProductDetails();

				if (StringUtils.isBlank(data.getSubDetailId())) {
					// Save
					totalCount = totalCount + 1;

					subDetailsId = Integer.valueOf(totalCount.toString());
					entryDate = new Date();

					res.setResponse("Saved Successfully ");

				} else {
					// Update

					subDetailsId = Integer.valueOf(data.getSubDetailId());
					res.setResponse("Updated Successfully ");

				}
				productDetailsEntity = mapper.map(data, ProductDetails.class);
				productDetailsEntity.setProductId(productId);
				productDetailsEntity.setEffectiveDate(req.getEffectiveDate());
				productDetailsEntity.setAmendId(amendId);
				productDetailsEntity.setEntryDate(new Date());
				productDetailsEntity.setSubDetailId(subDetailsId);
				productDetailsEntity.setStatus("Y");

				productDetailsRepo.save(productDetailsEntity);
				log.info("Saved Details is ---> " + json.toJson(productDetailsEntity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --> " + e.getMessage());

			return null;
		}
		return res;
	}

	// GET
	@Override
	public ProductMasterRes getProductMasterRes(ProductMasterGetReq req) {
		ProductMasterRes res = new ProductMasterRes();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ProductMaster> query = cb.createQuery(ProductMaster.class);
			List<ProductMaster> list = new ArrayList<ProductMaster>();

			// Find All
			Root<ProductMaster> c = query.from(ProductMaster.class);

			// Select
			query.select(c);

			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<ProductMaster> ocpm1 = effectiveDate.from(ProductMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("productId"), ocpm1.get("productId"));
			effectiveDate.where(a1);

			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<ProductMaster> ocpm2 = amendId.from(ProductMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("productId"), ocpm2.get("productId"));
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"), ocpm2.get("effectiveDate"));
			amendId.where(a2, a3);

			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("productName")));

			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"), amendId);
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("productId"), req.getProductId());
			query.where(n1, n2, n3, n4).orderBy(orderList);

			// Get Result
			TypedQuery<ProductMaster> result = em.createQuery(query);
			list = result.getResultList();
			ProductMaster data = list.get(0);
			res = mapper.map(data, ProductMasterRes.class);

			res.setEntryDate(sdf.parse(sdf.format(list.get(0).getEntryDate())));
			res.setEffectiveDate(sdf.parse(sdf.format(list.get(0).getEffectiveDate())));
			Integer amend = data.getAmendId();
			Date eff = data.getEffectiveDate();
			Integer pro = data.getProductId();
			List<ProductDetails> products = productDetailsRepo.findByAmendIdAndEffectiveDateAndProductId(amend, eff,
					pro);
			List<ProductDetailsRes> productList = new ArrayList<ProductDetailsRes>();

			for (ProductDetails productdata : products) {
				ProductDetailsRes prosRes = new ProductDetailsRes();
				prosRes = mapper.map(productdata, ProductDetailsRes.class);

				productList.add(prosRes);
			}
			res.setProductDetails(productList);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());

			return null;
		}
		return res;

	}

	// GET ALL
	@Override
	public List<ProductMasterGetAllRes> getallProductMaster(ProductMasterGetAllReq req) {
		List<ProductMasterGetAllRes> resList = new ArrayList<ProductMasterGetAllRes>();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Limit Offset
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 100 : Integer.valueOf(req.getOffset());

			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ProductMaster> query = cb.createQuery(ProductMaster.class);
			List<ProductMaster> list = new ArrayList<ProductMaster>();

			// Find All
			Root<ProductMaster> c = query.from(ProductMaster.class);

			// Select
			query.select(c);

			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<ProductMaster> ocpm1 = effectiveDate.from(ProductMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("productId"), ocpm1.get("productId"));
			effectiveDate.where(a1);

			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<ProductMaster> ocpm2 = amendId.from(ProductMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("productId"), ocpm2.get("productId"));
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"), ocpm2.get("effectiveDate"));
			amendId.where(a2, a3);

			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("productName")));

			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"), amendId);
			query.where(n1, n2, n3).orderBy(orderList);

			// Get Result
			TypedQuery<ProductMaster> result = em.createQuery(query);
			result.setFirstResult(limit * offset);
			result.setMaxResults(offset);
			list = result.getResultList();

			for (ProductMaster data : list) {
				ProductMasterGetAllRes res = new ProductMasterGetAllRes();
				res = mapper.map(data, ProductMasterGetAllRes.class);

				res.setEntryDate(sdf.parse(sdf.format(list.get(0).getEntryDate())));
				res.setEffectiveDate(sdf.parse(sdf.format(list.get(0).getEffectiveDate())));
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// View
	@Override
	public ProductMasterViewRes viewProductMaster(ProductMasterGetReq req) {
		ProductMasterViewRes res = new ProductMasterViewRes();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ProductMaster> query = cb.createQuery(ProductMaster.class);
			List<ProductMaster> list = new ArrayList<ProductMaster>();

			// Find All
			Root<ProductMaster> c = query.from(ProductMaster.class);

			// Select
			query.select(c);

			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<ProductMaster> ocpm1 = effectiveDate.from(ProductMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("productId"), ocpm1.get("productId"));
			effectiveDate.where(a1);

			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<ProductMaster> ocpm2 = amendId.from(ProductMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("productId"), ocpm2.get("productId"));
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"), ocpm2.get("effectiveDate"));
			amendId.where(a2, a3);

			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("productName")));

			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"), amendId);
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("productId"), req.getProductId());
			query.where(n1, n2, n3, n4).orderBy(orderList);

			// Get Result
			TypedQuery<ProductMaster> result = em.createQuery(query);
			list = result.getResultList();
			ProductMaster data = list.get(0);
			res = mapper.map(data, ProductMasterViewRes.class);

			// res.setEntryDate(sdf.parse(sdf.format(list.get(0).getEntryDate())));

			Integer amend = data.getAmendId();
			Date eff = data.getEffectiveDate();
			Integer pro = data.getProductId();

			List<ProductDetails> products = productDetailsRepo.findByAmendIdAndEffectiveDateAndProductId(amend, eff,
					pro);
			// res.setProductDetails(mapper.map(products, ProductDetailsViewRes.class));

			List<ProductDetailsViewRes> proList = new ArrayList<ProductDetailsViewRes>();

		/*	Map<String, Map<String, List<ProductDetailsViewRes>>> map = proList
				    .collect(Collectors.groupingBy(ProductDetailsViewRes::getCategoryName,
				        Collectors.groupingBy(ProductDetailsViewRes::getDetails)));*/
			
		/*	Map<String, List<ProductDetailsViewRes>> listGrouped =
				    proList.stream().collect(Collectors.groupingBy(w -> w.categoryName));

			

			res.setProductDetails(listGroup);

			proList.add(proRes);*/

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());

			return null;
		}
		return res;
	}
}
