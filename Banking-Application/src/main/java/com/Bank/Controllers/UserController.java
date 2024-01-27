package com.Bank.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bank.Dto.BankResponse;
import com.Bank.Dto.UserRequest;
import com.Bank.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired UserService userservice;

	@PostMapping()
	public ResponseEntity<BankResponse> createAccount(@RequestBody UserRequest userRequest) {
		BankResponse response= userservice.createAccount(userRequest);
		 return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
}
