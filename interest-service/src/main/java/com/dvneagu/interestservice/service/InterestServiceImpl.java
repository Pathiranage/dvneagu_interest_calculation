package com.dvneagu.interestservice.service;

import com.dvneagu.interestservice.dao.EsResponse;
import com.dvneagu.interestservice.dao.RequestData;
import com.dvneagu.interestservice.entity.Rate;
import com.dvneagu.interestservice.repository.RatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
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
	public EsResponse<Map<String, Object>> calculateInterest( RequestData requestData )
	{
		try
		{
			double interest = 0.0;
			double penalties = 0.0;

			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );

			LocalDate actualDate = LocalDate.parse( requestData.getActualDate(), dateTimeFormatter );
			LocalDate receivedDate = LocalDate.parse( requestData.getReceivedDate(), dateTimeFormatter );

			double amount = requestData.getAmount();
			Rate actualTop = this.ratesRepository.findTopByFromDateLessThanEqualOrderByFromDateDesc( actualDate );
			Rate receivedTop = this.ratesRepository.findTopByFromDateLessThanEqualOrderByFromDateDesc( receivedDate );
			List<Rate> betweenData = this.ratesRepository.findAllByFromDateGreaterThanEqualAndFromDateLessThanEqual( actualDate, receivedDate );
			if ( actualDate.getMonth().getValue() == receivedDate.getMonth().getValue() )
			{
				long noOfDays = ChronoUnit.DAYS.between( actualDate, receivedDate );
				interest += ( noOfDays * ( actualTop.getInterestRate() / 100 ) * amount );
				penalties += ( noOfDays * ( actualTop.getPenalties() / 100 ) * amount );
			}
			for ( int i = 0; i < betweenData.size(); i++ )
			{
				if ( i == 0 )
				{
					long noOfDays = ChronoUnit.DAYS.between( actualDate, betweenData.get( i ).getFromDate() );
					interest += ( noOfDays * ( actualTop.getInterestRate() / 100 ) * amount );
					penalties += ( noOfDays * ( actualTop.getPenalties() / 100 ) * amount );
				}
				if ( betweenData.size() == 1 )
				{
					long noOfDays = ChronoUnit.DAYS.between( betweenData.get( i ).getFromDate(), receivedDate ) + 1;
					interest += ( noOfDays * ( betweenData.get( i ).getInterestRate() / 100 ) * amount );
					penalties += ( noOfDays * ( betweenData.get( i ).getPenalties() / 100 ) * amount );
				}
				if ( betweenData.size() > 1 && i == betweenData.size() - 1 && betweenData.get( betweenData.size() - 1 ).getFromDate().isBefore( receivedDate ) )
				{
					long noOfDays = ChronoUnit.DAYS.between( betweenData.get( i ).getFromDate(), receivedDate );
					interest += ( noOfDays * ( receivedTop.getInterestRate() / 100 ) * amount );
					penalties += ( noOfDays * ( receivedTop.getPenalties() / 100 ) * amount );
				}
				if ( i > 0 && betweenData.size() > 1 )
				{
					long noOfDays = ChronoUnit.DAYS.between( betweenData.get( i - 1 ).getFromDate(), betweenData.get( i ).getFromDate() ) + 1;
					interest += ( noOfDays * ( betweenData.get( i - 1 ).getInterestRate() / 100 ) * amount );
					penalties += ( noOfDays * ( betweenData.get( i - 1 ).getPenalties() / 100 ) * amount );
				}
			}
			System.out.println( interest );
			System.out.println( penalties );
			Map<String, Object> hashMap = new HashMap<>();
			hashMap.put( "interest", interest );
			hashMap.put( "penalties", penalties );
			return new EsResponse<>( 1, hashMap, "Calculation Success" );
		}
		catch ( Exception e )
		{
			return new EsResponse<>( -1, "Calculation error" );
		}
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

	@Override
	public EsResponse<List<Rate>> findAll()
	{
		return new EsResponse<>( 1, this.ratesRepository.findAll(), "Rates found" );
	}
}
