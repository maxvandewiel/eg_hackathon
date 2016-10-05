package nl.maxvandewiel.service.userregistration.restfull.controller;

import nl.maxvandewiel.service.userregistration.configuration.RabbitMQConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by max on 6/9/16.
 */
@RestController
public class TestVTBMessageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestVTBMessageController.class);

    @Autowired
    AmqpTemplate rabbitTemplate;

    @RequestMapping(path = "/testvtbmessages", method = RequestMethod.GET)
    public
    @ResponseBody
    String triggerTest() {
        StringBuffer retVal = new StringBuffer();
        for (int i = 0; i <= 10; i++) {
            String testNotificatie = "VTS notificatie " + i;
            LOGGER.info("Sending message <{}> to VTB TopicExchange", testNotificatie);
            rabbitTemplate.convertAndSend(RabbitMQConfiguration.topicExchangeName, "#.vts.register-user", testNotificatie /*, MessagePostProcessor*/);
            retVal.append("Message <" + testNotificatie + "> send to VTB TopicExchange\n");
        }
        for (int j = 0; j <= 10; j++) {
            String testNotificatie = "VTT notificatie " + j;
            LOGGER.info("Sending message <{}> to VTB TopicExchange", testNotificatie);
            rabbitTemplate.convertAndSend(RabbitMQConfiguration.topicExchangeName, "#.vtt.register-user", testNotificatie /*, MessagePostProcessor*/);
            retVal.append("Message <" + testNotificatie + "> send to VTB TopicExchange\n");
        }
        for (int h = 0; h <= 10; h++) {
            String testNotificatie = "VTL notificatie " + h;
            LOGGER.info("Sending message <{}> to VTB TopicExchange", testNotificatie);
            rabbitTemplate.convertAndSend(RabbitMQConfiguration.topicExchangeName, "#.vtl.register-user", testNotificatie /*, MessagePostProcessor*/);
            retVal.append("Message <" + testNotificatie + "> send to VTB TopicExchange\n");
        }
        return retVal.toString();
    }

}
