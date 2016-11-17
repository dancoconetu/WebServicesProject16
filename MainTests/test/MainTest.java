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
import javax.xml.datatype.XMLGregorianCalendar;
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

        String itineraryId= createItinerary("somethiung");
         System.out.println("Before");
            System.out.println("Itinerary: " + itineraryId );
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
            GetTravelHotelInput getTravelHotelInput = new GetTravelHotelInput();
            getTravelHotelInput.setItineraryId(itineraryId);
            getTravelHotelInput.setGetHotelInput(hinp);
              System.out.println("After1");
        
      //  System.out.println("Cancelation" +cancelPlan(itineraryId));
//        AddHotelToItineraryInput addHotelToItineraryInput = new AddHotelToItineraryInput();
//        addHotelToItineraryInput.setHotelType(null);
//        addHotelToItineraryInput.setItineraryId(itineraryId);
//            addHotelToItinerary(addHotelToItineraryInput);
//        
            System.out.println("hi");
       GetHotelOutput h = getHotels(getTravelHotelInput);
////          
//        System.out.println("After;");
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
        
        
        AddHotelToItineraryInput addHotelToItineraryInput = new AddHotelToItineraryInput();
        addHotelToItineraryInput.setItineraryId(itineraryId);
        addHotelToItineraryInput.setHotelType(hot.get(0));
        
       ItineraryStatus itineraryStatus =      addHotelToItinerary(addHotelToItineraryInput);
       addHotelToItineraryInput.setHotelType(hot.get(1));
       itineraryStatus =      addHotelToItinerary(addHotelToItineraryInput);
       
       HotelTypeWithStatus bla = itineraryStatus.getHotelArray().getHotelTypeWithStatus().get(0);
        System.out.println("Name : " + bla.getHotelName() + " Adress: " + bla.getAdress() + " BookingNR: " + bla.getBookingNR() + " TotalPrice: " + bla.getPrice() + " Reservation Service : " + bla.getReservationService() + "Booked status: "  + bla.getStatus());
           
       bla = itineraryStatus.getHotelArray().getHotelTypeWithStatus().get(1);
        System.out.println("Name : " + bla.getHotelName() + " Adress: " + bla.getAdress() + " BookingNR: " + bla.getBookingNR() + " TotalPrice: " + bla.getPrice() + " Reservation Service : " + bla.getReservationService() + "Booked status: "  + bla.getStatus());
           
        
        System.out.println("\n\n\n");
        
        
        GetTravelFlightInput getTravelFlightInput = new GetTravelFlightInput();
        getTravelFlightInput.setItineraryId(itineraryId);
        GetFlightsInput getFlightsInput = new GetFlightsInput();
        getFlightsInput.setStartAirport("Riga");
        getFlightsInput.setDestination("Madrid");
         GregorianCalendar x3 = new GregorianCalendar(2016, 11, 1, 14, 0, 0);
        
         XMLGregorianCalendar date3 = null;
        date3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(x3);
         getFlightsInput.setFlightDate(date3);
         getTravelFlightInput.setGetFlightsInput(getFlightsInput);
         
         
         GetFlightsOutput gfo = getFlights(getTravelFlightInput);
         
         for (FlightInformation flightInformation: gfo.getFlightInformation())
         {
             System.out.println("Flight Information: \n "  + flightInformation.getBookingNo() + "\n" + flightInformation.getFlight().getStartAirport() + "<->" + flightInformation.getFlight().getDestination());
         }
         
         AddFlightToItineraryInput addFlightToItineraryInput = new AddFlightToItineraryInput();
         addFlightToItineraryInput.setItineraryId(itineraryId);
         addFlightToItineraryInput.setFlightInformation(gfo.getFlightInformation().get(0));
         
         
         ItineraryStatus itineraryStatus2 = addFlightToItinerary(addFlightToItineraryInput);
         
        
          bla = itineraryStatus2.getHotelArray().getHotelTypeWithStatus().get(0);
        System.out.println("Name : " + bla.getHotelName() + " Adress: " + bla.getAdress() + " BookingNR: " + bla.getBookingNR() + " TotalPrice: " + bla.getPrice() + " Reservation Service : " + bla.getReservationService() + "Booked status: "  + bla.getStatus());
           
       bla = itineraryStatus2.getHotelArray().getHotelTypeWithStatus().get(1);
        System.out.println("Name : " + bla.getHotelName() + " Adress: " + bla.getAdress() + " BookingNR: " + bla.getBookingNR() + " TotalPrice: " + bla.getPrice() + " Reservation Service : " + bla.getReservationService() + "Booked status: "  + bla.getStatus());
           
        
        FlightInformationWithStatus flightInformation = itineraryStatus2.getFlightArray().getFlightInformationWithStatus().get(0);
          System.out.println("Flight Information: \n "  + flightInformation.getBookingNo() + "\n" + flightInformation.getFlight().getStartAirport() + "<->" + flightInformation.getFlight().getDestination() + "\nstatus:" + flightInformation.getStatus());
        
        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    private static GetHotelOutput getHotels(org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.GetTravelHotelInput input) {
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService service = new org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService();
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyPortType port = service.getTravelAgencyPortTypeBindingPort();
        return port.getHotels(input);
    }

    private static String createItinerary(java.lang.String itineraryRequest) {
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService service = new org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService();
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyPortType port = service.getTravelAgencyPortTypeBindingPort();
        return port.createItinerary(itineraryRequest);
    }

    private static boolean cancelPlan(java.lang.String request) {
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService service = new org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService();
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyPortType port = service.getTravelAgencyPortTypeBindingPort();
        return port.cancelPlan(request);
    }

    private static ItineraryStatus addHotelToItinerary(org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.AddHotelToItineraryInput addHotelToItineraryInput) {
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService service = new org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService();
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyPortType port = service.getTravelAgencyPortTypeBindingPort();
        return port.addHotelToItinerary(addHotelToItineraryInput);
    }

    private static ItineraryStatus addFlightToItinerary(org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.AddFlightToItineraryInput addFlightToItineraryInput) {
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService service = new org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService();
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyPortType port = service.getTravelAgencyPortTypeBindingPort();
        return port.addFlightToItinerary(addFlightToItineraryInput);
    }

    private static GetFlightsOutput getFlights(org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.GetTravelFlightInput input) {
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService service = new org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService();
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyPortType port = service.getTravelAgencyPortTypeBindingPort();
        return port.getFlights(input);
    }

    private static boolean bookItinerary(java.lang.String request) {
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService service = new org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService();
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyPortType port = service.getTravelAgencyPortTypeBindingPort();
        return port.bookItinerary(request);
    }


   

    
}
