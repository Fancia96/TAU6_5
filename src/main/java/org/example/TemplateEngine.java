package org.example;

import java.util.zip.DataFormatException;

public class TemplateEngine {

    public String template_engine;
    public String prepareMessage(Template template, Client client){
        if(client.getEmail() == null || template.getTemplate() == null){
            template_engine = null;
        }
        else {
            template_engine = template.getTemplate() + " " + client.getEmail();
        }

        return template_engine;
    }
}
