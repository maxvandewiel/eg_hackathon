package nl.maxvandewiel.service.userregistration.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
//import org.springframework.boot.bind.RelaxedPropertyResolver;

@SpringBootApplication
@EnableDiscoveryClient
@Import({BeanValidatorPluginsConfiguration.class, MessageResourceConfiguration.class,
        SwaggerConfiguration.class, RabbitMQConfiguration.class, MongoDBConfiguration.class})
@ComponentScan(basePackages = "nl.maxvandewiel.service.userregistration")
@EnableAutoConfiguration
//@Profile({"development"})
public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        //SpringApplication.run(Application.class, args);
    }
}
