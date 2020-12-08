package com.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/api/secure")
public class SecuredController {
	@GetMapping(value = "/welcome")
	public ResponseEntity<String> reachSecureEndPoint() {
		return ResponseEntity.ok("Hey, Welcome to secure rest controller");
	}
}
