package com.example.payment.service;

import javax.ws.rs.*;

import com.example.payment.models.Customer;




@Path("/PersonEJB")
public interface CustomerRest {

	 @POST @Path("/addCustomer") @Consumes("application/json") @Produces("application/json")
	String addCustomer(Customer toAdd);

}
