package com.amhfilho.wsdemo;

import com.amhfilho.wsdemo.schema.AddResponse;
import org.springframework.oxm.XmlMappingException;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.core.WebServiceMessageCallback;

public class WebServiceMock extends WebServiceAdapter {
    @Override
    public Object marshalSendAndReceive(String s, Object o, WebServiceMessageCallback webServiceMessageCallback)
            throws XmlMappingException, WebServiceClientException {
        AddResponse response = new AddResponse();
        response.setAddResult(30);
        return response;
    }
}
