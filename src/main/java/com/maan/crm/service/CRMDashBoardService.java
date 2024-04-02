package com.maan.crm.service;

import com.maan.crm.controller.DashBoardCardsDataCountReq;
import com.maan.crm.res.ActiveUsersCountRes;
import com.maan.crm.res.DashBoardCardsDataCountRes;
import com.maan.crm.res.DashBoardQuoteStatusCountRes;
import com.maan.crm.res.DashBoardTotalCountRes;
import com.maan.crm.res.NewlyJoinedUsersCountRes;
import com.maan.crm.res.RevenueTotalCountRes;

public interface CRMDashBoardService {

	DashBoardCardsDataCountRes getCardsDataCount(DashBoardCardsDataCountReq req);

	DashBoardTotalCountRes getTotalDataCount(DashBoardCardsDataCountReq req);

	ActiveUsersCountRes getActiveUsersCount(DashBoardCardsDataCountReq req);

	NewlyJoinedUsersCountRes getNewlyJoinedUsersCount(DashBoardCardsDataCountReq req);

	RevenueTotalCountRes getRevenueTotalCount(DashBoardCardsDataCountReq req);

	DashBoardQuoteStatusCountRes getTotalQuoteStatusCount(DashBoardCardsDataCountReq req);

}
