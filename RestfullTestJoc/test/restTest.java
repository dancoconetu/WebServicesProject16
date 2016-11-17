/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import HelpClasses.FlightInformationList;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.ClientBuilder;
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.WebResource;
//import javax.ws.rs.client.ClientR
import static org.junit.Assert.*;
import ws.lameduck.CreditCardDetails;
import ws.lameduck.ExpirationDate;

/**
 *
 * @author justinas
 */
public class restTest {
    
     Client client = ClientBuilder.newClient();
     WebTarget r = client.target("http://localhost:9090/TravelGoodsREST/itinerary");
     WebTarget r2 = client.target("http://localhost:9090/TravelGoodsREST/flights?departureCity=Riga&destinationCity=Madrid&date=2016-11-1%2014:00");
     
    
    public restTest() {
    }
    
//    @Test
//     public void testGetInstituteName() {
//      String result = r.path("name").request().get(String.class);
//      
//      assertEquals("DTU",result);
//     }
    private CreditCardDetails getCreditCardInformation(){
    CreditCardDetails CreditInfo = new CreditCardDetails();
        CreditInfo.setCardNumber("50408823");
        CreditInfo.setHoldersName("Tobiasen Inge");
          ExpirationDate ex = new ExpirationDate();
          ex.setMonth(9);
          ex.setYear(10);
        CreditInfo.setExpirationDate(ex);
        
        return CreditInfo;
    
    
    }
    //Creating itinerary, getting flights and placing flights into itinerary
     @Test
     public void testCreateItinary() {
      
                                                
         System.out.println("soo: " + r.request().post(null).readEntity(String.class));

         System.out.println("Output is: @@@ " + r2.request().get(String.class));
         FlightInformationList list = r2.request().get(FlightInformationList.class);
         
         //r.path("/1/flights").request().post(Entity.entity(list.getList().get(0),MediaType.TEXT_XML));
         System.out.println("output " + r.path("/1/flights").request().post(Entity.entity(list,MediaType.APPLICATION_XML)));
         
         
         r.path("/1").request().put(Entity.entity(getCreditCardInformation(),MediaType.APPLICATION_XML));
         
      assertEquals("1","1");
}
   
}
