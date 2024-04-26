package com.applns.bigdata.web.data.events;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;

import com.applns.bigdata.web.data.models.EventMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Configuration
public class EventsSender {
	
	private SqsClient sqsClient;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private String queueUrl;

    // @Value("${amazonsqs.queueName}")
    private String queueName="MessageQueue.fifo";
    
    
    @PostConstruct
    public void postCreate() {
    	GetQueueUrlRequest getQueueRequest = GetQueueUrlRequest.builder().queueName(queueName).build();
		this.sqsClient = SqsClient.builder().region(Region.US_EAST_1)
				.credentialsProvider(EnvironmentVariableCredentialsProvider.create()).build();
		this.queueUrl = sqsClient.getQueueUrl(getQueueRequest).queueUrl();

    }

	public EventsSender() {
		super();
	}

	public void sendMessage(EventMessage message) throws JsonProcessingException, NoSuchAlgorithmException {
		EventMessage eMessage = new EventMessage();
		eMessage.setFromDate(message.getFromDate());
		eMessage.setToDate(message.getToDate());
		eMessage.setAdditionalFilters(message.getAdditionalFilters());
		String msg = objectMapper.writeValueAsString(eMessage);
		String uuid = UUID.randomUUID().toString();
		SendMessageRequest sendMsgRequest = SendMessageRequest.builder().queueUrl(queueUrl).messageBody(msg)
				.messageGroupId(uuid).messageDeduplicationId(uuid).build();
		sqsClient.sendMessage(sendMsgRequest);
	}

}