package org.example;

public class MailServer {

    public boolean messageSent = false;
    public void send(String email, String content) {
        messageSent = true;
    }

    public boolean isMessageSent(){
        return messageSent;
    }

    public void setMessageSent(boolean msg){
        messageSent = msg;
    }
}
