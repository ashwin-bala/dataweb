package com.applns.bigdata.web.data.events;

import static org.assertj.core.api.Assertions.assertThatNoException;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.applns.bigdata.web.data.models.EventMessage;
import com.fasterxml.jackson.core.JsonProcessingException;


@SpringBootTest
@ActiveProfiles("test")
public class DataWebUnitTest {
	
	@Autowired
	private EventsSender eventsSender;
	
    @Test
    public void sendMessageTest() {
    	EventMessage eMessage = new EventMessage();
    	eMessage.setAdditionalFilters("filters");
    	eMessage.setFromDate("2010-09-09");
    	eMessage.setToDate("2010-10-11");
    	try {
			eventsSender.sendMessage(eMessage);
		} catch (JsonProcessingException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	assertThatNoException();
    		
    }

}
