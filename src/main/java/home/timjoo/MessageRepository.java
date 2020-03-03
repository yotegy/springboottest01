package home.timjoo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Component;

@Component
public class MessageRepository {
    private final static Log log = LogFactory.getLog(MessageRepository.class);
    
    private SessionFactory sessionFactory;
    public MessageRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    
    public Message saveMessage(Message message){
        Session session = sessionFactory.getCurrentSession();
        session.save(message);
        return message;        
    }
}