package com.app.entity;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequest {
	@NotBlank(message = "Usrename can not blank")
	private String username;

	@NotBlank(message = "Passwword can not blank")
	private String password;
}
