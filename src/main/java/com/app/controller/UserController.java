package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.User;
import com.app.service.IUserService;
import com.app.validator.UserValidator;

@RestController
@RequestMapping(value = "/rest/api/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private UserValidator validator;

	@GetMapping(value = "/welcome")
	public ResponseEntity<String> welcome() {
		return ResponseEntity.ok("Welcome to Rest Controller");
	}

	@PostMapping(value = "/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody final User user, BindingResult result) {
		try {
			validator.validate(user, result);
			User saveduser = userService.saveUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveduser.getFullname() + " " + "Registered Successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to save record");
		}
	}
}
