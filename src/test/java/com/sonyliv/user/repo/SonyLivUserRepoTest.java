package com.sonyliv.user.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sonyliv.user.entity.SonyLivUserEntity;

@DataJpaTest
public class SonyLivUserRepoTest {
	
	@Autowired
	private SonyLivUserRepo sonyLivUserRepo;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@BeforeEach
	void setUp() {
		SonyLivUserEntity createdSonyLivUserEntity= new SonyLivUserEntity();
		createdSonyLivUserEntity.setAbout("1st user");
		createdSonyLivUserEntity.setName("saiesh");
		createdSonyLivUserEntity.setEmail("saiesh@gamil.com");
		createdSonyLivUserEntity.setPassword("saiesh");
		testEntityManager.persist(createdSonyLivUserEntity);
	
	}
	
	@Test
	public void finByIdTest(){
		Integer userId = 1;
		SonyLivUserEntity createdSonyLivUserEntity1 = sonyLivUserRepo.findById(userId).get();
		System.out.println(userId);
		System.out.println(createdSonyLivUserEntity1.getId());
		assertEquals(userId, createdSonyLivUserEntity1.getId());
	}
	
	@Test
	public void finAllTest(){
		SonyLivUserEntity createdSonyLivUserEntity= new SonyLivUserEntity();
	    createdSonyLivUserEntity.setId(3);
		createdSonyLivUserEntity.setAbout("1st user");
		createdSonyLivUserEntity.setName("saiesh");
		createdSonyLivUserEntity.setEmail("saiesh@gamil.com");
		createdSonyLivUserEntity.setPassword("saiesh");
		List<SonyLivUserEntity> sonyLivUserEntityList1 = new ArrayList<>();
		sonyLivUserEntityList1.add(createdSonyLivUserEntity);
		List<SonyLivUserEntity> createdSonyLivUserEntityList = sonyLivUserRepo.findAll();
		
		System.out.println(createdSonyLivUserEntityList);
		System.out.println(sonyLivUserEntityList1);
		assertEquals(sonyLivUserEntityList1, createdSonyLivUserEntityList);
	}
	
	@Test
	public void createUserTest(){
		
		SonyLivUserEntity createdSonyLivUserEntity1= new SonyLivUserEntity();
		createdSonyLivUserEntity1.setAbout("1st user");
		createdSonyLivUserEntity1.setName("saiesh");
		createdSonyLivUserEntity1.setEmail("saiesh@gamil.com");
		createdSonyLivUserEntity1.setPassword("saiesh");
		SonyLivUserEntity createdSonyLivUserEntity2 = sonyLivUserRepo.save(createdSonyLivUserEntity1);
		assertEquals(createdSonyLivUserEntity1, createdSonyLivUserEntity2);
	}
	
	@Test
	public void updateUserTest(){
		
		SonyLivUserEntity createdSonyLivUserEntity1= new SonyLivUserEntity();
		createdSonyLivUserEntity1.setAbout("1st user");
		createdSonyLivUserEntity1.setName("saiesh");
		createdSonyLivUserEntity1.setEmail("saiesh@gamil.com");
		createdSonyLivUserEntity1.setPassword("saiesh");
		SonyLivUserEntity createdSonyLivUserEntity2 = sonyLivUserRepo.save(createdSonyLivUserEntity1);
		assertEquals(createdSonyLivUserEntity1, createdSonyLivUserEntity2);
	}
	
	@Test
	public void deleteByIdTest(){
		Integer userId = 1;
		sonyLivUserRepo.deleteById(userId);
		SonyLivUserEntity sonyLivUserEntity = new SonyLivUserEntity();
		Optional<SonyLivUserEntity> createdSonyLivUserEntity1 = sonyLivUserRepo.findById(userId);
		if(createdSonyLivUserEntity1.isPresent()) {
			sonyLivUserEntity = createdSonyLivUserEntity1.get();
		}
		
		Assertions.assertThat(sonyLivUserEntity.getName()).isNull();
	}
}
