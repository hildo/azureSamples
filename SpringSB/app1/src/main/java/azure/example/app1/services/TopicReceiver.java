package azure.example.app1.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class TopicReceiver {

    private final Logger logger = LoggerFactory.getLogger(TopicReceiver.class);
    
    @Autowired
    private QueuePublisher queuePublisher;
    
    @JmsListener(destination = "mytopic",
            containerFactory = "topicJmsListenerContainerFactory",
            subscription = "TopicReceiver")
    public void onMessage(String message) {
        logger.info("Received message from topic: {}", message);
        try {
            Thread.sleep(3000L);
            queuePublisher.sendPing();
        } catch (InterruptedException ex) {
        }
        
    }
    
}
