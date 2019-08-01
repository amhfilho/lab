package com.amhfilho.wsdemo;

import com.amhfilho.wsdemo.schema.*;

public interface CalculatorClient {

    AddResponse add(Add request);

    SubtractResponse subtract(Subtract request);

    MultiplyResponse multiply(Multiply request);

    DivideResponse divide(Divide divide);
}
