/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ws.lameduck.*;
import ws.niceview.*;
import HelpClasses.*;
import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author justinas
 */
@Path("/itinerary")
public class ItineraryResource {
    
       public static HashMap<Integer,Itinerary> itineraryMap = new HashMap<>();
       private final static String output = "application/xml";

//getting ID, which will be assigned to the iterinary
    private static int getID(){
    
    int id = itineraryMap.size() + 1;
    return id;
    } 
    // http://localhost:9090/TravelGoodsREST/itinerary/x
    
    	@GET
	@Produces(output)
	@Path("/{id}")
	public Response getStatus(@PathParam("id")Integer itineraryID) {
            
            //check if there is a record in the map of requested ID
            if(itineraryMap.containsKey(itineraryID)){
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
        
//        addGetFlightsLink(userid, itineraryid, statusRep);
//        addGetHotelsLink(userid, itineraryid, statusRep);
//        addGetItineraryLink(userid, itineraryid, statusRep);
        return  Response.status(Response.Status.OK).entity(ir).build();
        
    } 
    
    @POST
	@Produces(output)
	@Path("/{id}/flights")
	public Response addFlightToItinerary(@PathParam("id")Integer itineraryID, FlightInformationList flightL) {
		      System.out.println("addFlighttoItenerary() executed");
            //if ID is found in the hashmap then retrieve itinerary
            if(itineraryMap.containsKey(itineraryID)){
            Itinerary itin = itineraryMap.get(itineraryID);
                System.out.println("Found an ID");
            //if the status of itinerary is unconfirmed then add flights
            if(itin.getStatus().equals(StatusInfo.Status.UNCONFIRMED)){
            FlightsInfo FI = new FlightsInfo(); 
            FlightsInfo FI2 = new FlightsInfo(); 
               
              
for(FlightInformation fl: flightL.getList()){
 System.out.println("price " + fl.getPrice());
 //FI.setFlightDetails(fl);

}
            FI2.setFlightDetails(flightL.getList().get(0));
            FI.setFlightDetails(flightL.getList().get(1));
            FI.setStatus(StatusInfo.Status.UNCONFIRMED);
            FI2.setStatus(StatusInfo.Status.UNCONFIRMED);
            List<FlightsInfo> list = new ArrayList<FlightsInfo>();
            list.add(FI);
            list.add(FI2);
            itin.setFlightDetails(list);
		itineraryMap.put(itineraryID, itin);
                System.out.println("Itinerary id: " + itineraryID + " Has been added");
                return Response.status(Response.Status.OK).build();
            }
            
            }
//            if (itineraries.containsKey(id)) {
//			Itinerary i = itineraries.get(id);
//			if (i.getStatus() == Status.UNCONFIRMED) {
//				i.addFlight(flight);
//				return Response.ok("Flight added").build();
//			} else {
//				return Response.status(Response.Status.BAD_REQUEST).entity("Can't add flight to booked/cancelled itinerary").build();
//			}
//		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
