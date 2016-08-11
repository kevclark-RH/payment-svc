package org.payment.svc;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.test.spring.CamelSpringTestSupport;

import com.payment.models.Customer;
import com.sun.mdm.index.webservice.AddSystemRecord;
import com.sun.mdm.index.webservice.PersonBean;
import com.sun.mdm.index.webservice.Search;
import com.sun.mdm.index.webservice.SystemPerson;
import com.sun.mdm.index.webservice.UpdateSystemRecord;

import org.junit.Before;
import org.junit.Test;


public class TestDirect extends CamelSpringTestSupport{

	private Customer testCustomer;
	//@EndpointInject(uri = "direct:callRestEndpoint") private MockEndpoint resultEndpoint;

    @Produce(uri = "direct:explicitCall") private ProducerTemplate callFactory;
    
	
    @Before
    public void setup() throws Exception {
    	
    	//build test object
    	testCustomer = new Customer();
    	testCustomer.setFirstName("Andy");
    	testCustomer.setLastName("Reed");
    	testCustomer.setPhone("3847474789");
    	testCustomer.setState("Arizona");
    	testCustomer.setRegion("SW");
    	testCustomer.setZip("44747");
    	testCustomer.setCompanyName("AMEX");
    

        
    }
    
    
	@Test
	public void testCamelRoute() throws Exception {
		
		
		System.out.print("\n\n\n\nTESTING\n\n\n\n\n");
		
		//send to direct:endpoint
		callFactory.sendBody(null);


		//dummy assert
		assertTrue(true);
	
		
	}



	@Override
	protected AbstractApplicationContext createApplicationContext() {
		// TODO Auto-generated method stub
		AbstractXmlApplicationContext springCtx	=
				new ClassPathXmlApplicationContext("META-INF/spring/bundle-context.xml");
		return springCtx;
	}

	
}
