/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelpClasses;
import javax.xml.bind.annotation.XmlRootElement;
import ws.niceview.*;

/**
 *
 * @author justinas
 */
@XmlRootElement
public class HotelsInfo {
 
    private StatusInfo.Status status;
    private HotelInformation hotelDetails;

    public HotelInformation getHotelDetails() {
        return hotelDetails;
    }

    public void setHotelDetails(HotelInformation hotelDetails) {
        this.hotelDetails = hotelDetails;
    }

    public StatusInfo.Status getStatus() {
        
        
        return status;
    }

    public void setStatus(StatusInfo.Status status) {
        this.status = status;
    }
    
    
    
    
}
