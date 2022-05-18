package com.example.M2.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
public class M2Producer
{
	public static final String topic = "k2-topic";
    //This Will Produce messages into K2
	@Autowired
	private KafkaTemplate<String, String> kafkaTemp;

	public void publishToTopic(String message) {
	System.out.println("Publishing to k2-topic "+message);
	this.kafkaTemp.send(topic, message);
	}
	

}
