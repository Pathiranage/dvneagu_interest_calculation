package com.dvneagu.interestservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Kalana Weerarathne on 29th 07, 2019
 */
@Data
@Entity
@Table(name = "USERS")
@JsonIgnoreProperties(value = { "password", "createdAt", "updatedAt" }, allowSetters = true)
public class User extends AuditModel
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "USER_TYPE")
	private String userType;
}
