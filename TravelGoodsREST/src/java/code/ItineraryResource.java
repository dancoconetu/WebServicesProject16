/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ws.lameduck.*;
import ws.niceview.*;
import HelpClasses.*;
import java.util.Calendar;
import java.util.GregorianCalendar; 
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author justinas
 */
@Path("/itinerary")
public class ItineraryResource {

    public static HashMap<Integer, Itinerary> itineraryMap = new HashMap<>();
    private final static String output = "application/xml";

    // http://localhost:9090/TravelGoodsREST/itinerary/x
 
    @GET
    @Produces(output)
    @Path("/{id}")
    public Response getStatusOfItinerary(@PathParam("id") int itineraryID) {

        //check if there is a record in the map of requested ID
        if (itineraryMap.containsKey(itineraryID)) {
            //getting itinerary from map
            Itinerary iten = itineraryMap.get(itineraryID);

            ItinararyRepr ir = new ItinararyRepr();
           // ir.setItinerary(iten);
            ir.addLinks(iten, itineraryID);
            return Response.ok(ir).build();

        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
                  
    @POST  
    @Produces(output)
    public Response createItineraryEmpty() {
        int ID = itineraryMap.size() + 1;
        System.out.println("ID " + ID);
        Itinerary itinerary = new Itinerary();
        itinerary.setStatus(StatusInfo.Status.UNCONFIRMED);
        itinerary.setFlightDetails(null);
        itinerary.setHotelDetails(null);
        itineraryMap.put(ID, itinerary);
        ItinararyRepr ir = new ItinararyRepr();
        ir.setItinerary(itinerary);
        System.out.println("creatItenerary executed!! created with ID: " + ID);
 
        return Response.status(Response.Status.OK).entity(""+ID).build();

    }

    @POST
    @Path("/{id}/hotels") 
    @Consumes(output)
    @Produces(output)
    public Response addHotelToItinerary(@PathParam("id") int id, HotelInformationListLocal hotelsList) {
        if (itineraryMap.containsKey(id)) {
            Itinerary itin = itineraryMap.get(id); 
           System.out.println("addHotelItinerary() executed");
            //if the status of itinerary is unconfirmed then add flights
            if (itin.getStatus().equals(StatusInfo.Status.UNCONFIRMED)) {

                List<HotelsInfo> currentHotelList = new ArrayList<>();
                if(!(itin.getHotelDetails()==null)){
                currentHotelList =itin.getHotelDetails();
                }
 
                for (HotelType hotelLoop : hotelsList.getList()) {
                    System.out.println("address for hotel" + hotelLoop.getAdress());
                    HotelsInfo hotelLocalLoop = new HotelsInfo();
                    hotelLocalLoop.setStatus(StatusInfo.Status.UNCONFIRMED);
                    hotelLocalLoop.setHotelDetails(hotelLoop);
                    currentHotelList.add(hotelLocalLoop);
                    
                }

                itin.setHotelDetails(currentHotelList);
                itineraryMap.put(id, itin);
                System.out.println("Itinerary id: " + id + " Has been added");
                return Response.status(Response.Status.OK).entity("Hotels successfully added into itinerary").build();
            }

        }
        return Response.status(Response.Status.NOT_FOUND).entity("Itinerary not found").build();
    }

    @POST
    @Produces(output)
    @Path("/{id}/flights")
    public Response addFlightToItinerary(@PathParam("id") int itineraryID, FlightInformationList flightL) {
        System.out.println("addFlighttoItenerary() executed");
        //if ID is found in the hashmap then retrieve itinerary
        if (itineraryMap.containsKey(itineraryID)) {
            Itinerary itin = itineraryMap.get(itineraryID);
            
            //if the status of itinerary is unconfirmed then add flights
            if (itin.getStatus().equals(StatusInfo.Status.UNCONFIRMED)) {
                
                 List<FlightsInfo> currentFlightList = new ArrayList<>();
                if(!(itin.getFlightDetails()==null)){
                currentFlightList =itin.getFlightDetails();
                }
               

                 for (FlightInformation fl : flightL.getList()) {
                    System.out.println("price " + fl.getPrice());
                    FlightsInfo FI = new FlightsInfo();
                    FI.setStatus(StatusInfo.Status.UNCONFIRMED);
                    FI.setFlightDetails(fl);
                    currentFlightList.add(FI);
                }

                itin.setFlightDetails(currentFlightList);
                itineraryMap.put(itineraryID, itin);
                System.out.println("Itinerary id: " + itineraryID + " Has been added");
                return Response.status(Response.Status.OK).entity("Flights successfully added into itinerary").build();
            }

        }

        return Response.status(Response.Status.NOT_FOUND).entity("Itinerary not found").build();
    }    

    @PUT
    @Path("/{id}")
    @Consumes(output)
    @Produces(output)
    public Response bookItinerary(@PathParam("id") int id, CreditCardDetails CardInfo) {
        boolean booking = true;
        if (itineraryMap.containsKey(id)) {
            System.out.println("itinerary is found, starting a book");
            Itinerary itin = itineraryMap.get(id);
            
            List<FlightsInfo> confirmedFlights = new ArrayList<FlightsInfo>();
            List<HotelsInfo> confirmedHotels = new ArrayList<HotelsInfo>();
            //start booking if itnierary status is unconfirmed
            if (itin.getStatus() == StatusInfo.Status.UNCONFIRMED) {
                   
                //for each flight/hotel connect to soap and and book them
                try {
                    if (itin.getFlightDetails()!=null) {
                        for (FlightsInfo fl : itin.getFlightDetails()) {
                            System.out.println("Booking flights: " + fl.getFlightDetails().getNameAirline());
                            BookFlightInput gfi = new BookFlightInput();
                            gfi.setBookingNo(fl.getFlightDetails().getBookingNo());
                            gfi.setCreditCardDetails(CardInfo);

                            if(bookFlight(gfi)){
                                
                            System.out.println("book flight set to Confirmed");
                            fl.setStatus(StatusInfo.Status.CONFIRMED);
                            confirmedFlights.add(fl);
                            }
                            

                        }
                    }
                    if (itin.getHotelDetails()!=null) {
                        for (HotelsInfo ht : itin.getHotelDetails()) {

                            BookHotelInput bhi = new BookHotelInput();
                            bhi.setBookingNR(ht.getHotelDetails().getBookingNR());
                            bhi.setCreditCardInformation(fromFlightToHotelsCredit(CardInfo));
                            if(bookHotel(bhi)){
                                System.out.println("Hotels confirmed" + ht.getHotelDetails().getHotelName());
                            ht.setStatus(StatusInfo.Status.CONFIRMED);
                            confirmedHotels.add(ht);
                            }
                             
                        } 
                    }
                    itin.setStatus(StatusInfo.Status.CONFIRMED);
                       Response.status(Response.Status.OK).build();
                       
                } catch (Exception ex1) {
                       System.out.println("Booking hotels/flight exceptions");
                    //Logger.getLogger(ItineraryResource.class.getName()).log(Level.SEVERE, null, ex1);
                    //If for some reason flight/hotel booking is failed, and code executed in the catch
                    //then c ancel all the hotels/flights
  
                    booking = false;
                } 

                if (!booking) {
                    System.out.println("Canceling flights and hotels...");
                    
                    try {
                        
                            for (FlightsInfo fl2 : itin.getFlightDetails()) {
                                
                                for(FlightsInfo inner:confirmedFlights){
                                    System.out.println("confirmedFlights: " + inner.getFlightDetails().getNameAirline());
                                    if(fl2.getFlightDetails().getBookingNo()==inner.getFlightDetails().getBookingNo()){
                                         CancelFlightInput cfi = new CancelFlightInput();
                                cfi.setBookingNo(fl2.getFlightDetails().getBookingNo());
                                cfi.setCreditCardDetails(CardInfo);
                                cfi.setPrice(fl2.getFlightDetails().getPrice());
                                fl2.setStatus(StatusInfo.Status.CANCELLED);
                                        System.out.println("Status canceled" + fl2.getFlightDetails().getNameAirline());
                                cancelFlight(cfi);
                                    
                                    }
                                }
                                
                               
                                
 
                             
                            } 
                            for (HotelsInfo ht2 : itin.getHotelDetails()) {
                                System.out.println("Canceling hotels");
                                for(HotelsInfo hi:confirmedHotels){
                                    System.out.println("confirmed:" + hi.getHotelDetails().getBookingNR());
                                    if(ht2.getHotelDetails().getBookingNR()==hi.getHotelDetails().getBookingNR()){
                                    ht2.setStatus(StatusInfo.Status.CANCELLED);
                                cancelHotel(ht2.getHotelDetails().getBookingNR());
                                    
                                    }
                                
                                
                                }
                                

                              
                            }
                            
                           
                       // itin.setStatus(StatusInfo.Status.CANCELLED);
                        return Response.status(Response.Status.NOT_ACCEPTABLE).entity("FAILtoBOOK").build();

                    } catch (Exception ex) {
                        Logger.getLogger(ItineraryResource.class.getName()).log(Level.SEVERE, null, ex);
                           System.out.println("Canceling hotel/flights exception");
                        
                        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
                    }

                }

            } //if status of itinerary is cancelled or confirmed, then respond
            else if (itin.getStatus() == StatusInfo.Status.CANCELLED) {

                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            } else if (itin.getStatus() == StatusInfo.Status.CONFIRMED) {

                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }

        }
        return Response.status(Response.Status.OK).build();
    }

   @PUT
    @Path("/{id}/cancelItinerary")
    @Consumes(output)
    @Produces(output)
    public Response CancelItinerary(@PathParam("id") int id, CreditCardDetails CardInfo) {
        Itinerary itin = itineraryMap.get(id);
        System.out.println("cancelItinerary() executed");
        try {   
            if (itin.getStatus().equals(StatusInfo.Status.UNCONFIRMED)){
                System.out.println("Removing itinerary form the itineraryMap");
                itineraryMap.remove(id);
                return Response.status(Response.Status.OK).entity("Cancelled").build(); 
            }
            else if (itin.getStatus().equals(StatusInfo.Status.CONFIRMED)) {
                long timeStamp = new GregorianCalendar().getTimeInMillis();
                boolean canCancelFlights = true;

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timeStamp);

                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH) + 1;
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                System.out.println("TODAY WE HAVE MONTH NUMBER: " + mMonth);
                
                if (itin.getFlightDetails() != null) {
                    for (int i = 0; i < itin.getFlightDetails().size(); i++) {
                    long timeStamp2 = itin.getFlightDetails().get(i).getFlightDetails().getFlight().getLiftOffTime().toGregorianCalendar().getTimeInMillis();
                    if (timeStamp2<timeStamp) {
                        System.out.println("Timestamp2 : " + timeStamp2 + "  " + timeStamp);
                    
                    canCancelFlights = false;
                    }
                        
                        
                    }
                    if (canCancelFlights) {
                        System.out.println("I AM CANCELLING");
                        
                        for (HotelsInfo ht2 : itin.getHotelDetails()) {
                            ht2.setStatus(StatusInfo.Status.CANCELLED);
                            cancelHotel(ht2.getHotelDetails().getBookingNR() + " booking number is cancelled.");
                        }
                        
                        for (FlightsInfo fl2 : itin.getFlightDetails()) {
                            CancelFlightInput cfi = new CancelFlightInput();
                            cfi.setBookingNo(fl2.getFlightDetails().getBookingNo());
                            cfi.setCreditCardDetails(CardInfo);
                            cfi.setPrice(fl2.getFlightDetails().getPrice());
                            
                            System.out.println("Canceling flight: " + fl2.getFlightDetails().getFlight().getDestination());
                            cancelFlight(cfi);
                            fl2.setStatus(StatusInfo.Status.CANCELLED); 

                        }

                        

                        itin.setStatus(StatusInfo.Status.CANCELLED);
                        //itineraryMap.remove(id);
                        return Response.status(Response.Status.OK).entity("SuccesfullyCancelled").build();
                    }

                }
            }

        } catch (Exception ex) {
            Logger.getLogger(ItineraryResource.class.getName()).log(Level.SEVERE, null, ex);

            //all of hotels/flights are cancelled 
            return Response.status(Response.Status.BAD_REQUEST).entity("Cancelling is failed").build();
        }

        return Response.status(Response.Status.OK).build();
    }

    //due to different name in Hotels/Flighs CreditCard types, must be casted
    private CreditCardInformation fromFlightToHotelsCredit(CreditCardDetails CardInfo) {

        CreditCardInformation cdi = new CreditCardInformation();
        cdi.setCardNumber(CardInfo.getCardNumber());
        cdi.setHolderName(CardInfo.getHoldersName());
        ExpDate expDate = new ExpDate();
        expDate.setMonth(CardInfo.getExpirationDate().getMonth());
        expDate.setYear(CardInfo.getExpirationDate().getYear());
        cdi.setExpDate(expDate);

        return cdi;

    }

    private static boolean bookFlight(ws.lameduck.BookFlightInput input) throws BookFlightFaultMessage {
        ws.lameduck.LameDuckService service = new ws.lameduck.LameDuckService();
        ws.lameduck.LameDuckPortType port = service.getLameDuckPortTypeBindingPort();
        return port.bookFlight(input);
    }

    private static boolean cancelFlight(ws.lameduck.CancelFlightInput cancelFlightInput) throws CancelFlightFaultMessage {
        ws.lameduck.LameDuckService service = new ws.lameduck.LameDuckService();
        ws.lameduck.LameDuckPortType port = service.getLameDuckPortTypeBindingPort();
        return port.cancelFlight(cancelFlightInput);
    }

    private static boolean bookHotel(ws.niceview.BookHotelInput input2) throws BookHotelFault {
        ws.niceview.NiceViewService service = new ws.niceview.NiceViewService();
        ws.niceview.NiceViewPort port = service.getNiceViewPortBindingPort();
        return port.bookHotel(input2);
    }

    private static boolean cancelHotel(java.lang.String input2) throws CancelHotelFault {
        ws.niceview.NiceViewService service = new ws.niceview.NiceViewService();
        ws.niceview.NiceViewPort port = service.getNiceViewPortBindingPort();
        return port.cancelHotel(input2);
    }

}
