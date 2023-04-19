package com.sonyliv.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonyliv.user.entity.SonyLivUserEntity;

public interface SonyLivUserRepo extends JpaRepository<SonyLivUserEntity, Integer>{

}
