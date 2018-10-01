package com.talentnet.bugetsystem.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.BUser;


@Repository
public interface UserDAO extends JpaRepository<BUser, Integer>{
	BUser findByUsername(String username);
}
