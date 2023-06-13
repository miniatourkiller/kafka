package kaf.exp.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kafka.exp.services.VideoDistributor;

@RestController
public class KafkaController {

	@Autowired
KafkaTemplate<String, byte[]> kafka;
	
	
@RequestMapping("/")
public String Starter() {
	return "hello";
}
@Autowired
VideoDistributor vd;
@RequestMapping("/video")
public void video() throws Exception {
	kafka.send("newTopic", vd.getVideo("jamesvideo"));
}
}
