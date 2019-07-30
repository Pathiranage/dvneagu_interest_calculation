package com.dvneagu.interestservice.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2019. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 7/30/2019.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsResponse<T>
{
	private int status;
	private T data;
	private String message;

	public EsResponse( int status, String message )
	{
		this.status = status;
		this.message = message;
	}
}
