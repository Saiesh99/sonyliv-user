package com.sonyliv.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonyliv.user.dto.SonyLivUserDTO;
import com.sonyliv.user.entity.SonyLivUserEntity;
import com.sonyliv.user.repo.SonyLivUserRepo;
import com.sonyliv.user.service.SonyLivUserService;
@Service
public class SonyLivUserServiceImpl implements SonyLivUserService{

	@Autowired
	private SonyLivUserRepo sonyLivUserRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public SonyLivUserDTO createUser(SonyLivUserDTO userDTO) {
		SonyLivUserEntity sonyLivUserEntity = modelMapper.map(userDTO, SonyLivUserEntity.class);
		SonyLivUserEntity savedUser = sonyLivUserRepo.save(sonyLivUserEntity);
		return modelMapper.map(savedUser, SonyLivUserDTO.class);
	}

	@Override
	public SonyLivUserDTO updateUser(SonyLivUserDTO userDTO, Integer userId) {
		SonyLivUserEntity sonyLivUserEntity = sonyLivUserRepo.findById(userId).get();
		sonyLivUserEntity.setAbout(userDTO.getAbout());
		sonyLivUserEntity.setEmail(userDTO.getEmail());
		sonyLivUserEntity.setName(userDTO.getName());
		sonyLivUserEntity.setPassword(userDTO.getPassword());
		SonyLivUserEntity updatedUser = sonyLivUserRepo.save(sonyLivUserEntity);
		return modelMapper.map(updatedUser, SonyLivUserDTO.class);
	}

	@Override
	public SonyLivUserDTO getUserById(Integer userId) {
		SonyLivUserEntity sonyLivUserEntity = sonyLivUserRepo.findById(userId).get();
		return modelMapper.map(sonyLivUserEntity, SonyLivUserDTO.class); 
	}

	@Override
	public List<SonyLivUserDTO> getAllUsers() {
		List<SonyLivUserEntity> users = sonyLivUserRepo.findAll();
		// System.out.println(users);

		List<SonyLivUserDTO> userDTOList = users.stream().map(a -> modelMapper.map(a, SonyLivUserDTO.class)).collect(Collectors.toList());
		// System.out.println(userDTOList);
		return userDTOList;
	}

	@Override
	public void deleteUserById(Integer userId) {

		sonyLivUserRepo.deleteById(userId);
		
	}

}
