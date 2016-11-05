/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LameDuckService;

import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import sun.util.calendar.LocalGregorianCalendar.Date;
import ws.lameduck.*;
import javax.xml.datatype.XMLGregorianCalendar;
/**
 *
 * @author Dan
 */
@WebService(serviceName = "LameDuckService", portName = "LameDuckPortTypeBindingPort", endpointInterface = "ws.lameduck.LameDuckPortType", targetNamespace = "http://LameDuck.ws", wsdlLocation = "WEB-INF/wsdl/LameDuck/LameDuck.wsdl")
public class LameDuck {

    public GetFlightsOutput getFlights(GetFlightsInput getFlightsInput) {
        //TODO implement this method
         GetFlightsOutput object = new  GetFlightsOutput();
         FlightInformation flightInformation = new FlightInformation();
         Flight flight = new Flight();
         flight.setCarrier("Sas");
         flight.setDestination("Japan");
         GregorianCalendar x = new GregorianCalendar(2016,1,5, 17, 0,0);
        XMLGregorianCalendar date2 = null;
         try {
           date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(x);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(LameDuck.class.getName()).log(Level.SEVERE, null, ex);
        }
         flight.setLandingTime(date2);
         flight.setLiftOffTime(date2);
         
         flightInformation.setFlight(flight);
         flightInformation.setBookingNo("1");
         flightInformation.setNameAirline("FUCK YOU");
         flightInformation.setPrice(1000);
         object.getFlightInformation().add(flightInformation);
         return object;
    }
    
}
