package com.applns.bigdata.web.data.events;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.applns.bigdata.web.data.models.EventMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@ExtendWith(MockitoExtension.class)
public class EventsSenderMockTest {

	@Mock
	private SqsClient sqsClient;

	@InjectMocks
	private EventsSender eventsSender;

	@Test
	void testSendMessage() throws JsonProcessingException, NoSuchAlgorithmException {
		EventMessage eventMessage = new EventMessage();
		// Set up test data
		eventMessage.setAdditionalFilters(null);
		eventMessage.setFromDate(null);

		// Call the method under test
		eventsSender.sendMessage(eventMessage);

		// Verify that the method was called with the correct arguments
		verify(sqsClient).sendMessage(any(SendMessageRequest.class));
	}

	@Test
	void testSendMessageWithAdditionalFilters() throws JsonProcessingException, NoSuchAlgorithmException {
		EventMessage eventMessage = new EventMessage();
		// Set up test data
		eventMessage.setAdditionalFilters("filter1,filter2");
		eventMessage.setFromDate(null);

		// Call the method under test
		eventsSender.sendMessage(eventMessage);

		// Verify that the method was called with the correct arguments
		verify(sqsClient).sendMessage(any(SendMessageRequest.class));
		// Add additional verification/assertions specific to this test case
		
	}
}
