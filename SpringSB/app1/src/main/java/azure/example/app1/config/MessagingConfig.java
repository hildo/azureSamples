package azure.example.app1.config;

import java.io.UnsupportedEncodingException;

import javax.jms.ConnectionFactory;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MessagingConfig {
    
    @Value("${spring.application.name}")
    private String clientId;
    
    @Bean
    public ConnectionFactory jmsConnectionFactory(MessageStoreDetails details) throws UnsupportedEncodingException {
        JmsConnectionFactory jmsConnectionFactory = new JmsConnectionFactory(details.getUrlString());
        jmsConnectionFactory.setUsername(details.getUsername());
        jmsConnectionFactory.setPassword(details.getPassword());
        jmsConnectionFactory.setClientID(clientId);
        return new CachingConnectionFactory(jmsConnectionFactory);
    }

    @Bean
    public JmsTemplate queuePublisher(ConnectionFactory jmsConnectionFactory) {
        JmsTemplate returnValue = new JmsTemplate();
        returnValue.setConnectionFactory(jmsConnectionFactory);
        return returnValue;
    }
}
