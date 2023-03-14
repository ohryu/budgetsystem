package com.talentnet.bugetsystem.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.Criteria;

@Repository
public interface CriteriaRepo extends JpaRepository<Criteria, Integer>{
	Criteria findByCriteriaid(Integer id);
	@Transactional
	void removeByCriteriaid(Integer id);
	Criteria findByCriterianame(String criterianame);
}
