/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelpClasses;
import javax.xml.bind.annotation.XmlRootElement;
import ws.lameduck.*;


/**
 *
 * @author justinas
 */
@XmlRootElement
public class FlightsInfo {
    private FlightInformation flightDetails;
    private StatusInfo.Status status;

    public FlightInformation getFlightDetails() {
        return flightDetails;
    }

    public void setFlightDetails(FlightInformation flightDetails) {
        this.flightDetails = flightDetails;
    }

    public StatusInfo.Status getStatus() {
        return status;
    }

    public void setStatus(StatusInfo.Status status) {
        this.status = status;
    }
    
    
    
    
}
