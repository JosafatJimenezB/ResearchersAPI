package com.researchesapi.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResearcherDto {

    private String id;
    private String url;
    private String eid;
    private Integer totalResults;
    private String affiliationName;
    private String affiliationCity;
    private String affiliationCountry;

}
