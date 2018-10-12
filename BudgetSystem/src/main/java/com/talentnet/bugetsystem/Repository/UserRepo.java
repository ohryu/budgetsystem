package com.talentnet.bugetsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.BUser;


@Repository
public interface UserRepo extends JpaRepository<BUser, Integer>{
	BUser findByUsername(String username);
	BUser findByUserid(Integer id);
	void deleteByUserid(Integer id);
}
