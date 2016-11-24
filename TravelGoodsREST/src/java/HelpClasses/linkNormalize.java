/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelpClasses;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author justinas
 */
public class linkNormalize {
    
    public List<Link> add(StatusInfo.Status status,int itineraryID){
    
        
        List <Link> links = new ArrayList<Link>();
        String mainPath = "http://localhost:9090/TravelGoodsREST/" ;
        String itinPath = mainPath + "itinerary/" +itineraryID + "/";
        
        //if status is confirmed of itinierary then add cancel link
        if(status == StatusInfo.Status.CONFIRMED){
        
            Link link = new Link();
        
			link.setRel("http://itinerary/"+itineraryID +"/cancelItinerary");
			link.setUri(itinPath + "cancelItinerary");
			links.add(link);
        }
        
        if(status == StatusInfo.Status.UNCONFIRMED){
			
			// Add hotels to itinerary
			Link link = new Link();
			link.setRel("http://itinerary/hotels/add");
                        
			link.setUri(itinPath + "hotels");
                        
			links.add(link);
			
			
			// add flight into itinerary
			link = new Link();
			link.setRel("http://itinerary/flights/add");
                        
			link.setUri(itinPath + "flights");
			links.add(link);
			
			//retrieve all the available hotels
			link = new Link();
			link.setRel("http://AllHotels");
			link.setUri(mainPath+ "hotels");
			links.add(link);
			
			// link to view possible flights
			link = new Link();
			link.setRel("http://AllFlights");
			link.setUri(mainPath+ "flights");
			links.add(link);
                        
                         //Add link to book itinirerary
			link = new Link();
			link.setRel("http://itinerary/"+ itineraryID + "/BookItinerary");
			link.setUri(itinPath);
                       
			links.add(link);
        }
		
		
    return links;
    }
    
    
}
