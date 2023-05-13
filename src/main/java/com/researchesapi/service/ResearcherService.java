package com.researchesapi.service;

import java.util.ArrayList;
import com.researchesapi.model.ResearcherDto;

public interface ResearcherService {
    ResearcherDto getResearcherDetail(String id);
    ArrayList<ResearcherDto> getResearcherDetailList();
}
