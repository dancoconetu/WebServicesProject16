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
       private final static String MEDIATYPE = "application/xml";

//getting ID, which will be assigned to the iterinary
    private static int getID(){
    
    int id = itineraryMap.size() + 1;
    return id;
    } 
    // http://localhost:8080/TravelGoodsREST/itinerary/1
    
    	@GET
	@Produces(MEDIATYPE)
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
		//return Response.status(Response.Status.OK).build();
             
		return Response.status(Response.Status.NOT_FOUND).build();
	}
       
     
    @PUT
    @Produces(MEDIATYPE)
    public Response createItinerary() {
        int ID = getID();
        Itinerary itinerary = new Itinerary();
        itinerary.setStatus(StatusInfo.Status.UNCONFIRMED);
        itinerary.setFlightDetails(null);
        itinerary.setHotelDetails(null);
        itineraryMap.put(ID, itinerary);
        ItinararyRepr ir = new ItinararyRepr();
        ir.setItinerary(itinerary);
        
        
        
        
        
//        addGetFlightsLink(userid, itineraryid, statusRep);
//        addGetHotelsLink(userid, itineraryid, statusRep);
//        addGetItineraryLink(userid, itineraryid, statusRep);
        return  Response.status(Response.Status.OK).entity(ir).build();
        //return Response.ok(statusRep).build();
    } 
}
