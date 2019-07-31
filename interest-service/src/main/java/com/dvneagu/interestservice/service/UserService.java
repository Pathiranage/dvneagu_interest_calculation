package com.dvneagu.interestservice.service;

import com.dvneagu.interestservice.dao.EsResponse;
import com.dvneagu.interestservice.entity.User;

/**
 * Copyright (c) 2019. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 7/30/2019.
 */
public interface UserService
{
	EsResponse<User> login( String userName, String password );

	EsResponse changePassword( String userName, String password, String currentPassword );

}
