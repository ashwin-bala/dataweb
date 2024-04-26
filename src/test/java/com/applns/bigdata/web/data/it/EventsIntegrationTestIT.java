package com.applns.bigdata.web.data.it;

import static org.assertj.core.api.Assertions.assertThat;

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

import com.applns.bigdata.web.data.models.EventMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EventsIntegrationTestIT {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void greetingShouldReturnDefaultMessage() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        EventMessage eMessage = new EventMessage();
    	eMessage.setAdditionalFilters("newfilters");
    	eMessage.setFromDate("2010-11-11");
    	eMessage.setToDate("2010-12-12");
    	
        String requestBody = new ObjectMapper().writeValueAsString(eMessage);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/events",
                HttpMethod.POST, requestEntity, String.class);

                
		assertThat(responseEntity.getBody()).contains("Success");
	}
}
