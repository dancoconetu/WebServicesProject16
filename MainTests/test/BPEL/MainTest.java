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
       
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void P1() throws DatatypeConfigurationException
    {
      
        
    //1.Plan a trip by first planning a flight (i.e. getting a list of flights and then 
    //new itineraryId
    String  itineraryId= createItinerary("");
    //new TravelFlightInput so we can get all flights
    GetTravelFlightInput getTravelFlightInput = new GetTravelFlightInput();
    getTravelFlightInput.setItineraryId(itineraryId);  
    //flightinput for the getTravelFLightinput.
    GetFlightsInput getFlightsInput = new GetFlightsInput();
    getFlightsInput.setStartAirport("Riga");
    getFlightsInput.setDestination("Madrid");  
    //set the Gregoriandates to gregorian xml format and add date to getflightsinput
    GregorianCalendar x3 = new GregorianCalendar(2017, 11, 1, 14, 0, 0);
    XMLGregorianCalendar date3 = null;
    date3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(x3);
    //add the getflightsInput to the getTravelFlightInput 
    getFlightsInput.setFlightDate(date3);
    getTravelFlightInput.setGetFlightsInput(getFlightsInput);
    GetFlightsOutput gfo = getFlights(getTravelFlightInput); 
    
    
     //2.adding a  flight to the itinerary)  
    AddFlightToItineraryInput addFToIn = new AddFlightToItineraryInput();
    FlightInformation fInfo = new FlightInformation();
    fInfo=gfo.getFlightInformation().get(0);
    addFToIn.setFlightInformation(fInfo);
    addFToIn.setItineraryId(itineraryId);
    addFlightToItinerary(addFToIn);
    
    //3. Add planning a hotel, another flight, a third flight, and finally a hotel
    
    //3A - plan a hotel fist
    GetTravelHotelInput gthi = new  GetTravelHotelInput();
    GetHotelInput ghi = new GetHotelInput();
    GregorianCalendar xA = new GregorianCalendar(2016, 11, 1, 14, 0, 0);
    XMLGregorianCalendar dateA = null;
    dateA = DatatypeFactory.newInstance().newXMLGregorianCalendar(xA);
    GregorianCalendar xB = new GregorianCalendar(2016, 11, 3, 14, 0, 0);
    XMLGregorianCalendar dateB = null;
    dateB = DatatypeFactory.newInstance().newXMLGregorianCalendar(xB); 
    ghi.setCity("Copenhagen");
    ghi.setArrival(dateA);
    ghi.setDeparture(dateB);
    
    gthi.setGetHotelInput(ghi);
    gthi.setItineraryId(itineraryId);
    
    AddHotelToItineraryInput ahtii  = new AddHotelToItineraryInput();
    GetHotelOutput gho = new GetHotelOutput();
    gho = getHotels(gthi);
    ahtii.setHotelType(gho.getHotelType().get(0));
    ahtii.setItineraryId(itineraryId);
    
    addHotelToItinerary(ahtii);
    
    //3B - add another flight nr.2:
    GetTravelFlightInput getTravelFlightInputB = new GetTravelFlightInput();
    getTravelFlightInputB.setItineraryId(itineraryId);  
    GetFlightsInput getFlightsInputB = new GetFlightsInput(); 
    GregorianCalendar xC = new GregorianCalendar(2017, 10, 10, 14, 0, 0);
    XMLGregorianCalendar dateRiga = null;
    dateRiga = DatatypeFactory.newInstance().newXMLGregorianCalendar(xC);
    getFlightsInputB.setStartAirport("Riga");
    getFlightsInputB.setDestination("Dublin");
    getFlightsInputB.setFlightDate(dateRiga);
    getTravelFlightInputB.setGetFlightsInput(getFlightsInputB);
    getTravelFlightInputB.setItineraryId(itineraryId);
    GetFlightsOutput gfoB = getFlights(getTravelFlightInputB); 
    FlightInformation fInfoB = new FlightInformation();
    fInfoB = gfoB.getFlightInformation().get(0);
    AddFlightToItineraryInput addFToInB = new AddFlightToItineraryInput();
    addFToInB.setFlightInformation(fInfoB);
    addFToInB.setItineraryId(itineraryId);
    addFlightToItinerary(addFToInB);
    
    //3C add the thirdFlight
    GetTravelFlightInput getTravelFlightInput3C = new GetTravelFlightInput();
    getTravelFlightInput3C.setItineraryId(itineraryId);  
    GetFlightsInput getFlightsInput3C = new GetFlightsInput(); 
    
    GregorianCalendar x3C = new GregorianCalendar(2017, 11, 1, 19, 0, 0);
    XMLGregorianCalendar date3C = null;
    date3C = DatatypeFactory.newInstance().newXMLGregorianCalendar(x3C);
    
    getFlightsInput3C.setStartAirport("Riga");
    getFlightsInput3C.setDestination("Madrid");
    getFlightsInput3C.setFlightDate(date3C);
    
    getTravelFlightInput3C.setGetFlightsInput(getFlightsInput3C);
    getTravelFlightInput3C.setItineraryId(itineraryId); 
    
    GetFlightsOutput gfo3C = getFlights(getTravelFlightInput3C);
    FlightInformation fInfo3C = new FlightInformation();
    fInfo3C = gfo3C.getFlightInformation().get(1);
    
    AddFlightToItineraryInput addFToIn3C = new AddFlightToItineraryInput();
    addFToIn3C.setFlightInformation(fInfo3C);
    addFToIn3C.setItineraryId(itineraryId);
    addFlightToItinerary(addFToIn3C);
    
    //3D - and finally a hotel  
    GetTravelHotelInput gthi3D = new  GetTravelHotelInput();
    GetHotelInput ghi3D = new GetHotelInput();
    GregorianCalendar x3D = new GregorianCalendar(2016, 11, 1, 14, 0, 0);
    XMLGregorianCalendar date3D = null;
    dateA = DatatypeFactory.newInstance().newXMLGregorianCalendar(x3D);
    GregorianCalendar x3DB = new GregorianCalendar(2016, 11, 3, 14, 0, 0);
    XMLGregorianCalendar date3DB = null;
    dateB = DatatypeFactory.newInstance().newXMLGregorianCalendar(x3DB); 
    ghi3D.setCity("Amsterdam");
    ghi3D.setArrival(dateA);
    ghi3D.setDeparture(dateB);
    
    gthi3D.setGetHotelInput(ghi3D);
    gthi3D.setItineraryId(itineraryId);
    
    AddHotelToItineraryInput ahtii3D  = new AddHotelToItineraryInput();
    GetHotelOutput gho3D = new GetHotelOutput();
    gho3D = getHotels(gthi3D);
    ahtii3D.setHotelType(gho3D.getHotelType().get(0));
    ahtii3D.setItineraryId(itineraryId);
    addHotelToItinerary(ahtii3D);
            
    //Ask for the itinerary and check that it is correctusing JUnit's assert statements 
   // { i.e.assertEquals, assertTrue, . . . {in particular, that the booking status for each item is unconfirmed
    GetItineraryStatusOutput giso = new GetItineraryStatusOutput();
    giso = getItineraryStatus(itineraryId);
    
    //check if it returns 2 hotels as expected
    assertEquals(2, giso.getItineraryStatus().getHotelArray().getHotelTypeWithStatus().size());
    //check if it returns 3 flights as expected
    assertEquals(3, giso.getItineraryStatus().getFlightArray().getFlightInformationWithStatus().size());
    
        //check if each hotel item is unconfirmed:
        for(HotelTypeWithStatus a : giso.getItineraryStatus().getHotelArray().getHotelTypeWithStatus() ){
            System.out.println(a.getStatus());
           
                assertEquals("unconfirmed",a.getStatus());
        }
        
                //check if each hotel item is unconfirmed:
        for(FlightInformationWithStatus a : giso.getItineraryStatus().getFlightArray().getFlightInformationWithStatus() ){

                assertEquals("unconfirmed",a.getStatus());
        }
        
    //Book the itinerary and ask again for the itinerary.
    BookItineraryInput bii = new BookItineraryInput();
    CreditCardDetails ccd = new CreditCardDetails();
    ExpirationDate exd = new ExpirationDate();
    
    exd.setMonth(2);
    exd.setYear(11);
    ccd.setExpirationDate(exd);   
    ccd.setCardNumber("50408824");
    ccd.setHoldersName("Tick Joachim");
    
    bii.setCreditCardDetails(ccd);
    bii.setItineraryId(itineraryId);
    assertEquals(true,bookItinerary(bii));
    
    GetItineraryStatusOutput gisoBooked = new GetItineraryStatusOutput();
    gisoBooked = getItineraryStatus(itineraryId);
    
    //Check that each  hotel booking status is now confirmed   
    for(HotelTypeWithStatus g: gisoBooked.getItineraryStatus().getHotelArray().getHotelTypeWithStatus()){
        
        assertEquals("confirmed", g.getStatus());
    }
    
    //check that each flight booking status is now confirmed
    for(FlightInformationWithStatus f : gisoBooked.getItineraryStatus().getFlightArray().getFlightInformationWithStatus()){
        
        assertEquals("confirmed", f.getStatus());
        
    }
    
    
    
    
    int izz = 3; 
     
        
    }
    
    
    
    @Test
    //Cancel Planning
    public void P2() throws DatatypeConfigurationException
    {
        //creating the itinerary
        String itineraryId= createItinerary("");
        //generating the input for getting flighs 
        GetTravelFlightInput getTravelFlightInput = new GetTravelFlightInput();
        getTravelFlightInput.setItineraryId(itineraryId);
        GetFlightsInput getFlightsInput = new GetFlightsInput();
        getFlightsInput.setStartAirport("Riga");
        getFlightsInput.setDestination("Madrid");
        GregorianCalendar x3 = new GregorianCalendar(2017, 11, 1, 14, 0, 0);
        XMLGregorianCalendar date3 = null;
        date3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(x3);
        getFlightsInput.setFlightDate(date3);
        getTravelFlightInput.setGetFlightsInput(getFlightsInput);
         
         // getting flights bsaed on the info
        GetFlightsOutput gfo = getFlights(getTravelFlightInput);
        
         //generating input for adding a flight to the itinerary
         AddFlightToItineraryInput addFlightToItineraryInput = new AddFlightToItineraryInput();
         addFlightToItineraryInput.setItineraryId(itineraryId);
         
         //adding the first flight from the flight list to the itineary  input information
         addFlightToItineraryInput.setFlightInformation(gfo.getFlightInformation().get(0));
         
         // adding the flight to the itineary 
         GetItineraryStatusOutput itineraryStatus2 = addFlightToItinerary(addFlightToItineraryInput);
         
         // checking that it is uncorfirmed status for the flight
         assertEquals("unconfirmed", itineraryStatus2.getItineraryStatus().getFlightArray().getFlightInformationWithStatus().get(0).getStatus() );
         //chhecking if the flight is in the itinerary
         assertEquals(gfo.getFlightInformation().get(0).getBookingNo(), itineraryStatus2.getItineraryStatus().getFlightArray().getFlightInformationWithStatus().get(0).getBookingNo() );
         
         // checking if the cancel plan went through
         assertEquals(true,cancelPlan(itineraryId));
         //returning the itinerary
         itineraryStatus2 = getItineraryStatus(itineraryId);
         
         //checking if there are now 0 flights inside the itineary.
         assertEquals(0, itineraryStatus2.getItineraryStatus().getFlightArray().getFlightInformationWithStatus().size());
         
    }
    
    
    @Test
    public void B() throws DatatypeConfigurationException
    { //Bech Camilla 50408822 7 9 
        
        
        //creating itinerary 
        String itineraryId= createItinerary("");
        
        //getting flights 
        GetTravelFlightInput getTravelFlightInput = new GetTravelFlightInput();
        getTravelFlightInput.setItineraryId(itineraryId);
        GetFlightsInput getFlightsInput = new GetFlightsInput();
        getFlightsInput.setStartAirport("Riga");
        getFlightsInput.setDestination("Madrid");
        GregorianCalendar x3 = new GregorianCalendar(2017, 11, 1, 14, 0, 0);
        
        XMLGregorianCalendar date3 = null;
        date3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(x3);
        getFlightsInput.setFlightDate(date3);
        getTravelFlightInput.setGetFlightsInput(getFlightsInput);
        GetFlightsOutput gfo = getFlights(getTravelFlightInput);
        
         
         AddFlightToItineraryInput addFlightToItineraryInput = new AddFlightToItineraryInput();
         addFlightToItineraryInput.setItineraryId(itineraryId);
         //adding Flight  on index 1 from the list  to the itineary - this flight is cheaper and can be bought 
         addFlightToItineraryInput.setFlightInformation(gfo.getFlightInformation().get(1));
         GetItineraryStatusOutput itineraryStatus2 = addFlightToItinerary(addFlightToItineraryInput);
         
         //adding the flight on index 0 from the list to the itinerary twice - this flight is expensive and it cannot be booked
         addFlightToItineraryInput.setFlightInformation(gfo.getFlightInformation().get(0));
         
         itineraryStatus2 = addFlightToItinerary(addFlightToItineraryInput);
         
         itineraryStatus2 = addFlightToItinerary(addFlightToItineraryInput);
         
         
         //adding information about credit card to the itinerary.
        
         BookItineraryInput bookItineraryInput = new BookItineraryInput();
         bookItineraryInput.setItineraryId(itineraryId);
         CreditCardDetails creditCardDetails = new CreditCardDetails();
         creditCardDetails.setCardNumber("50408822");
         creditCardDetails.setHoldersName("Bech Camilla");
         ExpirationDate expirationDate = new ExpirationDate();
         expirationDate.setMonth(7);
         expirationDate.setYear(9);
         creditCardDetails.setExpirationDate(expirationDate);
         bookItineraryInput.setCreditCardDetails(creditCardDetails);
         
         //checking if the booking FAILS
         assertEquals(false,  bookItinerary(bookItineraryInput));
         
         itineraryStatus2 = getItineraryStatus(itineraryId);
         //chcking if the first flight has status Canceled 
         assertEquals("canceled", itineraryStatus2.getItineraryStatus().getFlightArray().getFlightInformationWithStatus().get(0).getStatus());
        //checking if the second flight has status unconfirmed 
           assertEquals("unconfirmed", itineraryStatus2.getItineraryStatus().getFlightArray().getFlightInformationWithStatus().get(1).getStatus());
         //checking if the third flight has status unconfirmed 
           assertEquals("unconfirmed", itineraryStatus2.getItineraryStatus().getFlightArray().getFlightInformationWithStatus().get(1).getStatus());

        
    }
    
    
    
    
    @Test
    public void C1() throws DatatypeConfigurationException
    {
         String itineraryId= createItinerary("");
        GetTravelFlightInput getTravelFlightInput = new GetTravelFlightInput();
        getTravelFlightInput.setItineraryId(itineraryId);
        GetFlightsInput getFlightsInput = new GetFlightsInput();
        getFlightsInput.setStartAirport("Riga");
        getFlightsInput.setDestination("Madrid");
         GregorianCalendar x3 = new GregorianCalendar(2017, 11, 1, 14, 0, 0);
        
        XMLGregorianCalendar date3 = null;
        date3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(x3);
        getFlightsInput.setFlightDate(date3);
        getTravelFlightInput.setGetFlightsInput(getFlightsInput);
         
         
         GetFlightsOutput gfo = getFlights(getTravelFlightInput);
        
         
         AddFlightToItineraryInput addFlightToItineraryInput = new AddFlightToItineraryInput();
         addFlightToItineraryInput.setItineraryId(itineraryId);
         addFlightToItineraryInput.setFlightInformation(gfo.getFlightInformation().get(1));
         
         
         GetItineraryStatusOutput itineraryStatus2 = addFlightToItinerary(addFlightToItineraryInput);
         
         addFlightToItineraryInput.setFlightInformation(gfo.getFlightInformation().get(0));
         
         itineraryStatus2 = addFlightToItinerary(addFlightToItineraryInput);
         
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
      
       GetHotelOutput h = getHotels(getTravelHotelInput);

        List<HotelType> hot = h.getHotelType();

    
        
        AddHotelToItineraryInput addHotelToItineraryInput = new AddHotelToItineraryInput();
        addHotelToItineraryInput.setItineraryId(itineraryId);
        addHotelToItineraryInput.setHotelType(hot.get(0));
        
       GetItineraryStatusOutput itineraryStatus =      addHotelToItinerary(addHotelToItineraryInput);
       
       assertEquals(2, itineraryStatus.getItineraryStatus().getFlightArray().getFlightInformationWithStatus().size());
       assertEquals(1, itineraryStatus.getItineraryStatus().getHotelArray().getHotelTypeWithStatus().size());
       
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
         
         GetItineraryStatusOutput itineraryAfterBooking = getItineraryStatus(itineraryId);
         
         assertEquals(true, booked);
         CancelItineraryInput cancelItineraryInput = new CancelItineraryInput();
         cancelItineraryInput.setItineraryId(itineraryId);
         cancelItineraryInput.setCreditCardDetails(creditCardDetails);
         boolean canceled = cancelItinerary(cancelItineraryInput);
         assertEquals(canceled, true);
         
         GetItineraryStatusOutput itineraryStatusAfterCanceling = getItineraryStatus(itineraryId);
         
         assertEquals("canceled", itineraryStatusAfterCanceling.getItineraryStatus().getFlightArray().getFlightInformationWithStatus().get(0).getStatus());
          assertEquals("canceled", itineraryStatusAfterCanceling.getItineraryStatus().getFlightArray().getFlightInformationWithStatus().get(1).getStatus());
          assertEquals("canceled", itineraryStatusAfterCanceling.getItineraryStatus().getHotelArray().getHotelTypeWithStatus().get(0).getStatus());
       
    }
    
    
    @Test
    public void C2() throws DatatypeConfigurationException
    {
        String itineraryId= createItinerary("");
        GetTravelFlightInput getTravelFlightInput = new GetTravelFlightInput();
        getTravelFlightInput.setItineraryId(itineraryId);
        GetFlightsInput getFlightsInput = new GetFlightsInput();
        getFlightsInput.setStartAirport("Riga");
        getFlightsInput.setDestination("Madrid");
         GregorianCalendar x3 = new GregorianCalendar(2017, 11, 1, 14, 0, 0);
        
        XMLGregorianCalendar date3 = null;
        date3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(x3);
        getFlightsInput.setFlightDate(date3);
        getTravelFlightInput.setGetFlightsInput(getFlightsInput);
         
         
         GetFlightsOutput gfo = getFlights(getTravelFlightInput);
        
         
         AddFlightToItineraryInput addFlightToItineraryInput = new AddFlightToItineraryInput();
         addFlightToItineraryInput.setItineraryId(itineraryId);
         addFlightToItineraryInput.setFlightInformation(gfo.getFlightInformation().get(1));
         
         
         GetItineraryStatusOutput itineraryStatus2 = addFlightToItinerary(addFlightToItineraryInput);
         
         
         x3 = new GregorianCalendar(2015, 11, 2, 14, 0, 0);
        
        date3 = null;
        date3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(x3);
        getFlightsInput.setFlightDate(date3);
        getTravelFlightInput.setGetFlightsInput(getFlightsInput);
         
         
          gfo = getFlights(getTravelFlightInput);
        
         
         addFlightToItineraryInput = new AddFlightToItineraryInput();
         addFlightToItineraryInput.setItineraryId(itineraryId);
         addFlightToItineraryInput.setFlightInformation(gfo.getFlightInformation().get(0));
      
         
         itineraryStatus2 = addFlightToItinerary(addFlightToItineraryInput);
         
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
      
       GetHotelOutput h = getHotels(getTravelHotelInput);

        List<HotelType> hot = h.getHotelType();

    
        
        AddHotelToItineraryInput addHotelToItineraryInput = new AddHotelToItineraryInput();
        addHotelToItineraryInput.setItineraryId(itineraryId);
        addHotelToItineraryInput.setHotelType(hot.get(0));
        
       GetItineraryStatusOutput itineraryStatus =      addHotelToItinerary(addHotelToItineraryInput);
       
       assertEquals(2, itineraryStatus.getItineraryStatus().getFlightArray().getFlightInformationWithStatus().size());
       assertEquals(1, itineraryStatus.getItineraryStatus().getHotelArray().getHotelTypeWithStatus().size());
       
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
         
           
         GetItineraryStatusOutput itineraryAfterBooking = getItineraryStatus(itineraryId);
         
         assertEquals(true, booked);
         CancelItineraryInput cancelItineraryInput = new CancelItineraryInput();
         cancelItineraryInput.setItineraryId(itineraryId);
         cancelItineraryInput.setCreditCardDetails(creditCardDetails);
         boolean canceled = cancelItinerary(cancelItineraryInput);
         assertEquals(canceled, true);
         
         GetItineraryStatusOutput itineraryStatusAfterCanceling = getItineraryStatus(itineraryId);
         
         assertEquals("canceled", itineraryStatusAfterCanceling.getItineraryStatus().getFlightArray().getFlightInformationWithStatus().get(0).getStatus());
          assertEquals("confirmed", itineraryStatusAfterCanceling.getItineraryStatus().getFlightArray().getFlightInformationWithStatus().get(1).getStatus());
          assertEquals("canceled", itineraryStatusAfterCanceling.getItineraryStatus().getHotelArray().getHotelTypeWithStatus().get(0).getStatus());
    }
    
    
    
    
    
      
    public void testHotelListType() throws DatatypeConfigurationException  {

        String itineraryId= createItinerary("");
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
         
          System.out.println("Cancelation" +cancelPlan(itineraryId));
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
