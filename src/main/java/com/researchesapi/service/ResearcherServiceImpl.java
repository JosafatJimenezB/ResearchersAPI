package com.researchesapi.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.researchesapi.jpa.repository.ResearchersDetailRepository;
import com.researchesapi.jpa.ResearchersDetail;
import com.researchesapi.model.ResearcherDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.beans.BeanUtils;
import java.lang.invoke.MethodHandles;
import java.text.MessageFormat;
import java.util.ArrayList;

@Service
public class ResearcherServiceImpl implements ResearcherService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String HTTP_STATUS_MESSAGE = "HttpStatus: {}";

    private String jsonString;
    private RestTemplate restTemplate;

    @Value("${com-researchesapi.serpApi.apiKey}")
    private String serpApiApiKey;

    @Value("${com-researchesapi.serpApi.google.scholar.author.url}")
    private String authorServiceUrl;

    @Autowired
    private ResearchersDetailRepository researchersDetailRepository;

    @Retryable(backoff = @Backoff(1000), maxAttempts = 2)
    public ResearcherDto getResearcherDetail(String id) {
        ResearcherDto researcherDto = null;
        try {
            restTemplate = new RestTemplate();
            authorServiceUrl = MessageFormat.format(authorServiceUrl, id, serpApiApiKey);

            ResponseEntity<String> response = restTemplate.getForEntity(authorServiceUrl, String.class);

            HttpStatus statusCode = response.getStatusCode();
            if (statusCode == HttpStatus.OK) {
                researcherDto = createResearcherDto(response.getBody(), id);
                researchersDetailRepository.save(researcherDtoToResearchersDetail(researcherDto));
            }
            return researcherDto;
        } catch (HttpClientErrorException exception) {
            logger.info(HTTP_STATUS_MESSAGE, exception.getStatusCode().value());
            logger.error(exception.getResponseBodyAsString());
        }
        return researcherDto;
    }

    private ResearcherDto createResearcherDto(String responseBody, String id) {
        ResearcherDto researcherDto = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(responseBody);

            researcherDto = new ResearcherDto();
            researcherDto.setId(id);
            researcherDto.setUrl(jsonNode.get("search_metadata").get("google_scholar_author_url").asText());
            researcherDto.setEid(jsonNode.get("search_metadata").get("id").asText());
            researcherDto.setAffiliationName(jsonNode.get("author").get("affiliations").asText());
            researcherDto.setTotalResults(jsonNode.get("articles").size());
        } catch (JsonProcessingException jsonProcessingException) {
            logger.error(jsonProcessingException.getMessage());
        }
        return researcherDto;
    }

    private ResearchersDetail researcherDtoToResearchersDetail(ResearcherDto researcherDto) {
        ResearchersDetail researchersDetail = new ResearchersDetail();
        BeanUtils.copyProperties(researcherDto, researchersDetail);
        return researchersDetail;
    }

    public ArrayList<ResearcherDto> getResearcherDetailList() {
        List<ResearchersDetail> researchersDetailList = researchersDetailRepository.findAll();
        ArrayList<ResearcherDto> researcherDtoList = new ArrayList<>();

        for (ResearchersDetail researchersDetail : researchersDetailList) {
            ResearcherDto researcherDto = new ResearcherDto();
            BeanUtils.copyProperties(researchersDetail, researcherDto);
            researcherDtoList.add(researcherDto);
        }
        return researcherDtoList;
    }
}
