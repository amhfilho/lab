package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaPedidos {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("file:pedidos?noop=true").
					setProperty("pedidoId",xpath("/pedido/id/text()")).
					setProperty("clienteId",xpath("/pedido/pagamento/email-titular/text()")).
					log("${file:name}").
					split().xpath("/pedido/itens/item").
					//log("${body}").
					filter().xpath("/item/formato[text()='EBOOK']").
					setProperty("ebookId",xpath("/item/livro/codigo/text()")).
					marshal().xmljson().
					log("${body}").
					setHeader(Exchange.HTTP_QUERY, 
							simple("clienteId=${property.clienteId}&pedidoId=${property.pedidoId}&ebookId=${property.ebookId}")).
				to("http4://localhost:8080/webservices/ebook/item");
			}
			
		});
		
		context.start();
		Thread.sleep(5000);
		context.stop();

	}
	

}
