package nl.maxvandewiel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;

/**
 * Created by max on 6/6/16.
 */
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

    private static final RestTemplate restTemplate = new RestTemplate();

    private static String resourceUrl = "http://192.168.99.100:8081/getvtb/";


    public void receiveMessage(String message) {
        String productType = message.substring(0,3);
        String productTypeNumber = message.substring(message.length()-2, message.length()).trim();
        LOGGER.info("Received <" + message + ">");
        LOGGER.info("ProductType = <" + productType + ">");
        //ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl + productType, String.class);
        //String product = response.getBody();
        //LOGGER.info("Retrieved = <" + product + " " + productTypeNumber +  "> for processing");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
