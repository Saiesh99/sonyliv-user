package com.sonyliv.user.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.sonyliv.user.dto.SonyLivUserDTO;
import com.sonyliv.user.service.SonyLivUserService;

@WebMvcTest(SonyLivUserController.class)
public class SonyLivUserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SonyLivUserService sonyLivUserService;
	
	private SonyLivUserDTO sonyLivUserDTO; 
	

	
	@BeforeEach
	void setUp() {
		sonyLivUserDTO	= new SonyLivUserDTO();
		sonyLivUserDTO.setId(1);
		sonyLivUserDTO.setName("saiesh");
		sonyLivUserDTO.setEmail("saiesh@gmail.com");
		sonyLivUserDTO.setPassword("saiesh");
		sonyLivUserDTO.setAbout("1st user");
	}
	
	@Test
	void createUserTest() throws Exception {
		
		SonyLivUserDTO sonyLivUserDTO1	= new SonyLivUserDTO();
		sonyLivUserDTO1.setName("saiesh");
		sonyLivUserDTO1.setEmail("saiesh@gmail.com");
		sonyLivUserDTO1.setPassword("saiesh");
		sonyLivUserDTO1.setAbout("1st user");
		
		Mockito.when(sonyLivUserService.createUser(sonyLivUserDTO1)).thenReturn(sonyLivUserDTO);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/sonyliv/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"name\" : \"saiesh\",\r\n"
						+ "    \"email\" : \"saiesh@gmail.com\",\r\n"
						+ "    \"password\" : \"saiesh\",\r\n"
						+ "    \"about\" : \"1st user\"\r\n"
						+ "\r\n"
						+ "}")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		
	}
	
	@Test
	void findByIdTest() throws Exception {
		Integer userId = 1;
		Mockito.when(sonyLivUserService.getUserById(userId)).thenReturn(sonyLivUserDTO);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/sonyliv/1")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	@Test
	void updateUserTest() throws Exception {
		
		SonyLivUserDTO sonyLivUserDTO1	= new SonyLivUserDTO();
		sonyLivUserDTO1.setName("saiesh");
		sonyLivUserDTO1.setEmail("saiesh@gmail.com");
		sonyLivUserDTO1.setPassword("saiesh");
		sonyLivUserDTO1.setAbout("1st user");
		
		Mockito.when(sonyLivUserService.updateUser(sonyLivUserDTO1,1)).thenReturn(sonyLivUserDTO);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/sonyliv/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"name\" : \"saiesh\",\r\n"
						+ "    \"email\" : \"saiesh@gmail.com\",\r\n"
						+ "    \"password\" : \"saiesh\",\r\n"
						+ "    \"about\" : \"1st user\"\r\n"
						+ "\r\n"
						+ "}")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		
	}
	
	@Test
	void getAllUsersTest() throws Exception {
		List<SonyLivUserDTO> sonyLivUserDTOList = new ArrayList<SonyLivUserDTO>(); 
		sonyLivUserDTOList.add(sonyLivUserDTO);
		Mockito.when(sonyLivUserService.getAllUsers()).thenReturn(sonyLivUserDTOList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/sonyliv/")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	@Test
	void deleteUserByIdTest() throws Exception {
		Integer userId = 1;
		doNothing().when(sonyLivUserService).deleteUserById(userId);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/sonyliv/delete/1")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
}
