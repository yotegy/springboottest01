package home.timjoo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/messages")
public class MessageController {
    @GetMapping("/welcome")     
    public String welcome(Model model){
        String msg = "Hello, Welcome Message server with thymeleaf";
        model.addAttribute("message",msg);
        return "welcome";
    }
}