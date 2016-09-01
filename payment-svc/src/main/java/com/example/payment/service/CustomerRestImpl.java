package com.example.payment.service;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

import com.example.payment.models.Customer;

import io.swagger.annotations.Api;

@Api(value="/")
public class CustomerRestImpl implements CustomerRest{

	
	@Produce(uri = "direct:uploadToQueue")
	private ProducerTemplate template;
	
	
	public Customer addCustomer(Customer toAdd) {
		// TODO Auto-generated method stub
		
		
		//template.sendBody(addRequest);
		
		
		
		System.out.println("\n**********\nADD REST METHOD CALLED\n**********\n");
		return toAdd;
		
	}
	

}
