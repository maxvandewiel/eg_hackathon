package nl.maxvandewiel.microservices.eurekaserver;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigServer
public class ConfigServer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigServer.class).web(true).run(args);
    }

}
