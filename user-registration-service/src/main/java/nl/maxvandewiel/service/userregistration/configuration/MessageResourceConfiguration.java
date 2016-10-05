package nl.maxvandewiel.service.userregistration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Created by max on 6/8/16.
 */
@Configuration
//@Profile("messagesource")
public class MessageResourceConfiguration {
    @Bean(name = "messageSource")
    ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageBundle = new ResourceBundleMessageSource();
        messageBundle.setBasename("classpath:messages/messages");
        messageBundle.setDefaultEncoding("UTF-8");
        return messageBundle;
    }
}
