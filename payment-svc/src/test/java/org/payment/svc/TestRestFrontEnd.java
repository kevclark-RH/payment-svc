package org.payment.svc;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.payment.models.Customer;

public class TestRestFrontEnd  extends CamelSpringTestSupport {

	private Customer testCustomer;

    @Produce(uri = "direct:callRestFrontEnd") private ProducerTemplate restFactory;
    
	
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
    }
    
    
	@Test
	public void testCamelRoute() throws Exception {
		
		
		System.out.print("\n\n\n\nTESTING\n\n\n\n\n");
		
		
		//send to direct:endpoint
		restFactory.sendBodyAndHeader(testCustomer, "type", "search");


		//dummy assert
		assertTrue(true);
	
		
	}



	@Override
	protected AbstractApplicationContext createApplicationContext() {
		AbstractXmlApplicationContext springCtx	=
				new ClassPathXmlApplicationContext("META-INF/spring/bundle-context.xml");
		return springCtx;
	}
	
}
