package com.example.payment.service;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

import com.example.payment.models.Customer;


public class CustomerRestImpl implements CustomerRest{

	
	@Produce(uri = "direct:uploadToQueue")
	private ProducerTemplate template;
	
	public String addCustomer(Customer toAdd) {
		// TODO Auto-generated method stub
		
		
		//template.sendBody(addRequest);
		
		
		System.out.println("\n**********\nADD REST METHOD CALLED\n**********\n");
		return toAdd.toString();
		
	}
	
	public String updateCustomer(Customer toUpdate) {
		// TODO Auto-generated method stub
		

		//template.sendBody(updateRequest);
		
		
		System.out.println("\n**********\nUPDATE REST METHOD CALLED\n**********\n");
		return "";
	}

	public String searchCustomer(Customer toAdd) {
		// TODO Auto-generated method stub
		

		//template.sendBody(searchRequest);
		
		System.out.println("\n**********\nSEARCH REST METHOD CALLED\n**********\n");
		return "";
	}
	

}
