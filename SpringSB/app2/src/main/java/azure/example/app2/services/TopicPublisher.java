package azure.example.app2.services;

import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class TopicPublisher {

    private final Logger logger = LoggerFactory.getLogger(TopicPublisher.class);
    
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendPing() {
        logger.info("Sending pong");
        jmsTemplate.send("mytopic", (Session session) -> session.createTextMessage("pong"));
    }
    
}
