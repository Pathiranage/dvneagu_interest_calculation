package com.dvneagu.interestservice.repository;

import com.dvneagu.interestservice.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2019. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 7/30/2019.
 */
@Repository
public interface RatesRepository extends JpaRepository<Rate, Long>
{
}
