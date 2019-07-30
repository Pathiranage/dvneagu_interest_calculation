package com.dvneagu.interestservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Kalana Weerarathne on 29th 07, 2019
 */
@Data
@Entity
@Table(name = "RATES")
public class Rate extends AuditModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FROM_DATE")
    private LocalDateTime fromDate;

    @Column(name = "INTEREST_RATE", precision = 3, scale = 2)
    private double interestDate;

    @Column(name = "PENALTIES", precision = 3, scale = 2)
    private double penalties;
}
