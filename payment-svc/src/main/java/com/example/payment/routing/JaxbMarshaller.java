package com.example.payment.routing;

import javax.xml.bind.JAXBContext;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

import com.example.payment.models.Customer;

public class JaxbMarshaller extends RouteBuilder{

		@Override
		public void configure() throws Exception {
			
			//jaxb format needed as a paramter for camel
			JaxbDataFormat dataFormat = new JaxbDataFormat();
			dataFormat.setContext(JAXBContext.newInstance(Customer.class));
			
			
			
			from("direct:jaxbMarshaller")
			.marshal(dataFormat)
			.log("marshalled to ${body}")
			.unmarshal(dataFormat);
			
		}

	}
