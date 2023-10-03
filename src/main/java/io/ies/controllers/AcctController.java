package io.ies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.ies.bindings.UserAccountForm;
import io.ies.service.AccountService;


@RestController
public class AcctController {

	@Autowired
	private AccountService service;

	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody UserAccountForm form) throws Exception {
		boolean status = service.createAcct(form);
		if (status) {
			return new ResponseEntity<>("Account Created",HttpStatus.CREATED);//201
		} else {
			 return new ResponseEntity<>("Account Failed to Create",HttpStatus.INTERNAL_SERVER_ERROR);//500
		}

	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserAccountForm>> getAllUsers(){
		 List<UserAccountForm> users = service.fetchUserAccounts();
		 return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserAccountForm> getUserById(@PathVariable ("userId") Integer userId) {
		 UserAccountForm userAccById = service.getUserAccById(userId);
		 return new ResponseEntity<>(userAccById,HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<List<UserAccountForm>> updateUserAcct(@PathVariable 
			("userId") Integer userId, @PathVariable ("status") String status){
		 service.changeAcctStatus(userId, status);
		List<UserAccountForm> fetchUserAccounts = service.fetchUserAccounts();
		return new ResponseEntity<>(fetchUserAccounts,HttpStatus.OK);
	}

}
