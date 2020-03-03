package home.timjoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    public MessageService messageService;

    @GetMapping("/welcome")     
    public String welcome(Model model){
        String msg = "Hello, Welcome Message server with thymeleaf";
        model.addAttribute("message",msg);
        return "welcome";
    }

    @PostMapping("/message")
    @ResponseBody
    public ResponseEntity<Message> saveMessage(@RequestBody MessageData data){
        
        Message saved = messageService.save(data.getText());

        if ( saved == null ){
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(saved);
        
    }
}
