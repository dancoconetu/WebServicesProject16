/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.GetHotelOutput;
import org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.*;
/**
 *
 * @author Dan
 */
public class MainTest {
    
    public MainTest() {
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

    
        @Test
    public void testHotelListType() throws DatatypeConfigurationException  {

        GetHotelInput hinp = new GetHotelInput();  
        hinp = new GetHotelInput();
        hinp.setCity("Copenhagen");
        
        GregorianCalendar x = new GregorianCalendar(2016, 11, 1, 14, 0, 0);
        XMLGregorianCalendar date1 = null;
        date1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(x);
        hinp.setArrival(date1);

        GregorianCalendar x2 = new GregorianCalendar(2016, 11, 5, 14, 0, 0);
        XMLGregorianCalendar date2 = null;
        date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(x2);
        hinp.setDeparture(date2);

        GetHotelOutput h = getHotels(hinp);
//               
        List<HotelType> hot = h.getHotelType();

        if (hot.size() > 0) {
            System.out.println("List of hotels in : " + hinp.getCity());

            for (HotelType bla : hot) {
                String creditCardNeeded = "Not needed";
                if (bla.isCreditCardNeeded() == true) {
                    creditCardNeeded = "Needed";
                }
                System.out.println("Name : " + bla.getHotelName() + " Adress: " + bla.getAdress() + " BookingNR: " + bla.getBookingNR() + " TotalPrice: " + bla.getPrice() + " CreditCard is : " + creditCardNeeded + " Reservation Service : " + bla.getReservationService());
            }
        } else {
            System.out.println("No hotel found in : " + hinp.getCity());
        }

    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    private static GetHotelOutput getHotels(org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.GetHotelInput request) {
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService service = new org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService();
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyPortType port = service.getTravelAgencyPortTypeBindingPort();
        return port.getHotels(request);
    }
}
