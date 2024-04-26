package com.applns.bigdata.web.data.it;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.applns.bigdata.web.data.models.web.DataWebRequest;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ActionIntegrationTestIT {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void testEarliestArtDatesByPlaceOfOrigin() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        DataWebRequest name = new DataWebRequest();
        String requestBody = new ObjectMapper().writeValueAsString(name);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/earliestArtDatesByPlaceOfOrigin",
                HttpMethod.POST, requestEntity, String.class);

                
		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
	}
	
	
	@Test
	void testMostPopularStyleTitles() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        DataWebRequest name = new DataWebRequest();
        String requestBody = new ObjectMapper().writeValueAsString(name);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/mostPopularStyleTitles",
                HttpMethod.POST, requestEntity, String.class);

                
		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	void testGetAllArtWorkTypeTitles() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        DataWebRequest name = new DataWebRequest();
        String requestBody = new ObjectMapper().writeValueAsString(name);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/getAllArtWorkTypeTitles",
                HttpMethod.POST, requestEntity, String.class);

                
		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
	}


}
