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
import com.sun.mdm.index.webservice.PersonBean;
import com.sun.mdm.index.webservice.PersonEJB;
import com.sun.mdm.index.webservice.SystemPerson;


public class TestRestEndpoint  extends CamelSpringTestSupport {

	private Customer person;

    @Produce(uri = "direct:callRestEndpoint") private ProducerTemplate restFactory;
    
	
    @Before
    public void setup() throws Exception {
    	
    	//build test object
    	//need some sort of wsdl object here that we can send
    	
        person= new Customer();
        
        person.setLastName("Williams");
        person.setFirstName("Mark");
        person.setActive(true);
        person.setCompanyName("Red Hat");
        person.setPhone("867-5309");
    }
    
    
	@Test
	public void testCamelRoute() throws Exception {
		
		
		System.out.print("\n\n\n\nTESTING\n\n\n\n\n");
		
		//send to direct:endpoint
		restFactory.sendBodyAndHeader(person, "type", "update");


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
