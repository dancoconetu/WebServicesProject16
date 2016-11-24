/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelpClasses;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author justinas
 */
@XmlRootElement
public class ItinararyRepr {
    private Itinerary itinerary;
    private List<Link> linkList;

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }
    
    public void addLinks(Itinerary itinerary, int itineraryID){
    
        setItinerary(itinerary);
        linkNormalize linkNormalize1 = new linkNormalize();
       linkList = linkNormalize1.add(itinerary.getStatus(), itineraryID);
    
    }
}
