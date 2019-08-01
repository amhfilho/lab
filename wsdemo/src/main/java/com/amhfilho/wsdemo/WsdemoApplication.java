package com.amhfilho.wsdemo;

import com.amhfilho.wsdemo.schema.Add;
import com.amhfilho.wsdemo.schema.AddResponse;
import com.amhfilho.wsdemo.schema.Subtract;
import com.amhfilho.wsdemo.schema.SubtractResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WsdemoApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(WsdemoApplication.class.getName());

	@Autowired
	private CalculatorClient client;


	public static void main(String[] args) {
		SpringApplication.run(WsdemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Running WsdemoApplication...");
		log.info("Calling Add operation: 2 + 3...");
		AddResponse response = client.add(add(2,3));
		log.info("AddResponse = {}", response.getAddResult());

		log.info("Calling Subtract operation: 10 - 7...");
		SubtractResponse sResponse = client.subtract(subtract(10,7));
		log.info("SubtractResponse = {}", sResponse.getSubtractResult());
	}

	private Add add(int a, int b) {
		Add request = new Add();
		request.setIntA(a);
		request.setIntB(b);
		return request;
	}

	private Subtract subtract(int a, int b){
		Subtract request = new Subtract();
		request.setIntA(a);
		request.setIntB(b);
		return request;
	}
}

