package org.amexp.payment.test;


import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Properties;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.example.payment.models.Customer;


@PropertySource("classpath:payment_test.properties")
public class TestRestFrontEnd extends CamelSpringTestSupport{

	public Customer testCustomer;
 
  
    @Produce(uri = "direct:sendToRest") protected ProducerTemplate restFactory;
	
    @Before
    public void setup() throws Exception {
    	
    	//build test object
    	//need some sort of wsdl object here that we can send
    	
    	testCustomer = new Customer();
    	testCustomer.setFirstName("Andy");
    	testCustomer.setLastName("Reed");
    	testCustomer.setPhone("3847474789");
    	testCustomer.setState("CA");
    	testCustomer.setRegion("SW");
    	testCustomer.setZip("44747");
    	testCustomer.setCompanyName("REALgrillz");
    	testCustomer.setStreetAddr("666 Rowdon Ln.");
    	testCustomer.setCity("San Francisco");
    	
    	System.out.println("\n\nSTARTING TESTING\n\n");
    	
    	
    }
    
	@Test
	public void testCamelRoute() throws Exception {
		
		
		System.out.print("\n\n\n\nRUNNING testCamelRoute() \n\n\n\n\n");
		Map<String, Object> headers =new HashMap<String, Object>();
		
		/**
		 
		headers.put("Accept", "application/json");
		headers.put("CamelHTTPMethod", "POST");
		headers.put("CamelCxfRsUsingHttpAPI", true);
		headers.put("CamelHTTPPath", "/PersonEJB/addCustomer");
		
		
		
		restFactory.sendBodyAndHeaders(testCustomer, headers);
		**/
		restFactory.sendBody(testCustomer);

		
		//dummy assert
		assertTrue(true);
	
		
	}


	@Override
	protected AbstractApplicationContext createApplicationContext() {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:META-INF/spring/bundle-context.xml");
	    ConfigurableEnvironment env = ctx.getEnvironment();
	    env.setActiveProfiles("test");
	    ctx.refresh();
		
		return ctx;
	
	}
	


}


