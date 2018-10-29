package com.talentnet.bugetsystem.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer>{
	public Company findByCompanyid(Integer id);
	public Company findByCompanyname(String name);
	@Transactional
	void removeByCompanyid(Integer id);
}
