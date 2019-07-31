package com.dvneagu.interestservice.service;

import com.dvneagu.interestservice.dao.EsResponse;
import com.dvneagu.interestservice.entity.User;
import com.dvneagu.interestservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Copyright (c) 2019. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 7/30/2019.
 */
@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public EsResponse<User> login( String userName, String password )
	{
		Optional<User> byUserName = this.userRepository.findByUserName( userName );
		if ( !byUserName.isPresent() )
		{
			return new EsResponse<>( -1, "Username not found" );
		}
		User user = byUserName.get();
		if ( this.passwordEncoder.matches( password, user.getPassword() ) )
		{
			return new EsResponse<>( 1, user, "Login success" );
		}
		else
		{
			return new EsResponse<>( -1, "Password not matched" );
		}
	}

	@Override
	public EsResponse changePassword( String userName, String password, String currentPassword )
	{
		Optional<User> byUserName = this.userRepository.findByUserName( userName );
		if ( !byUserName.isPresent() )
		{
			return new EsResponse<>( -1, "Username not found" );
		}
		User user = byUserName.get();
		if ( !this.passwordEncoder.matches( currentPassword, user.getPassword() ) )
		{
			return new EsResponse<>( -1, user, "invalid Previous Password " );
		}
		user.setPassword( this.passwordEncoder.encode( password ) );
		this.userRepository.save( user );
		return new EsResponse( 1, "Password Changed" );
	}
}
