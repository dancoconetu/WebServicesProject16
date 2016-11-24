/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import HelpClasses.FlightInformationList;
import HelpClasses.FlightsInfo;
import HelpClasses.HotelInformationListLocal;
import HelpClasses.HotelsInfo;
import HelpClasses.ItinararyRepr;
import HelpClasses.Itinerary;
import HelpClasses.StatusInfo;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.lameduck.CreditCardDetails;
import ws.lameduck.ExpirationDate;

/**
 *
 * @author justinas
 */
public class MandatoryTests {
    
    Client client = ClientBuilder.newClient();
     WebTarget itinTarget = client.target("http://localhost:9090/TravelGoodsREST/itinerary");
     //WebTarget flightTarget = client.target("http://localhost:9090/TravelGoodsREST/flights?departureCity=Viena&destinationCity=Vilnius&date=2016-11-1%2014:00");
     WebTarget flightTarget = client.target("http://localhost:9090/TravelGoodsREST");
     WebTarget hotelTarget = client.target("http://localhost:9090/TravelGoodsREST");
     //WebTarget hotelTarget = client.target("http://localhost:9090/TravelGoodsREST/hotels?city=Copenhagen&startDate=2016-11-1%2014:00&endDate=2016-11-5%2014:00");
    
    
    public MandatoryTests() {
    }
    
    //Have 1000DKK in the bank account
     private CreditCardDetails getCreditCardInformationOne(){
    CreditCardDetails CreditInfo = new CreditCardDetails();
        CreditInfo.setCardNumber("50408823");
        CreditInfo.setHoldersName("Tobiasen Inge");
          ExpirationDate ex = new ExpirationDate();
          ex.setMonth(9);
          ex.setYear(10);
        CreditInfo.setExpirationDate(ex);
        
        return CreditInfo;
    
      
    }
     //Have 10 000DKK in the bank account
     private CreditCardDetails getCreditCardInformationTen(){
    CreditCardDetails CreditInfo = new CreditCardDetails();
        CreditInfo.setCardNumber("50408824");
        CreditInfo.setHoldersName("Tick Joachim");
          ExpirationDate ex = new ExpirationDate();
          ex.setMonth(2);
          ex.setYear(11);
        CreditInfo.setExpirationDate(ex);
        
        return CreditInfo;
    
      
    }
    
  @Test
  public void testP1(){
      //Planning and booking
      
      
      //1.Get a list of the flights and hotels      
      FlightInformationList flightList1 = flightTarget.path("/flights").queryParam("date", "2017-11-1 14:00")
              .queryParam("departureCity", "Viena").
              queryParam("destinationCity", "Vilnius").request().get(FlightInformationList.class);
      FlightInformationList flightList2 = flightTarget.path("/flights").queryParam("date", "2016-12-10 14:00")
              .queryParam("departureCity", "Berlin").
              queryParam("destinationCity", "Dublin").request().get(FlightInformationList.class);
      FlightInformationList flightList3 = flightTarget.path("/flights").queryParam("date", "2017-04-16 12:00")
              .queryParam("departureCity", "Talin").
              queryParam("destinationCity", "Copenhagen").request().get(FlightInformationList.class);
    HotelInformationListLocal hotelList1 = hotelTarget.path("/hotels").queryParam("startDate", "2017-11-1 13:00")
            .queryParam("endDate", "2017-11-2 13:00").queryParam("city", "Los Angeles")
            .request().get(HotelInformationListLocal.class);
    HotelInformationListLocal hotelList2 = hotelTarget.path("/hotels").queryParam("startDate", "2017-03-1 13:00")
            .queryParam("endDate", "2017-04-1 13:00").queryParam("city", "Oslo")
            .request().get(HotelInformationListLocal.class);  
    
    assertTrue(flightList1.getList().size()>0);
      assertTrue(flightList2.getList().size()>0);
      assertTrue(flightList3.getList().size()>0);
      assertTrue(hotelList1.getList().size()>0);
      assertTrue(hotelList2.getList().size()>0);
    
    //2.Create itinierary post method, which returning the ID of created itninerary
    String itineraryID = itinTarget.request().post(null).readEntity(String.class);
      System.out.println("Itininerary created with ID of: "+itineraryID);
      //MUST be CHANGED!!!!!
      assertEquals(itineraryID, itineraryID);
      
    //3. Add 1st flight to the itinerary  
      itinTarget.path("/"+itineraryID+"/flights").request().post(Entity.entity(flightList1,MediaType.APPLICATION_XML));
    //3. Add 1st hotel to the itinerary  
      itinTarget.path("/"+itineraryID+"/hotels").request().post(Entity.entity(hotelList1,MediaType.APPLICATION_XML));
    //3.  Add 2nd and 3rd flight to the itinerary   
      itinTarget.path("/"+itineraryID+"/flights").request().post(Entity.entity(flightList2,MediaType.APPLICATION_XML));
      itinTarget.path("/"+itineraryID+"/flights").request().post(Entity.entity(flightList3,MediaType.APPLICATION_XML));
    //3. Add 2nd hotel to the itinerary    
      itinTarget.path("/"+itineraryID+"/hotels").request().post(Entity.entity(hotelList1,MediaType.APPLICATION_XML));
      
      
     //4. Retrieve itinerary
      ItinararyRepr itineraryRepr = itinTarget.path("/"+itineraryID).request().get(ItinararyRepr.class);
      Itinerary itinerary = itineraryRepr.getItinerary();
      //5. Check that each booked hotel/flight is unconfirmed 
      for(FlightsInfo fl:itinerary.getFlightDetails()){
      
          assertEquals(StatusInfo.Status.UNCONFIRMED, fl.getStatus());
      }
      for(HotelsInfo ht:itinerary.getHotelDetails()){
      
          assertEquals(StatusInfo.Status.UNCONFIRMED, ht.getStatus());
      }
      
      //6. Book itinerary
      itinTarget.path("/"+itineraryID).request().put(Entity.entity(getCreditCardInformationTen(),MediaType.APPLICATION_XML));
      
      //7. Get once again itinerary
      ItinararyRepr itineraryRepr2 = itinTarget.path("/"+itineraryID).request().get(ItinararyRepr.class);
      Itinerary itinerary2 = itineraryRepr2.getItinerary();
      
      
      //8. Check that after booking is confirmed, that ALL flights and flight status is confirmed
      for(FlightsInfo fl:itinerary2.getFlightDetails()){
          assertEquals(StatusInfo.Status.CONFIRMED, fl.getStatus());
      
      }
      for(HotelsInfo ht:itinerary2.getHotelDetails()){
          assertEquals(StatusInfo.Status.CONFIRMED, ht.getStatus());
      
      }
      //9. CHeck if the itinerary status is confirmed:
      assertEquals(StatusInfo.Status.CONFIRMED, itinerary2.getStatus());
         
      System.out.println("FlightList: " + hotelList2.getList().get(0).getPrice());

  }
  
  @Test
  public void testP2(){
  
      //1.Get a list of the flights     
      FlightInformationList flightList1 = flightTarget.path("/flights").queryParam("date", "2017-11-1 14:00")
              .queryParam("departureCity", "Viena").
              queryParam("destinationCity", "Vilnius").request().get(FlightInformationList.class);
      assertTrue(flightList1.getList().size()>0);
      //2.Create itinierary post method, which returning the ID of created itninerary
    String itineraryID = itinTarget.request().post(null).readEntity(String.class);
      System.out.println("TestP2: Itininerary created with ID of: "+itineraryID);
      
      //3. Add a flight to the itinerary  
      itinTarget.path("/"+itineraryID+"/flights").request().post(Entity.entity(flightList1,MediaType.APPLICATION_XML));
      
      //4. Retrieve itinerary:
      ItinararyRepr itineraryRepr2 = itinTarget.path("/"+itineraryID).request().get(ItinararyRepr.class);
      Itinerary itinerary2 = itineraryRepr2.getItinerary();
      
      //5. Check the status of itinerary..expected cancel
      assertEquals(StatusInfo.Status.UNCONFIRMED, itinerary2.getStatus());
      //6.Cancel itinerary/planning
      String itineraryStatus = itinTarget.path("/"+itineraryID+"/cancelItinerary").request().
              put(Entity.entity(getCreditCardInformationTen(), MediaType.APPLICATION_XML))
              .readEntity(String.class);
      assertEquals("Cancelled", itineraryStatus);
  
  }
  @Test
  public void testB(){
    // For the CANCEL SCENARIO TO FAIL FLIGHT PRICE IS BIGGER THAN IN CREDITCARD 
  //1.Get a list of the flights and hotels       
      FlightInformationList flightList1 = flightTarget.path("/flights").queryParam("date", "2017-11-1 14:00")
              .queryParam("departureCity", "Viena").
              queryParam("destinationCity", "Vilnius").request().get(FlightInformationList.class);
    HotelInformationListLocal hotelList1 = hotelTarget.path("/hotels").queryParam("startDate", "2017-11-1 13:00")
            .queryParam("endDate", "2017-11-2 13:00").queryParam("city", "Oslo")
            .request().get(HotelInformationListLocal.class);
          FlightInformationList flightList2 = flightTarget.path("/flights").queryParam("date", "2017-11-1 14:00")
              .queryParam("departureCity", "Minsk").
              queryParam("destinationCity", "Vilnius").request().get(FlightInformationList.class);
 
    
    assertTrue(flightList1.getList().size()>0);
      assertTrue(hotelList1.getList().size()>0);
      assertTrue(flightList2.getList().size()>0);
      
      System.out.println("SOOO " + hotelList1.getList().get(0).getHotelName());
    //2.Create itinerary post method, which returning the ID of created itninerary
    String itineraryID = itinTarget.request().post(null).readEntity(String.class);
      System.out.println("TEST B:  Itininerary created with ID of: "+itineraryID);
      //MUST be CHANGED!!!!!
      assertEquals(itineraryID, itineraryID);
      
      
      //3. Add one flight to the itinerary  
      itinTarget.path("/"+itineraryID+"/flights").request().post(Entity.entity(flightList1,MediaType.APPLICATION_XML));
      //3. Add 2nd flight to the itinerary  (FAILING one)   
      itinTarget.path("/"+itineraryID+"/flights").request().post(Entity.entity(flightList2,MediaType.APPLICATION_XML));
      //3. Add one hotel to the itinerary  
      itinTarget.path("/"+itineraryID+"/hotels").request().post(Entity.entity(hotelList1,MediaType.APPLICATION_XML));
    
    
      
      
     //4. Retrieve itinerary
      ItinararyRepr itineraryRepr = itinTarget.path("/"+itineraryID).request().get(ItinararyRepr.class);
      Itinerary itinerary = itineraryRepr.getItinerary();
      //5. Check that each booked hotel/flight is unconfirmed 
      for(FlightsInfo fl:itinerary.getFlightDetails()){
      
          assertEquals(StatusInfo.Status.UNCONFIRMED, fl.getStatus());
      }
      for(HotelsInfo ht:itinerary.getHotelDetails()){
      
          assertEquals(StatusInfo.Status.UNCONFIRMED, ht.getStatus());
      }
      
      //6. Book itinerary
      String resp = itinTarget.path("/"+itineraryID).request()
              .put(Entity.entity(getCreditCardInformationOne(),MediaType.APPLICATION_XML))
              .readEntity(String.class);
      
      //if Book itinerary is failed for some rason, FIALtoBOOK response is received
      assertEquals("FAILtoBOOK", resp);
      
      //7. retrieve booking:
       ItinararyRepr itineraryRepr2 = itinTarget.path("/"+itineraryID).request().get(ItinararyRepr.class);
      Itinerary itinerary2 = itineraryRepr2.getItinerary();
      
      //8. 1st status of booking has to be canceled, the other two has to be unconfirmmed
      assertEquals(StatusInfo.Status.CANCELLED, itinerary2.getFlightDetails().get(0).getStatus());
      assertEquals(StatusInfo.Status.UNCONFIRMED, itinerary2.getFlightDetails().get(1).getStatus());
      assertEquals(StatusInfo.Status.UNCONFIRMED, itinerary2.getHotelDetails().get(0).getStatus());
      
  
  }
  
 @Test
public void testC1(){
//1.Get a list of the flights and hotels       
      FlightInformationList flightList1 = flightTarget.path("/flights").queryParam("date", "2017-11-1 14:00")
              .queryParam("departureCity", "Viena").
              queryParam("destinationCity", "Vilnius").request().get(FlightInformationList.class);
    HotelInformationListLocal hotelList1 = hotelTarget.path("/hotels").queryParam("startDate", "2017-11-1 13:00")
            .queryParam("endDate", "2017-11-2 13:00").queryParam("city", "Oslo")
            .request().get(HotelInformationListLocal.class);
          FlightInformationList flightList2 = flightTarget.path("/flights").queryParam("date", "2017-11-1 14:00")
              .queryParam("departureCity", "Minsk").
              queryParam("destinationCity", "Vilnius").request().get(FlightInformationList.class);
 
    
    assertTrue(flightList1.getList().size()>0);
      assertTrue(hotelList1.getList().size()>0);
      assertTrue(flightList2.getList().size()>0);
      
      
    //2.Create itinerary post method, which returning the ID of created itninerary
    String itineraryID = itinTarget.request().post(null).readEntity(String.class);
      System.out.println("TEST C1:  Itininerary created with ID of: "+itineraryID);
      //MUST be CHANGED!!!!!
      assertEquals(itineraryID, itineraryID);
      
      
      //3. Add one flight to the itinerary  
      itinTarget.path("/"+itineraryID+"/flights").request().post(Entity.entity(flightList1,MediaType.APPLICATION_XML));
      //3. Add 2nd flight to the itinerary   
      itinTarget.path("/"+itineraryID+"/flights").request().post(Entity.entity(flightList2,MediaType.APPLICATION_XML));
      //3. Add one hotel to the itinerary  
      itinTarget.path("/"+itineraryID+"/hotels").request().post(Entity.entity(hotelList1,MediaType.APPLICATION_XML));
    
   
      //4. Book itinerary
      String resp = itinTarget.path("/"+itineraryID).request()
              .put(Entity.entity(getCreditCardInformationTen(),MediaType.APPLICATION_XML))
              .readEntity(String.class);
      
            
      
     //5. Retrieve itinerary
      ItinararyRepr itineraryRepr = itinTarget.path("/"+itineraryID).request().get(ItinararyRepr.class);
      Itinerary itinerary = itineraryRepr.getItinerary();
      //6. Check that each booked hotel/flight is confirmed 
      for(FlightsInfo fl:itinerary.getFlightDetails()){
      
          assertEquals(StatusInfo.Status.CONFIRMED, fl.getStatus());
      }
      for(HotelsInfo ht:itinerary.getHotelDetails()){
      
          assertEquals(StatusInfo.Status.CONFIRMED, ht.getStatus());
      }
      
      //7 CANCELLING the BOOKING
        String itineraryStatus = itinTarget.path("/"+itineraryID+"/cancelItinerary").request().
              put(Entity.entity(getCreditCardInformationTen(), MediaType.APPLICATION_XML))
              .readEntity(String.class);
      assertEquals("SuccesfullyCancelled", itineraryStatus);
      
      
      //8. retrieve booking:
       ItinararyRepr itineraryRepr2 = itinTarget.path("/"+itineraryID).request().get(ItinararyRepr.class);
      Itinerary itinerary2 = itineraryRepr2.getItinerary();
      //9. Check if all the bookings are cancelled
      
        for(FlightsInfo fl:itinerary2.getFlightDetails()){
      
          assertEquals(StatusInfo.Status.CANCELLED, fl.getStatus());
      }
      for(HotelsInfo ht:itinerary2.getHotelDetails()){
      
          assertEquals(StatusInfo.Status.CANCELLED, ht.getStatus());
      }
      
      
      
  

}

@Test
public void testC2(){
//1.Get a list of the flights and hotels       
      FlightInformationList flightList1 = flightTarget.path("/flights").queryParam("date", "2017-11-1 14:00")
              .queryParam("departureCity", "Viena").
              queryParam("destinationCity", "Vilnius").request().get(FlightInformationList.class);
    HotelInformationListLocal hotelList1 = hotelTarget.path("/hotels").queryParam("startDate", "2017-11-1 13:00")
            .queryParam("endDate", "2017-11-2 13:00").queryParam("city", "Oslo")
            .request().get(HotelInformationListLocal.class);
          FlightInformationList flightList2 = flightTarget.path("/flights").queryParam("date", "2017-06-05 14:00")
              .queryParam("departureCity", "Boston").
              queryParam("destinationCity", "Barcelona").request().get(FlightInformationList.class);
 
    
    assertTrue(flightList1.getList().size()>0);
      assertTrue(hotelList1.getList().size()>0);
      assertTrue(flightList2.getList().size()>0);
      
      
    //2.Create itinerary post method, which returning the ID of created itninerary
    String itineraryID = itinTarget.request().post(null).readEntity(String.class);
      System.out.println("TEST C1:  Itininerary created with ID of: "+itineraryID);
      //MUST be CHANGED!!!!!
      assertEquals(itineraryID, itineraryID);
      
      
      //3. Add one flight to the itinerary  
      itinTarget.path("/"+itineraryID+"/flights").request().post(Entity.entity(flightList1,MediaType.APPLICATION_XML));
      //3. Add 2nd flight to the itinerary   
      itinTarget.path("/"+itineraryID+"/flights").request().post(Entity.entity(flightList2,MediaType.APPLICATION_XML));
      //3. Add one hotel to the itinerary  
      itinTarget.path("/"+itineraryID+"/hotels").request().post(Entity.entity(hotelList1,MediaType.APPLICATION_XML));
    
   
      //4. Book itinerary
      String resp = itinTarget.path("/"+itineraryID).request()
              .put(Entity.entity(getCreditCardInformationTen(),MediaType.APPLICATION_XML))
              .readEntity(String.class);
      
            
      
     //5. Retrieve itinerary
      ItinararyRepr itineraryRepr = itinTarget.path("/"+itineraryID).request().get(ItinararyRepr.class);
      Itinerary itinerary = itineraryRepr.getItinerary();
      //6. Check that each booked hotel/flight is confirmed 
      for(FlightsInfo fl:itinerary.getFlightDetails()){
      
          assertEquals(StatusInfo.Status.CONFIRMED, fl.getStatus());
      }
      for(HotelsInfo ht:itinerary.getHotelDetails()){
      
          assertEquals(StatusInfo.Status.CONFIRMED, ht.getStatus());
      }
     //7. Cancelling bookings, supposed to get string "Cancelling is failed" 
  String itineraryStatus = itinTarget.path("/"+itineraryID+"/cancelItinerary").request().
              put(Entity.entity(getCreditCardInformationOne(), MediaType.APPLICATION_XML))
              .readEntity(String.class);
      assertEquals("Cancelling is failed", itineraryStatus);
      
          //8. Retrieve itinerary
      ItinararyRepr itineraryRepr1 = itinTarget.path("/"+itineraryID).request().get(ItinararyRepr.class);
      Itinerary itinerary1 = itineraryRepr1.getItinerary();
      
       //9. first and third of the bookings has be cancelled, but the second one has to be confirmed status
      assertEquals(StatusInfo.Status.CANCELLED, itinerary1.getFlightDetails().get(0).getStatus());
      assertEquals(StatusInfo.Status.CONFIRMED, itinerary1.getFlightDetails().get(1).getStatus());
      assertEquals(StatusInfo.Status.CANCELLED, itinerary1.getHotelDetails().get(0).getStatus());
      
      
      
}

}

