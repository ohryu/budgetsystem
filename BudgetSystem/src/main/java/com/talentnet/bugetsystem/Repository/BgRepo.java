package com.talentnet.bugetsystem.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.Bg;
import com.talentnet.bugetsystem.Entity.Wb;

@Repository
public interface BgRepo extends JpaRepository<Bg, Integer>{
	List<Bg> findByWb(Wb wb);
	Bg findByBgid(Integer bgid);
	Bg findByWbAndBgcode(Wb wb, String wbcode);
	Bg findByWbAndBgname(Wb wb, String wbname);
	
	@Transactional
	void removeByBgid(Integer bgid);
	@Transactional
	void removeByWb(Wb wb);
}
