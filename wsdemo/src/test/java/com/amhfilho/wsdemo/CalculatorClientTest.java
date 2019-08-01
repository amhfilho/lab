package com.amhfilho.wsdemo;

import com.amhfilho.wsdemo.schema.Add;
import com.amhfilho.wsdemo.schema.AddResponse;
import org.junit.Test;
import org.springframework.ws.client.core.WebServiceOperations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculatorClientTest {

    @Test
    public void shouldReturn10plus20(){
        WebServiceOperations mockWebService = new WebServiceMock();
        DefaultCalculatorClient client = new DefaultCalculatorClient(mockWebService);
        AddResponse response = client.add(new Add());
        assertEquals(30, response.getAddResult());
    }

    @Test
    public void shouldReturn5Plus6UsingMockito(){
        WebServiceOperations mockWebService = mock(WebServiceOperations.class);
        AddResponse mockResponse = new AddResponse();
        mockResponse.setAddResult(11);
        when(mockWebService.marshalSendAndReceive(anyString(),any(), any()))
                .thenReturn(mockResponse);

        DefaultCalculatorClient client = new DefaultCalculatorClient(mockWebService);
        AddResponse response = client.add(new Add());
        assertEquals(11, response.getAddResult());
    }

}
