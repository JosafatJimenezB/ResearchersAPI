package com.researchesapi.jpa.repository;

import com.researchesapi.jpa.ResearchersDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearchersDetailRepository extends JpaRepository<ResearchersDetail, String> {}
