package kaf.exp.main;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import kafka.exp.services.VideoDistributor;



@Configuration
public class KafkaConfig {

	@Bean
	VideoDistributor setVideoDistributor() {
		return new VideoDistributor();
	}
	@Bean
	public NewTopic newTopic() {
		return TopicBuilder.name("newTopic")
				.build();
	}
	
	///////////Producer code////////////////////////////////////////////
	@Value("${spring.kafka.bootstrap-servers}")
	private String servers;
	
	public Map<String, Object> producerConfig(){
		Map<String, Object> objs = new HashMap<>();
		objs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
		objs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		objs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
		return objs;
	}
	
	@Bean
	public ProducerFactory<String, byte[]> producerFactory(){
		return new DefaultKafkaProducerFactory<>(producerConfig());
	}
	@Bean
	public KafkaTemplate<String, byte[]> temp(ProducerFactory<String, byte[]> producerFactory){
		return new KafkaTemplate<>(producerFactory);
	}
	///////////////////////Consumer code////////////////////////////////////
	
	
	public Map<String, Object> consumerConfig(){
		Map<String, Object> objs = new HashMap<>();
		objs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
		objs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		objs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
		return objs;
	}
	@Bean
	public ConsumerFactory<String, String> consumerFactory(){
		return new DefaultKafkaConsumerFactory<>(consumerConfig());
	}
	
	
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> listenerFactory(ConsumerFactory<String, String> consumerFactory)
	{
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}
	
}
