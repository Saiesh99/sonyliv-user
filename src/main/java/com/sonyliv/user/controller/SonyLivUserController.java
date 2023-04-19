package com.sonyliv.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonyliv.user.dto.DeleteApiResponse;
import com.sonyliv.user.dto.SonyLivUserDTO;
import com.sonyliv.user.service.SonyLivUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sonyliv")
public class SonyLivUserController {
	
	@Autowired
	private SonyLivUserService sonyLivUserService;

	@PostMapping("/create")
	public ResponseEntity<SonyLivUserDTO> createUser(@Valid @RequestBody SonyLivUserDTO userDTO){
		SonyLivUserDTO createdUserDTO = sonyLivUserService.createUser(userDTO);
		return new ResponseEntity<>(createdUserDTO,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<SonyLivUserDTO> updateUser(@Valid @RequestBody SonyLivUserDTO userDTO,@PathVariable("userId") Integer userId){
		SonyLivUserDTO updatedUserDTO = sonyLivUserService.updateUser(userDTO, userId);
		return new ResponseEntity<>(updatedUserDTO,HttpStatus.OK);
	}
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<DeleteApiResponse> deleteUser(@PathVariable("userId") Integer userId){
		sonyLivUserService.deleteUserById(userId);

		return new ResponseEntity<>(new DeleteApiResponse("user deletd successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<SonyLivUserDTO>> getAllUsers(){
		//this.userService.getAllUsers();
		return new ResponseEntity<>(sonyLivUserService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<SonyLivUserDTO> getUserById(@PathVariable("userId") Integer userId){
		//this.userService.getAllUsers();
		return new ResponseEntity<>(sonyLivUserService.getUserById(userId),HttpStatus.OK);
	}	

}
