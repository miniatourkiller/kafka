package kafka.exp.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class VideoDistributor {

	private static final String FORMAT= "classpath:videos/%s.mp4";
		
	
	public byte[] getVideo(String title) throws Exception {
		File file = ResourceUtils.getFile(String.format(FORMAT,title));
		String filepath = file.getPath();
		Path path =  Paths.get(filepath);
		byte[] array = Files.readAllBytes(path);
		return array;
	}
}
