package com.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_DETAILS_TBL")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Email(message = "Username needs to be email")
	@NotBlank(message = "Username is required")
	@Column(name = "USERNAME", unique = true)
	private String username;

	@NotBlank(message = "Please enter your full name")
	@Column(name = "FULL_NAME")
	private String fullname;

	@NotBlank(message = "Password field is required")
	@Column(name = "PASSWORD")
	private String password;

	@Transient
	private String confirmPassword;

	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;

	@Column(name = "UPDATED_AT")
	private LocalDateTime updatedAt;

}
