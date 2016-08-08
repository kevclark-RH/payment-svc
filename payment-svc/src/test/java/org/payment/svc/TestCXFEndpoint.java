package org.payment.svc;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.mdm.index.webservice.PersonEJB;


public class TestCXFEndpoint  extends CamelSpringTestSupport {

	private PersonEJB testPerson;

    //@Produce(uri = "direct:callCXFEndpointCXF") private ProducerTemplate cxfFactory;
    
	
    @Before
    public void setup() throws Exception {
    	
    	//build test object
    	//need some sort of wsdl object here that we can send
    	
//    	testPerson = new PersonEJB();
//    	
//        person= new PersonBean();
//        sysPerson = new SystemPerson();
//        
//        person.setLastName("Williams");
//        person.setFirstName("Mark");
//        person.setVetStatus("yes");
//        person.setBirthOrder("2");
//        person.setAuthFlag("no");
//        person.setDegree("bachelors");
//        person.setGender("male");
//        
//        sysPerson.setPerson(person);
//        sysPerson.setStatus("CRITICAL");
//        sysPerson.setSystemCode("LZJFDK");
//        sysPerson.setCreateUser("yes");
//        sysPerson.setLocalId("1238DFT324");
//        
//        exMatch.setSysObjBean(sysPerson);
    }
    
    
	@Test
	public void testCamelRoute() throws Exception {
		
		
		System.out.print("\n\n\n\nTESTING\n\n\n\n\n");
		
		//send to direct:endpoint
		//soapFactory.sendBody(testCustomer);


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
