package home.timjoo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MessageService{

    private static final Log log = LogFactory.getLog(MessageService.class);

    @Autowired
    private MessageRepository repository;

    @SecurityCheck
    @Transactional(noRollbackFor = { UnsupportedOperationException.class })
    public Message save(String msg){
        Message message = repository.saveMessage(new Message(msg));
        log.debug("New message[id="+ message.getId()+"] saved");
        updateStatistics();
        return message;
    }

    private void updateStatistics(){
        throw new UnsupportedOperationException("This method is not implemented yet");

    }
}