package azure.example.app2.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class QueueReceiver {

    private final Logger logger = LoggerFactory.getLogger(QueueReceiver.class);
    
    @JmsListener(destination = "myqueue")
    public void onMessage(String message) {
        logger.info("Received message from queue: {}", message);
    }
}
