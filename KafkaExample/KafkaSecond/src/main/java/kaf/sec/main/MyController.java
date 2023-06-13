package kaf.sec.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	KafkaTemplate<String, String> kafka;
	
	public MyController(KafkaTemplate<String, String> kafka) {
		this.kafka = kafka;
	}
	Logger logger;
	@RequestMapping(value= "send", method = RequestMethod.POST)
	public String sendMessage(@RequestBody Message message) {
		logger = LoggerFactory.getLogger(MyController.class);
		kafka.send("newTopic", message.getMessage());
		logger.info(message.getMessage()+" :::Sent to kafka");
		return "done";
	}
}
