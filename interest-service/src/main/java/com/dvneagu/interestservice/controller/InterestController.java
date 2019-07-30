package com.dvneagu.interestservice.controller;

import com.dvneagu.interestservice.dao.EsResponse;
import com.dvneagu.interestservice.dao.RequestData;
import com.dvneagu.interestservice.entity.Rate;
import com.dvneagu.interestservice.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Kalana Weerarathne on 29th 07, 2019
 */
@RestController
@RequestMapping("/interest")
public class InterestController
{
	@Autowired
	private InterestService interestService;

	@PostMapping("")
	public ResponseEntity<EsResponse> createNewRate( @RequestBody Rate rate )
	{
		if ( rate == null )
		{
			return ResponseEntity.badRequest().body( new EsResponse<>( -1, "Rate details not found" ) );
		}
		return ResponseEntity.ok( this.interestService.createNewRate( rate ) );
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<EsResponse> deleteRate( @PathVariable long id )
	{
		if ( id <= 0 )
		{
			return ResponseEntity.badRequest().body( new EsResponse<>( -1, "Rate id not found" ) );
		}
		return ResponseEntity.ok( this.interestService.deleteRate( id ) );
	}

	@PostMapping("/calculate")
	public ResponseEntity calculateInterest( @RequestBody RequestData requestData )
	{
		this.interestService.calculateInterest( requestData );
		return ResponseEntity.ok().build();
	}
}
