package com.dvneagu.interestservice.service;

import com.dvneagu.interestservice.dao.EsResponse;
import com.dvneagu.interestservice.dao.RequestData;
import com.dvneagu.interestservice.entity.Rate;
import com.dvneagu.interestservice.repository.RatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Copyright (c) 2019. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 7/30/2019.
 */
@Service
public class InterestServiceImpl implements InterestService
{
	@Autowired
	private RatesRepository ratesRepository;

	@Override
	public Map<String, String> calculateInterest( RequestData requestData )
	{
		return null;
	}

	@Override
	public EsResponse<Rate> createNewRate( Rate rate )
	{
		return new EsResponse<>( 1, this.ratesRepository.save( rate ), "New rate created" );
	}

	@Override
	public EsResponse deleteRate( long id )
	{
		this.ratesRepository.deleteById( id );
		return new EsResponse( 1, "Rate deleted" );
	}
}
