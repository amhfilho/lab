package com.amhfilho.wsdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceOperations;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class WsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(WsConfiguration.class.getName());


    @Bean
    public Jaxb2Marshaller marshaller(){
        log.info("Creating marshaller");
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.amhfilho.wsdemo.schema");
        return marshaller;
    }


    @Bean
    public WebServiceOperations webService(Jaxb2Marshaller marshaller){
        WebServiceTemplate template = new WebServiceTemplate();
        template.setMarshaller(marshaller);
        template.setUnmarshaller(marshaller);
        return template;
    }


}
