package com.dvneagu.interestservice.dao;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright (c) 2019. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 7/30/2019.
 */
@Data
public class RequestData implements Serializable
{
	private String actualDate;
	private String receivedDate;
	private double amount;
}
