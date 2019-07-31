package com.dvneagu.interestservice.service;

import com.dvneagu.interestservice.dao.EsResponse;
import com.dvneagu.interestservice.dao.RequestData;
import com.dvneagu.interestservice.entity.Rate;

import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2019. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 7/30/2019.
 */
public interface InterestService
{
	EsResponse<Map<String, Object>> calculateInterest( RequestData requestData );

	EsResponse<Rate> createNewRate( Rate rate );

	EsResponse deleteRate( long id );

	EsResponse<List<Rate>> findAll();
}
