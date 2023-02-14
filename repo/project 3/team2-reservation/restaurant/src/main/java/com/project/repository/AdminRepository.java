package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {

	AdminEntity findByIdAndPasswd(String id, String passwd);
	
	
	
	
	
	
	
	
}
