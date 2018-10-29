package com.talentnet.bugetsystem.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.Criteria;
import com.talentnet.bugetsystem.Entity.CriteriaDetail;
import com.talentnet.bugetsystem.Entity.Dept;

@Repository
public interface CriteriaDetailRepo extends JpaRepository<CriteriaDetail, Integer>{
	List<CriteriaDetail> findByDept(Dept dept);
	@Transactional
	void removeByDept(Dept dept);
	CriteriaDetail findByCdid(Integer cdid);
	CriteriaDetail findByDeptAndCriteria(Dept dept, Criteria criteria);
	
	@Transactional
	void removeByCriteria(Criteria criteria);
}
