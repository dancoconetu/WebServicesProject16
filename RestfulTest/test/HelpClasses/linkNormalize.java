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
        
//        Link link = new Link();
//        
//			link.setRel("http://itinerary/"+itineraryID +"/cancelItinerary");
//			link.setUri(itinPath + "cancelItinerary");
//			links.add(link);
        }
		
		
    return links;
    }
    
    
}
