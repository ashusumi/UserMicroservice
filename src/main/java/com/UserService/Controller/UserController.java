package com.UserService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserService.Model.Users;
import com.UserService.ServiceImpl.UsersServiceImpl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	 UsersServiceImpl impl;

	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers() {
		try {
			return new ResponseEntity<List<Users>>(impl.getAllUsers(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Integer id){
//		try {
			return ResponseEntity.status(HttpStatus.OK).body(impl.getUserById(id));
//		} catch (Exception e) {
// 			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//		}
	}

	@PostMapping("/addUser")
	public ResponseEntity<?> addUsers(@RequestBody Users entity) {
		try {

			return new ResponseEntity<String>(impl.addUsers(entity), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}

	}

	@PutMapping("update/{id}")
	public ResponseEntity<String> updateUsers(@PathVariable Integer id, @RequestBody Users users) {
		try {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(impl.updateUsers(id, users));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteUsers(@PathVariable Integer id) {
		try {
			return new ResponseEntity<String>(impl.deleteUserById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
