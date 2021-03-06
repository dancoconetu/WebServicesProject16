/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.lameduck.GetFlightsOutput;
import ws.lameduck.*;

/**
 *
 * @author justinas
 */
public class ClientTester {
    
    public ClientTester() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    //Expecting just one flight
      @Test
    public void testGetFlightsOutput() throws DatatypeConfigurationException{
    int a = 0;
        GetFlightsInput gfi = new GetFlightsInput();
        gfi.setDestination("Madrid");
        gfi.setStartAirport("Riga");
         GregorianCalendar x = new GregorianCalendar(2016, 11, 1, 14, 0, 0);
        XMLGregorianCalendar date2 = null;
        date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(x);
        gfi.setFlightDate(date2);
          
    List<FlightInformation> list = new ArrayList<FlightInformation>();
          list = getFlights(gfi).getFlightInformation();
          
          for(FlightInformation result: list){
               a= result.getPrice();
               
               System.out.println("sooo: " + result.getNameAirline());
               System.out.println("Price: " + a);
}
    
    
    assertEquals(1,list.size());
    }
    
    //Test having enough money on the account.
      @Test
    public void testbookValidFlight() throws DatatypeConfigurationException, BookFlightFaultMessage{
     
        BookFlightInput gfi = new BookFlightInput();
        gfi.setBookingNo("2");
        CreditCardDetails CreditInfo = new CreditCardDetails();
        CreditInfo.setCardNumber("50408823");
        CreditInfo.setHoldersName("Tobiasen Inge");
          ExpirationDate ex = new ExpirationDate();
          ex.setMonth(9);
          ex.setYear(10);
        CreditInfo.setExpirationDate(ex);
        gfi.setCreditCardDetails(CreditInfo);
          
        System.out.println(gfi.getCreditCardDetails().getExpirationDate().getMonth());
        assertEquals(true,bookFlight(gfi));
    }
    
    //Test having NOT enough money on the account. EXPECTING ERROR
      @Test
    public void testbookInValidFlight() throws DatatypeConfigurationException, BookFlightFaultMessage{
     
        BookFlightInput gfi = new BookFlightInput();
        gfi.setBookingNo("1");
        CreditCardDetails CreditInfo = new CreditCardDetails();
        CreditInfo.setCardNumber("50408823");
        CreditInfo.setHoldersName("Tobiasen Inge");
          ExpirationDate ex = new ExpirationDate();
          ex.setMonth(9);
          ex.setYear(10);
        CreditInfo.setExpirationDate(ex);
        gfi.setCreditCardDetails(CreditInfo);
          
        System.out.println(gfi.getCreditCardDetails().getExpirationDate().getMonth());
        assertEquals("Expecting an error",bookFlight(gfi));
    }
    

    

    private static GetFlightsOutput getFlights(ws.lameduck.GetFlightsInput getFlightsInput) {
        ws.lameduck.LameDuckService service = new ws.lameduck.LameDuckService();
        ws.lameduck.LameDuckPortType port = service.getLameDuckPortTypeBindingPort();
        return port.getFlights(getFlightsInput);
    }

    private static boolean bookFlight(ws.lameduck.BookFlightInput input) throws BookFlightFaultMessage {
        ws.lameduck.LameDuckService service = new ws.lameduck.LameDuckService();
        ws.lameduck.LameDuckPortType port = service.getLameDuckPortTypeBindingPort();
        return port.bookFlight(input);
    }

}
