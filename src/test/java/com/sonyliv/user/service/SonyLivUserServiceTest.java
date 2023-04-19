package com.sonyliv.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sonyliv.user.dto.SonyLivUserDTO;
import com.sonyliv.user.entity.SonyLivUserEntity;
import com.sonyliv.user.repo.SonyLivUserRepo;

@SpringBootTest
public class SonyLivUserServiceTest {

	@Autowired
	private SonyLivUserService sonyLivUserService;
	
	@MockBean
	private SonyLivUserRepo sonyLivUserRepo;
	
	@BeforeEach
	void setUp() {
		Optional<SonyLivUserEntity> sonyLivUserEntity= Optional.of(new SonyLivUserEntity(1, "saiesh", "saiesh@gamil.com", "saiesh", "1st user"));
		Mockito.when(sonyLivUserRepo.findById(1)).thenReturn(sonyLivUserEntity);
		
		SonyLivUserEntity createdSonyLivUserEntity= new SonyLivUserEntity(1, "saiesh", "saiesh@gamil.com", "saiesh", "1st user");
		Mockito.when(sonyLivUserRepo.save(createdSonyLivUserEntity)).thenReturn(createdSonyLivUserEntity);
		
		SonyLivUserEntity updatedSonyLivUserEntity= new SonyLivUserEntity(1, "saiesh1", "saiesh@gamil.com", "saiesh", "1st user");
		Mockito.when(sonyLivUserRepo.save(updatedSonyLivUserEntity)).thenReturn(updatedSonyLivUserEntity);
		
		SonyLivUserEntity sonyLivUserEntity11= new SonyLivUserEntity(1, "saiesh1", "saiesh@gamil.com", "saiesh", "1st user");
		SonyLivUserEntity sonyLivUserEntity22= new SonyLivUserEntity(2, "saiesh2", "saiesh2@gamil.com", "saiesh2", "2nd user");
		List<SonyLivUserEntity> sonyLivUserEntityList = new ArrayList<SonyLivUserEntity>();
		sonyLivUserEntityList.add(sonyLivUserEntity11);
		sonyLivUserEntityList.add(sonyLivUserEntity22);
		//System.out.println(sonyLivUserEntityList);
		Mockito.when( sonyLivUserRepo.findAll()).thenReturn(sonyLivUserEntityList);
		
		Optional<SonyLivUserEntity> sonyLivUserEntity4 = Optional.of(new SonyLivUserEntity());
		Mockito.when(sonyLivUserRepo.findById(2)).thenReturn(sonyLivUserEntity4);
	}
	
	@Test
	public void getUserByIdTest() {
		Integer userId = 1;
		SonyLivUserDTO sonyLivUserDTO1 =  sonyLivUserService.getUserById(userId);
		assertEquals(userId, sonyLivUserDTO1.getId());
	}
	
	@Test
	public void createUserTest() {
		SonyLivUserDTO sonyLivUserDTO1= new SonyLivUserDTO(1, "saiesh", "saiesh@gamil.com", "saiesh", "1st user");
		SonyLivUserDTO sonyLivUserDTO2 =  sonyLivUserService.createUser(sonyLivUserDTO1);
		assertEquals(sonyLivUserDTO1, sonyLivUserDTO2);
	}
	
	@Test
	public void updateUserTest() {
		Integer userId = 1;
		SonyLivUserDTO sonyLivUserDTO1= new SonyLivUserDTO(1, "saiesh", "saiesh@gamil.com", "saiesh", "1st user");
		sonyLivUserDTO1.setName("saiesh1");
		SonyLivUserDTO sonyLivUserDTO2 =  sonyLivUserService.updateUser(sonyLivUserDTO1,userId);
		assertEquals(sonyLivUserDTO1, sonyLivUserDTO2);
	}
	
	@Test
	public void getAllUsersTest() {
		SonyLivUserDTO sonyLivUserEntity1= new SonyLivUserDTO(1, "saiesh1", "saiesh@gamil.com", "saiesh", "1st user");
		SonyLivUserDTO sonyLivUserEntity2= new SonyLivUserDTO(2, "saiesh2", "saiesh2@gamil.com", "saiesh2", "2nd user");
		List<SonyLivUserDTO> sonyLivUserDTOList = new ArrayList<SonyLivUserDTO>();
		sonyLivUserDTOList.add(sonyLivUserEntity1);
		sonyLivUserDTOList.add(sonyLivUserEntity2);
		//System.out.println(sonyLivUserDTOList);
		List<SonyLivUserDTO> sonyLivUserDTOList1=  sonyLivUserService.getAllUsers();
		//System.out.println(sonyLivUserDTOList1);
		assertEquals(sonyLivUserDTOList, sonyLivUserDTOList1 );
	}
	
	@Test
	public void deleteByIdTest() {
		Integer userId = 2;
		SonyLivUserDTO sonyLivUserDTO2 =new SonyLivUserDTO();
		sonyLivUserService.deleteUserById(userId);
		SonyLivUserDTO sonyLivUserDTO1 =  sonyLivUserService.getUserById(userId);
		System.out.println(sonyLivUserDTO1);
		System.out.println(sonyLivUserDTO2);
		assertEquals(sonyLivUserDTO1, sonyLivUserDTO2);
	}
	
}
