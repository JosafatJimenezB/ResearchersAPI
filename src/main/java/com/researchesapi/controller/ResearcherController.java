package com.researchesapi.controller;

import java.util.ArrayList;

import com.researchesapi.service.ResearcherService;
import com.researchesapi.model.ResearcherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/researchers")
public class ResearcherController {

    @Autowired
    private ResearcherService researcherServiceImpl;

    @GetMapping(path = "", produces = { "application/json" })
    public ResponseEntity<ArrayList<ResearcherDto>> getResearcher() {

        ArrayList<ResearcherDto> researcherList = new ArrayList<>();
        researcherList = this.researcherServiceImpl.getResearcherDetailList();
        if (!researcherList.isEmpty()) {
            return new ResponseEntity<>(researcherList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(researcherList, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{researcherId}", produces = { "application/json" })
    public ResponseEntity<ResearcherDto> getResearcher(@PathVariable("researcherId") String researcherId){

        ResearcherDto researcherDto = new ResearcherDto();

        if(researcherId!=null){
            researcherDto = this.researcherServiceImpl.getResearcherDetail(researcherId);
            return new ResponseEntity<>(researcherDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(researcherDto, HttpStatus.NOT_FOUND);
        }
    }
}
