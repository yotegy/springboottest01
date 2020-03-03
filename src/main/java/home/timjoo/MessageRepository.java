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
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

@Component
public class MessageRepository {
    private final static Log log = LogFactory.getLog(MessageRepository.class);
    
    private DataSource dataSource;

    public MessageRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Message saveMessage(Message message){
        Connection conn = DataSourceUtils.getConnection(dataSource);
        try {
            String insertSQL = "INSERT INTO messages (`id`,`text`,`created_date`) VALUE (null, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,message.getText());
            ps.setTimestamp(2,new Timestamp(message.getCreatedDate().getTime()));
            int rowsAffected = ps.executeUpdate();

            if(rowsAffected >0 ){
                ResultSet result = ps.getGeneratedKeys();
                if(result.next()){
                    int id = result.getInt(1);
                    return new Message(id, message.getText(), message.getCreatedDate());
                } else {
                    log.error("Failed to retrieve id, No row in result");
                    return null;
                }
            } else {
                log.error("Failed to insert message into DB");
                return null;
            }
        }catch (SQLException ex){
            log.error("Failed to save message",ex);
            try{
                conn.close();
            }catch(SQLException e){
                log.error("Failed to close connection",ex);
            }
        } finally {
            DataSourceUtils.releaseConnection(conn, dataSource);
        }
        return null;
    }
}