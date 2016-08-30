package com.example.payment.tools;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

public class ExtractCustomer{


	public void extractCustomer(@Body Exchange ex) throws Exception {
		
		System.out.println("\n\n\n\n\n\n\n\n\nTYPE == "+ex.getIn().getBody().getClass().toString()+"\n");
		
		MessageContentsList mcl = (MessageContentsList)ex.getIn().getBody();
		
		ex.getIn().setBody(mcl.get(0));
		
	}

}
