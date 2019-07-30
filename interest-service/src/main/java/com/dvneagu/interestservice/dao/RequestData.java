package com.dvneagu.interestservice.dao;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Copyright (c) 2019. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 7/30/2019.
 */
@Data
public class RequestData implements Serializable
{
	private LocalDateTime actualDate;
	private LocalDateTime receivedData;
}
