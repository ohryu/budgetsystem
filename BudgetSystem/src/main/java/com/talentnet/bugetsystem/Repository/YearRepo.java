package com.talentnet.bugetsystem.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.Year;

@Repository
public interface YearRepo extends JpaRepository<Year, Integer>{

}
