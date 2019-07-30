package com.dvneagu.interestservice.repository;

import com.dvneagu.interestservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Copyright (c) 2019. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 7/30/2019.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	Optional<User> findByUserName( @Param("userName") String userName );
}
