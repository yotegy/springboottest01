package home.timjoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageService{

    @Autowired
    private MessageRepository repository;

    // @Autowired
    // public MessageService(MessageRepository repository){
    //     this.repository = repository;
    // }

    // @Autowired
    // public void setRepository(MessageRepository repository){
    //     this.repository = repository;
    // }

    public void save(String msg){
        this.repository.saveMessage(new Message(msg));
    }
}