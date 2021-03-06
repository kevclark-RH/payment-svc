package com.payment.service;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

import com.payment.models.Customer;
import com.sun.mdm.index.webservice.AddOrUpdateSystemRecord;
import com.sun.mdm.index.webservice.AddSystemRecord;
import com.sun.mdm.index.webservice.Search;
import com.sun.mdm.index.webservice.UpdateSystemRecord;

public class CustomerRestImpl implements CustomerRest{

	
	@Produce(uri = "direct:uploadToQueue")
	private ProducerTemplate template;
	
	@Override
	public String addCustomer(Customer toAdd) {
		// TODO Auto-generated method stub
		
		
		//template.sendBody(addRequest);
		
		
		System.out.println("\n**********\nADD REST METHOD CALLED\n**********\n");
		return "";
	}
	
	@Override
	public String updateCustomer(Customer toUpdate) {
		// TODO Auto-generated method stub
		

		//template.sendBody(updateRequest);
		
		
		System.out.println("\n**********\nUPDATE REST METHOD CALLED\n**********\n");
		return "";
	}

	@Override
	public String searchCustomer(Customer toAdd) {
		// TODO Auto-generated method stub
		

		//template.sendBody(searchRequest);
		
		System.out.println("\n**********\nSEARCH REST METHOD CALLED\n**********\n");
		return "";
	}
	

}
