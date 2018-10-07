package com.talentnet.bugetsystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.Company;
import com.talentnet.bugetsystem.Entity.Group;

@Repository
public interface GroupRepo extends JpaRepository<Group, Integer>{
	List<Group> findByCompany(Company company);
}
