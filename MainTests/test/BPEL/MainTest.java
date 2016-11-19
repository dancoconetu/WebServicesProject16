/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BPEL;

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
     
        String itineraryId = "";
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
        //returns the Itinerary Id after it is created
         itineraryId= createItinerary("");
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void P1()
    {
        
    }
    
    
    @Test
    public void P2()
    {
        
    }
    
    
    @Test
    public void B()
    {
        
    }
    
    
    @Test
    public void C1()
    {
        
    }
    
    
    @Test
    public void C2()
    {
        
    }
    
    
    
    
    
        @Test
    public void testHotelListType() throws DatatypeConfigurationException  {

        
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
        
       GetItineraryStatusOutput itineraryStatus =      addHotelToItinerary(addHotelToItineraryInput);
       addHotelToItineraryInput.setHotelType(hot.get(1));
       itineraryStatus =      addHotelToItinerary(addHotelToItineraryInput);
       
       
       HotelTypeWithStatus bla = itineraryStatus.getItineraryStatus().getHotelArray().getHotelTypeWithStatus().get(0);
        System.out.println("Name : " + bla.getHotelName() + " Adress: " + bla.getAdress() + " BookingNR: " + bla.getBookingNR() + " TotalPrice: " + bla.getPrice() + " Reservation Service : " + bla.getReservationService() + "Booked status: "  + bla.getStatus());
           
       bla = itineraryStatus.getItineraryStatus().getHotelArray().getHotelTypeWithStatus().get(1);
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
         if (gfo.getFlightInformation()!=null)
         {
         for (FlightInformation flightInformation: gfo.getFlightInformation())
         {
             if (flightInformation!=null && flightInformation.getFlight()!=null)
             System.out.println("Flight Information: \n "  + flightInformation.getBookingNo() +
                     "\n" + flightInformation.getFlight().getStartAirport() + "<->" 
                     + flightInformation.getFlight().getDestination());
         }
         
         AddFlightToItineraryInput addFlightToItineraryInput = new AddFlightToItineraryInput();
         addFlightToItineraryInput.setItineraryId(itineraryId);
         addFlightToItineraryInput.setFlightInformation(gfo.getFlightInformation().get(0));
         
         
         GetItineraryStatusOutput itineraryStatus2 = addFlightToItinerary(addFlightToItineraryInput);
         
         if  (itineraryStatus2.isStatus()) {
        
          bla = itineraryStatus2.getItineraryStatus().getHotelArray().getHotelTypeWithStatus().get(0);
        System.out.println("Name : " + bla.getHotelName() + " Adress: " + bla.getAdress() + " BookingNR: " + bla.getBookingNR() + " TotalPrice: " + bla.getPrice() + " Reservation Service : " + bla.getReservationService() + "Booked status: "  + bla.getStatus());
           
       bla = itineraryStatus2.getItineraryStatus().getHotelArray().getHotelTypeWithStatus().get(1);
        System.out.println("Name : " + bla.getHotelName() + " Adress: " + bla.getAdress() + " BookingNR: " + bla.getBookingNR() + " TotalPrice: " + bla.getPrice() + " Reservation Service : " + bla.getReservationService() + "Booked status: "  + bla.getStatus());
           
        
        FlightInformationWithStatus flightInformation = itineraryStatus2.getItineraryStatus().getFlightArray().getFlightInformationWithStatus().get(0);
          System.out.println("Flight Information: \n "  + flightInformation.getBookingNo() + "\n" + flightInformation.getFlight().getStartAirport() + "<->" + flightInformation.getFlight().getDestination() + "\nstatus:" + flightInformation.getStatus());
         }
         else
         {
             System.out.println("Failed trying to add Flight");
         }
         }
         else
             System.out.println("Empty");
         
         BookItineraryInput bookItineraryInput = new BookItineraryInput();
         bookItineraryInput.setItineraryId(itineraryId);
         CreditCardDetails creditCardDetails = new CreditCardDetails();
         creditCardDetails.setCardNumber("50408824");
         creditCardDetails.setHoldersName("Tick Joachim");
         ExpirationDate expirationDate = new ExpirationDate();
         expirationDate.setMonth(2);
         expirationDate.setYear(11);
         creditCardDetails.setExpirationDate(expirationDate);
         bookItineraryInput.setCreditCardDetails(creditCardDetails);
         
         boolean booked = bookItinerary(bookItineraryInput);
         
         System.out.println("Booked: " + booked);
         
         GetItineraryStatusOutput itineraryStatus2 = getItineraryStatus(itineraryId);
         
         if  (itineraryStatus2.isStatus()) {
        
          bla = itineraryStatus2.getItineraryStatus().getHotelArray().getHotelTypeWithStatus().get(0);
        System.out.println("Name : " + bla.getHotelName() + " Adress: " + bla.getAdress() + " BookingNR: " + bla.getBookingNR() + " TotalPrice: " + bla.getPrice() + " Reservation Service : " + bla.getReservationService() + "Booked status: "  + bla.getStatus());
           
       bla = itineraryStatus2.getItineraryStatus().getHotelArray().getHotelTypeWithStatus().get(1);
        System.out.println("Name : " + bla.getHotelName() + " Adress: " + bla.getAdress() + " BookingNR: " + bla.getBookingNR() + " TotalPrice: " + bla.getPrice() + " Reservation Service : " + bla.getReservationService() + "Booked status: "  + bla.getStatus());
           
        
        FlightInformationWithStatus flightInformation = itineraryStatus2.getItineraryStatus().getFlightArray().getFlightInformationWithStatus().get(0);
          System.out.println("Flight Information: \n "  + flightInformation.getBookingNo() + "\n" + flightInformation.getFlight().getStartAirport() + "<->" + flightInformation.getFlight().getDestination() + "\nstatus:" + flightInformation.getStatus());
         }
         
         
         System.out.println("\nTrying to book second time: " +  bookItinerary(bookItineraryInput));
         
            CancelItineraryInput cancelItineraryInput = new CancelItineraryInput();
            cancelItineraryInput.setItineraryId(itineraryId);
            cancelItineraryInput.setCreditCardDetails(creditCardDetails);
            
            System.out.println("Canceled: " + cancelItinerary(cancelItineraryInput));
            
            itineraryStatus2 = getItineraryStatus(itineraryId);
         
         if  (itineraryStatus2.isStatus()) {
        
          bla = itineraryStatus2.getItineraryStatus().getHotelArray().getHotelTypeWithStatus().get(0);
        System.out.println("Name : " + bla.getHotelName() + " Adress: " + bla.getAdress() + " BookingNR: " + bla.getBookingNR() + " TotalPrice: " + bla.getPrice() + " Reservation Service : " + bla.getReservationService() + "Booked status: "  + bla.getStatus());
           
       bla = itineraryStatus2.getItineraryStatus().getHotelArray().getHotelTypeWithStatus().get(1);
        System.out.println("Name : " + bla.getHotelName() + " Adress: " + bla.getAdress() + " BookingNR: " + bla.getBookingNR() + " TotalPrice: " + bla.getPrice() + " Reservation Service : " + bla.getReservationService() + "Booked status: "  + bla.getStatus());
           
         }
         
        
         System.out.println("\nTrying to book third time: " +  bookItinerary(bookItineraryInput));
         
    }
    
    
    
    
    

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

    

    private static GetFlightsOutput getFlights(org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.GetTravelFlightInput input) {
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService service = new org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService();
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyPortType port = service.getTravelAgencyPortTypeBindingPort();
        return port.getFlights(input);
    }


    private static GetItineraryStatusOutput addFlightToItinerary(org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.AddFlightToItineraryInput addFlightToItineraryInput) {
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService service = new org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService();
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyPortType port = service.getTravelAgencyPortTypeBindingPort();
        return port.addFlightToItinerary(addFlightToItineraryInput);
    }

    private static GetItineraryStatusOutput addHotelToItinerary(org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.AddHotelToItineraryInput addHotelToItineraryInput) {
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService service = new org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService();
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyPortType port = service.getTravelAgencyPortTypeBindingPort();
        return port.addHotelToItinerary(addHotelToItineraryInput);
    }

    private static boolean bookItinerary(org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.BookItineraryInput request) {
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService service = new org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService();
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyPortType port = service.getTravelAgencyPortTypeBindingPort();
        return port.bookItinerary(request);
    }

    private static GetItineraryStatusOutput getItineraryStatus(java.lang.String request) {
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService service = new org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService();
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyPortType port = service.getTravelAgencyPortTypeBindingPort();
        return port.getItineraryStatus(request);
    }

    private static boolean cancelItinerary(org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.CancelItineraryInput request) {
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService service = new org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyService();
        org.netbeans.j2ee.wsdl.travelagencysoapbpel.src.travelagency.TravelAgencyPortType port = service.getTravelAgencyPortTypeBindingPort();
        return port.cancelItinerary(request);
    }

   

   

    
}
