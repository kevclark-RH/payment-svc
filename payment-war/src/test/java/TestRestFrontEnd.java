


import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.junit4.TestSupport;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext.HierarchyMode;
import org.springframework.test.context.*;
import com.amexp.payment.models.Customer;


public class TestRestFrontEnd extends CamelSpringTestSupport{

	private Customer testCustomer;
 
  
    @Produce(uri = "cxfrs:bean:customerRestServiceClient") protected ProducerTemplate restFactory;
	
    @Before
    public void setup() throws Exception {
    	
    	//build test object
    	//need some sort of wsdl object here that we can send
    	
    	testCustomer = new Customer();
    	testCustomer.setFirstName("Andy");
    	testCustomer.setLastName("Reed");
    	testCustomer.setPhone("3847474789");
    	testCustomer.setState("Arizona");
    	testCustomer.setRegion("SW");
    	testCustomer.setZip("44747");
    	testCustomer.setCompanyName("AMEX");
    	
    	System.out.println("\n\nSTARTING TESTING\n\n");
    	
    	
    }
    
    
	@Test
	public void testCamelRoute() throws Exception {
		
		
		System.out.print("\n\n\n\nRUNNING testCamelRoute() \n\n\n\n\n");
		Map<String, Object> headers =new HashMap<String, Object>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");
		headers.put("CamelHTTPMethod", "POST");
		headers.put("CamelCxfRsUsingHttpAPI", true);
		headers.put("CamelHTTPPath", "/PersonEJB/addCustomer");
		
		
		
		restFactory.sendBodyAndHeaders(testCustomer, headers);


		
		//dummy assert
		assertTrue(true);
	
		
	}


	@Override
	protected AbstractApplicationContext createApplicationContext() {
		// TODO Auto-generated method stub
		return new ClassPathXmlApplicationContext("classpath:WEB-INF/web.xml");
	}

}


