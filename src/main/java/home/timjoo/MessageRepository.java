package home.timjoo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageRepository {
    private final static Log log = LogFactory.getLog(MessageRepository.class);
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    };

    public Message saveMessage(Message message){

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("text", message.getText());
        params.addValue("created_date", message.getCreatedDate());
        String insertSQL = "INSERT INTO messages (`id`,`text`,`created_date`) VALUE (null, :text, :created_date)";
        try{
            this.jdbcTemplate.update(insertSQL,params,holder);
        } catch (DataAccessException ex){
            log.error("Failed to save message",ex);
            return null;
        } 
        return new Message(holder.getKey().intValue(),message.getText(),message.getCreatedDate());
    }
}