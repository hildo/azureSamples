/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.example.app1.services;

import javax.annotation.PostConstruct;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueuePublisher {
    
    private final Logger logger = LoggerFactory.getLogger(QueuePublisher.class);
    
    @Autowired
    private JmsTemplate jmsTemplate;

    @PostConstruct
    public void afterConstruct() {
//        sendPing();

    }
 
    public void sendPing() {
        logger.info("Sending ping");
        jmsTemplate.send("myqueue", (Session session) -> session.createTextMessage("ping"));
    }
}
