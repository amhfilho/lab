package com.amhfilho.wsdemo;

import com.amhfilho.wsdemo.schema.*;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceOperations;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import javax.validation.constraints.NotNull;

@Service
public class DefaultCalculatorClient implements CalculatorClient {

    public static final String ENDPOINT_URL = "http://www.dneonline.com/calculator.asmx";

    private WebServiceOperations webService;

    private static final String DEFAULT_NAMESPACE = "http://tempuri.org/";
    private static final String ADD_NAMESPACE = "Add";
    private static final String SUBTRACT_NAMESPACE = "Subtract";
    private static final String MULTIPLY_NAMESPACE = "Multiply";
    private static final String DIVIDE_NAMESPACE = "Divide";

    @Autowired
    public DefaultCalculatorClient(@NotNull WebServiceOperations webService){
        this.webService = webService;
        ((WebServiceTemplate)this.webService).setMessageSender(messageSender());
    }


    public AddResponse add(Add request) {
        return (AddResponse) webService.marshalSendAndReceive(
                ENDPOINT_URL,
                request,
                new SoapActionCallback(DEFAULT_NAMESPACE + ADD_NAMESPACE));
    }

    public SubtractResponse subtract(Subtract request) {
        return (SubtractResponse) webService.marshalSendAndReceive(
                ENDPOINT_URL,
                request,
                new SoapActionCallback(DEFAULT_NAMESPACE + SUBTRACT_NAMESPACE));
    }

    public MultiplyResponse multiply(Multiply request) {
        return (MultiplyResponse) webService.marshalSendAndReceive(
                ENDPOINT_URL,
                request,
                new SoapActionCallback(DEFAULT_NAMESPACE + MULTIPLY_NAMESPACE));
    }

    public DivideResponse divide(Divide request) {
        return (DivideResponse) webService.marshalSendAndReceive(
                ENDPOINT_URL,
                request,
                new SoapActionCallback(DEFAULT_NAMESPACE + DIVIDE_NAMESPACE));
    }

    private HttpComponentsMessageSender messageSender(){
        RequestConfig config = RequestConfig
                .custom()
                .setProxy(new HttpHost("hostname",9999))
                //.setAuthenticationEnabled(true)
                .build();

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope("hostname", 9999),
                new UsernamePasswordCredentials("username", "password"));


        CloseableHttpClient client = HttpClients
                .custom()
                .addInterceptorFirst(new HttpComponentsMessageSender.RemoveSoapHeadersInterceptor())
                .setDefaultCredentialsProvider(credsProvider)
                .setDefaultRequestConfig(config)
                .build();

        return new HttpComponentsMessageSender(client);

    }
}
