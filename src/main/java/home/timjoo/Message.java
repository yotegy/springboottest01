package home.timjoo;

import java.util.Date;

public class Message {
    private Integer id;
    private String text;
    private Date created_date; 

    public Message(String text){
        this.text = text;
        this.created_date = new Date();
    }

    public Message(int id, String text, Date createdDate){
        this.id =id;
        this.text = text;
        this.created_date = createdDate;
    }

    public Integer getId(){return id;}
    public String getText(){ return text;}
    public Date getCreatedDate(){ return created_date;}
}