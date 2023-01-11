package org.example;

public class Messenger {
    private TemplateEngine templateEngine;
    private MailServer mailServer;

    public Messenger(MailServer mailServer, TemplateEngine templateEngine){
        this.mailServer = mailServer;
        this.templateEngine = templateEngine;
    }

    public void sendMessage(Client client, Template template){
        System.out.println(client.getEmail());
        System.out.println(template.getTemplate());
        String msgContent = templateEngine.prepareMessage(template, client);
        System.out.println(msgContent);
        mailServer.send(client.getEmail(), msgContent);
    }

}