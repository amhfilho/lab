package com.amhfilho.wsdemo;

import org.springframework.oxm.XmlMappingException;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.core.SourceExtractor;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceMessageExtractor;
import org.springframework.ws.client.core.WebServiceOperations;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

public abstract class WebServiceAdapter implements WebServiceOperations {
    @Override
    public <T> T sendAndReceive(WebServiceMessageCallback webServiceMessageCallback, WebServiceMessageExtractor<T> webServiceMessageExtractor) throws WebServiceClientException {
        return null;
    }

    @Override
    public <T> T sendAndReceive(String s, WebServiceMessageCallback webServiceMessageCallback, WebServiceMessageExtractor<T> webServiceMessageExtractor) throws WebServiceClientException {
        return null;
    }

    @Override
    public boolean sendAndReceive(WebServiceMessageCallback webServiceMessageCallback, WebServiceMessageCallback webServiceMessageCallback1) throws WebServiceClientException {
        return false;
    }

    @Override
    public boolean sendAndReceive(String s, WebServiceMessageCallback webServiceMessageCallback, WebServiceMessageCallback webServiceMessageCallback1) throws WebServiceClientException {
        return false;
    }

    @Override
    public Object marshalSendAndReceive(Object o) throws XmlMappingException, WebServiceClientException {
        return null;
    }

    @Override
    public Object marshalSendAndReceive(String s, Object o) throws XmlMappingException, WebServiceClientException {
        return null;
    }

    @Override
    public Object marshalSendAndReceive(Object o, WebServiceMessageCallback webServiceMessageCallback) throws XmlMappingException, WebServiceClientException {
        return null;
    }

    @Override
    public Object marshalSendAndReceive(String s, Object o, WebServiceMessageCallback webServiceMessageCallback) throws XmlMappingException, WebServiceClientException {
        return null;
    }

    @Override
    public <T> T sendSourceAndReceive(Source source, SourceExtractor<T> sourceExtractor) throws WebServiceClientException {
        return null;
    }

    @Override
    public <T> T sendSourceAndReceive(String s, Source source, SourceExtractor<T> sourceExtractor) throws WebServiceClientException {
        return null;
    }

    @Override
    public <T> T sendSourceAndReceive(Source source, WebServiceMessageCallback webServiceMessageCallback, SourceExtractor<T> sourceExtractor) throws WebServiceClientException {
        return null;
    }

    @Override
    public <T> T sendSourceAndReceive(String s, Source source, WebServiceMessageCallback webServiceMessageCallback, SourceExtractor<T> sourceExtractor) throws WebServiceClientException {
        return null;
    }

    @Override
    public boolean sendSourceAndReceiveToResult(Source source, Result result) throws WebServiceClientException {
        return false;
    }

    @Override
    public boolean sendSourceAndReceiveToResult(String s, Source source, Result result) throws WebServiceClientException {
        return false;
    }

    @Override
    public boolean sendSourceAndReceiveToResult(Source source, WebServiceMessageCallback webServiceMessageCallback, Result result) throws WebServiceClientException {
        return false;
    }

    @Override
    public boolean sendSourceAndReceiveToResult(String s, Source source, WebServiceMessageCallback webServiceMessageCallback, Result result) throws WebServiceClientException {
        return false;
    }
}
