package com.sonyliv.user.service;

import java.util.List;

import com.sonyliv.user.dto.SonyLivUserDTO;

public interface SonyLivUserService {

	SonyLivUserDTO createUser(SonyLivUserDTO userDTO);
	SonyLivUserDTO updateUser(SonyLivUserDTO userDTO, Integer userId);
	SonyLivUserDTO getUserById(Integer userId);
	List<SonyLivUserDTO> getAllUsers();
	void deleteUserById(Integer userId);
	
	
}
