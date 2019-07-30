package com.dvneagu.interestservice.controller;

import com.dvneagu.interestservice.dao.EsResponse;
import com.dvneagu.interestservice.entity.User;
import com.dvneagu.interestservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (c) 2019. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 7/30/2019.
 */
@RestController
@RequestMapping("user")
public class UserController
{
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<EsResponse<User>> login( @RequestHeader String userName, @RequestHeader String password )
	{
		if ( userName == null || userName.isEmpty() )
		{
			return ResponseEntity.badRequest().body( new EsResponse<>( -1, "Invalid Username" ) );
		}
		if ( password == null || password.isEmpty() )
		{
			return ResponseEntity.badRequest().body( new EsResponse<>( -1, "Invalid password" ) );
		}
		return ResponseEntity.ok( this.userService.login( userName, password ) );
	}
}
