package com.talentnet.bugetsystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.DTO.bdsqueryDTO;
import com.talentnet.bugetsystem.DTO.blinequeryDTO;
import com.talentnet.bugetsystem.DTO.deptqueryDTO;
import com.talentnet.bugetsystem.Entity.SponsorReport;

@Repository
public interface SponsorReportRepo extends JpaRepository<SponsorReport, Integer>{
	@Query(value = "SELECT new com.talentnet.bugetsystem.DTO.bdsqueryDTO(sum(s.amount), s.deptcode, s.blname, s.blcode) "
			+ "FROM SponsorReport s "
			+ "WHERE s.budget IN :bgs GROUP BY s.deptcode, s.blcode,s.blname")
	public List<bdsqueryDTO> getdatagroupby(@Param("bgs") List<Integer> bgs);
	
	@Query(value = "SELECT new com.talentnet.bugetsystem.DTO.blinequeryDTO (s.blcode, s.blname) "
			+ "FROM SponsorReport s "
			+ "WHERE s.budget IN :bgs GROUP BY s.blcode, s.blname")
	public List<blinequeryDTO> getDistinctBline(@Param("bgs") List<Integer> bgs);
	
	@Query(value = "SELECT new com.talentnet.bugetsystem.DTO.deptqueryDTO(s.deptcode) "
			+ "FROM SponsorReport s "
			+ "WHERE s.budget IN :bgs GROUP BY s.deptcode")
	public List<deptqueryDTO> getDistinctDept(@Param("bgs") List<Integer> bgs);
}
