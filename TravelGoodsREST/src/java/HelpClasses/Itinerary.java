/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelpClasses;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ws.lameduck.*;
import ws.niceview.*;

/**
 *
 * @author justinas
 */
@XmlRootElement
public class Itinerary {
    
    private List<FlightsInfo> flightDetails;
    private List<HotelsInfo> HotelDetails;
    private StatusInfo.Status status;

    public List<FlightsInfo> getFlightDetails() {
        return flightDetails;
    }

    public void setFlightDetails(List<FlightsInfo> flightDetails) {
        this.flightDetails = flightDetails;
    }

    public List<HotelsInfo> getHotelDetails() {
        return HotelDetails;
    }

    public void setHotelDetails(List<HotelsInfo> HotelDetails) {
        this.HotelDetails = HotelDetails;
    }

    public StatusInfo.Status getStatus() {
        return status;
    }

    public void setStatus(StatusInfo.Status status) {
        this.status = status;
    }
    
}
