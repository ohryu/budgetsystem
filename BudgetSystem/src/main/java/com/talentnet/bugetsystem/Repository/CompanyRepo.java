package com.talentnet.bugetsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer>{

}
