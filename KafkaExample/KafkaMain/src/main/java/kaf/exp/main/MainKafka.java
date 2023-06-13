package kaf.exp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"kaf.exp.*"})
public class MainKafka {
public static void main(String[] args) {
	SpringApplication.run(MainKafka.class, args);
}


}
