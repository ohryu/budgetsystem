package com.talentnet.bugetsystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.Bg;
import com.talentnet.bugetsystem.Entity.Wb;

@Repository
public interface BgRepo extends JpaRepository<Bg, Integer>{
	List<Bg> findByWb(Wb wb);
	Bg findByBgid(Integer bgid);
}
