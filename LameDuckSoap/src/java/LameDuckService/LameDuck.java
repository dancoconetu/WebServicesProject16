/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LameDuckService;

import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.imm.fastmoney.types.AccountType;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import sun.util.calendar.LocalGregorianCalendar.Date;
import ws.lameduck.*;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;
/**
 *
 * @author Dan
 */
@WebService(serviceName = "LameDuckService", portName = "LameDuckPortTypeBindingPort", endpointInterface = "ws.lameduck.LameDuckPortType", targetNamespace = "http://LameDuck.ws", wsdlLocation = "WEB-INF/wsdl/LameDuck/LameDuck.wsdl")
public class LameDuck {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    private BankService service;
    


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

    private boolean chargeCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

    private boolean refundCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }

    private boolean validateCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }
    
    
    
}
