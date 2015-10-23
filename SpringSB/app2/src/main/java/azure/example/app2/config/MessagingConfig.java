package azure.example.app2.config;

import java.io.UnsupportedEncodingException;

import javax.jms.ConnectionFactory;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;

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
    public JmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory returnValue = new DefaultJmsListenerContainerFactory();
        returnValue.setConnectionFactory(connectionFactory);
        returnValue.setReceiveTimeout(-1L);
        return returnValue;
    }
}
