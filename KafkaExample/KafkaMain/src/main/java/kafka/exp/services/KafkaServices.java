package kafka.exp.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaServices {

	@KafkaListener(topics = "newTopic", groupId = "newer")
	void listener(String data) {
		System.out.println("received data:"+data+" ::::::::");
	}
}
