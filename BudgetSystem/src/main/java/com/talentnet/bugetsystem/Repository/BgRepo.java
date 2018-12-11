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
	Bg findByBgcode(String bgcode);
	Bg findByBgname(String bgname);
	Bg findByWbAndBgcode(Wb wb, String bgcode);
	Bg findByWbAndBgname(Wb wb, String bgname);
	
	@Transactional
	void removeByBgid(Integer bgid);
	@Transactional
	void removeByWb(Wb wb);
}
