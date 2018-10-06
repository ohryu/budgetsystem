package com.talentnet.bugetsystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.Dept;
import com.talentnet.bugetsystem.Entity.Group;

@Repository
public interface DeptRepo extends JpaRepository<Dept, Integer>{
	List<Dept> findByGroupAndControl(Group group, boolean control);
}
