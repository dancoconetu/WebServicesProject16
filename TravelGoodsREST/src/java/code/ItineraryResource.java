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

//getting ID, which will be assigned to the iterinary
    private static int getID() {

        int id = itineraryMap.size() + 1;
        return id;
    }
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
            ir.setItinerary(iten);
            return Response.ok(ir).build();

        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Produces(output)
    public Response createItineraryEmpty() {
        int ID = getID();
        System.out.println("ID " + ID);
        Itinerary itinerary = new Itinerary();
        itinerary.setStatus(StatusInfo.Status.UNCONFIRMED);
        itinerary.setFlightDetails(null);
        itinerary.setHotelDetails(null);
        itineraryMap.put(ID, itinerary);
        ItinararyRepr ir = new ItinararyRepr();
        ir.setItinerary(itinerary);
        System.out.println("creatItenerary executed!!@@@");

        return Response.status(Response.Status.OK).entity("Itinerary created with ID of: " + ID).build();

    }

@POST
    @Path("/{id}/hotels")
    @Consumes(output)
    @Produces(output)
    public Response addHotelToItinerary(@PathParam("id") int id, HotelInformationListLocal hotelsList) {
        if (itineraryMap.containsKey(id)) {
            Itinerary itin = itineraryMap.get(id);
            System.out.println("Found an ID");
            //if the status of itinerary is unconfirmed then add flights
            if (itin.getStatus().equals(StatusInfo.Status.UNCONFIRMED)) {

                List<HotelsInfo> list = new ArrayList<>();
   
                for (HotelType hotelLoop : hotelsList.getList()) {
                    System.out.println("price for hotel" + hotelLoop.getAdress());
                    HotelsInfo hotelLocalLoop = new HotelsInfo();
                    hotelLocalLoop.setStatus(StatusInfo.Status.UNCONFIRMED);
                    hotelLocalLoop.setHotelDetails(null);
                    list.add(hotelLocalLoop);
                }

                itin.setHotelDetails(list);
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
            System.out.println("Found an ID");
            //if the status of itinerary is unconfirmed then add flights
            if (itin.getStatus().equals(StatusInfo.Status.UNCONFIRMED)) {

                List<FlightsInfo> list = new ArrayList<>();

                for (FlightInformation fl : flightL.getList()) {
                    System.out.println("price " + fl.getPrice());
                    FlightsInfo FI = new FlightsInfo();
                    FI.setStatus(StatusInfo.Status.UNCONFIRMED);
                    FI.setFlightDetails(fl);
                    list.add(FI);
                }

                itin.setFlightDetails(list);
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
            //start booking if itnierary status is unconfirmed
            if (itin.getStatus() == StatusInfo.Status.UNCONFIRMED) {
                System.out.println("itinerary is Unconfirmed");
                //for each flight/hotel connect to soap and and book them
                try {
                    if(itin.getFlightDetails().size()>0){
                    for (FlightsInfo fl : itin.getFlightDetails()) {
                        System.out.println("Booking flights: " + fl.getFlightDetails().getNameAirline());
                        BookFlightInput gfi = new BookFlightInput();
                        gfi.setBookingNo(fl.getFlightDetails().getBookingNo());
                        gfi.setCreditCardDetails(CardInfo);

                        bookFlight(gfi);
                        System.out.println("book flight set to Confirmed");
                        fl.setStatus(StatusInfo.Status.CONFIRMED);

                    }
                    }
                    if(itin.getHotelDetails().size()>0){
                    for(HotelsInfo ht : itin.getHotelDetails()){
                        
                    
                    BookHotelInput bhi =  new BookHotelInput();
                    bhi.setBookingNR(ht.getHotelDetails().getBookingNR());
                    bhi.setCreditCardInformation(fromFlightToHotelsCredit(CardInfo));
                        bookHotel(bhi);
                    ht.setStatus(StatusInfo.Status.CONFIRMED);
                    }
                    }
                    
                } catch (Exception ex1) {

                    Logger.getLogger(ItineraryResource.class.getName()).log(Level.SEVERE, null, ex1);
                        //If for some reason flight/hotel booking is failed, and code executed in the catch
                    //then cancel all the hotels/flights

                    booking = false;
                }

                if (!booking) {
                    try {
                        if(itin.getFlightDetails().size()>0){
                    for (FlightsInfo fl2 : itin.getFlightDetails()) {
                        CancelFlightInput cfi = new CancelFlightInput();
                        cfi.setBookingNo(fl2.getFlightDetails().getBookingNo());
                        cfi.setCreditCardDetails(CardInfo);
                        cfi.setPrice(fl2.getFlightDetails().getPrice());
                        fl2.setStatus(StatusInfo.Status.UNCONFIRMED);
                        cancelFlight(cfi);
                        System.out.println("Canceling: " + fl2.getStatus());
                        
                    }
                        }
                      if(itin.getHotelDetails().size()>0){
                    for(HotelsInfo ht2 : itin.getHotelDetails()){
                        
                    
                    
                    ht2.setStatus(StatusInfo.Status.UNCONFIRMED);
                    }
                        }      

                        } catch (Exception ex) {
                            Logger.getLogger(ItineraryResource.class.getName()).log(Level.SEVERE, null, ex);
                        
                        //all of hotels/flights are cancelled
                        return Response.status(Response.Status.OK).build();
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
    
    //due to different name in Hotels/Flighs CreditCard types, must be casted
    private CreditCardInformation fromFlightToHotelsCredit(CreditCardDetails CardInfo){
    
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
