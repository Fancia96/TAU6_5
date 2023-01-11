package org.example;

public class TemplateEngine {

    public String prepareMessage(Template template, Client client){
        System.out.println(client.getEmail());
        System.out.println(template.getTemplate());
        return template.getTemplate() + "" + client.getEmail();
    }
}
