package com.payment.service;

import javax.ws.rs.*;

import com.payment.models.Customer;
import com.sun.mdm.index.webservice.AddOrUpdateSystemRecord;
import com.sun.mdm.index.webservice.AddSystemRecord;
import com.sun.mdm.index.webservice.Search;
import com.sun.mdm.index.webservice.UpdateSystemRecord;
import com.sun.mdm.index.webservice.AddOrUpdateSystemRecord;
import com.sun.mdm.index.webservice.AddOrUpdateSystemRecord;
import com.sun.mdm.index.webservice.AddOrUpdateSystemRecord;


@Path("/PersonEJB/")
public interface CustomerRest {

	 @POST @Path("/addCustomer") @Consumes("application/json")
	String addCustomer(Customer toAdd);

	 @POST @Path("/updateSystemRecordRequest") @Consumes("application/json")
	String updateCustomer(Customer toUpdate);
	 
	 @POST @Path("/searchRequest") @Consumes("application/json")
	String searchCustomer(Customer toSearchFor);
}
