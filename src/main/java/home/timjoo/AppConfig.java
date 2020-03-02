package home.timjoo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("home.timjoo")
public class AppConfig {
    
    // @Bean
    // public MessageRepository messageRepository() {
    //     return new MessageRepository();
    // }

    // @Bean
    // public MessageService messageService() {
    //     return new MessageService(messageRepository());
    // }

}