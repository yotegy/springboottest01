package home.timjoo;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public FilterRegistrationBean<AuditingFilter> auditingFilterRegistrationBean(){
        FilterRegistrationBean<AuditingFilter> registration = new FilterRegistrationBean<>();
        
        AuditingFilter filter = new AuditingFilter();
        registration.setFilter(filter);
        registration.setOrder(Integer.MAX_VALUE);
        registration.setUrlPatterns(Arrays.asList("/messages/*"));

        return registration;
    }

}