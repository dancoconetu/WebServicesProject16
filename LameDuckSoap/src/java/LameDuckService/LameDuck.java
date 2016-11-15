/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LameDuckService;

import dk.dtu.imm.fastmoney.*;
import dk.dtu.imm.fastmoney.types.AccountType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;
import ws.lameduck.BookFlightFaultMessage;
import ws.lameduck.BookFlightInput;
import ws.lameduck.CancelFlightFaultMessage;
import ws.lameduck.CancelFlightInput;
import ws.lameduck.Flight;
import ws.lameduck.FlightInformation;
import ws.lameduck.GetFlightsInput;
import ws.lameduck.GetFlightsOutput;

/**
 *
 * @author justinas
 */
@WebService(serviceName = "LameDuckService", portName = "LameDuckPortTypeBindingPort", endpointInterface = "ws.lameduck.LameDuckPortType", targetNamespace = "http://LameDuck.ws", wsdlLocation = "WEB-INF/wsdl/LameDuck/LameDuck.wsdl")
public class LameDuck {

   @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    private BankService service;
    
    //Bank detail information used to connect to bank services
    private static final AccountType accountInfo= new AccountType();
    static {
    accountInfo.setName("SleepDeep");
    accountInfo.setNumber("50308814");
    
    }

    
private static Flight addToFlightList(String startCity, String endCity, String carrier,GregorianCalendar departureDate, GregorianCalendar arrivalDate) throws DatatypeConfigurationException{
    
    //Converting to correct XML date
            XMLGregorianCalendar departureDate1, arrivalDate1;
        
            departureDate1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(departureDate);
            arrivalDate1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(arrivalDate);
       
    Flight flight = new Flight();
    flight.setCarrier(carrier);
    flight.setDestination(endCity);
    flight.setStartAirport(startCity);
    flight.setLandingTime(departureDate1);
    flight.setLiftOffTime(arrivalDate1);

return flight;
}

    private static List<FlightInformation> flightsList() throws DatatypeConfigurationException{
    List<FlightInformation> list = new ArrayList<FlightInformation>();
    
    
    FlightInformation FI = new FlightInformation();
    FI.setFlight(addToFlightList("Riga","Madrid","Ryanair",new GregorianCalendar(2016, 11, 1, 19, 0, 0),new GregorianCalendar(2016, 11, 1, 23, 50, 0)));
    FI.setBookingNo("1");
    FI.setNameAirline("GerLine");
    FI.setPrice(2000);
    list.add(FI);
    FlightInformation FI1 = new FlightInformation();
    FI1.setFlight(addToFlightList("Riga","Copenhagen","easyJet",new GregorianCalendar(2016, 11, 1, 14, 0, 0),new GregorianCalendar(2016, 11, 1, 18, 0, 0)));
    FI1.setBookingNo("2");
    FI1.setNameAirline("BEline");
    FI1.setPrice(200);
    list.add(FI1);
    FlightInformation FI2 = new FlightInformation();
    FI2.setFlight(addToFlightList("Riga","Dublin","SAS",new GregorianCalendar(2016, 10, 10, 14, 0, 0),new GregorianCalendar(2016, 10, 10, 2, 25, 0)));
    FI2.setBookingNo("3");
    FI2.setNameAirline("ZELine");
    FI2.setPrice(560);
    list.add(FI2);
    
    FlightInformation FI3 = new FlightInformation();
    FI3.setFlight(addToFlightList("Riga","Madrid","BolivianAir",new GregorianCalendar(2016, 11, 1, 19, 0, 0),new GregorianCalendar(2016, 11, 1, 10, 51, 0)));
    FI3.setBookingNo("4");
    FI3.setNameAirline("EstLine");
    FI3.setPrice(350);
    list.add(FI3);
    return list;
    
    }

    public GetFlightsOutput getFlights(GetFlightsInput getFlightsInput) throws DatatypeConfigurationException {
        List<FlightInformation> flights = flightsList();
        
         GetFlightsOutput object = new  GetFlightsOutput();

             for(FlightInformation result: flights){
                 
                 if(result.getFlight().getStartAirport().equals(getFlightsInput.getStartAirport()) && result.getFlight().getDestination().equals(getFlightsInput.getDestination())){
                     
                     if(result.getFlight().getLiftOffTime().getDay() == getFlightsInput.getFlightDate().getDay()
                        && result.getFlight().getLiftOffTime().getMonth() == getFlightsInput.getFlightDate().getMonth()
                             && result.getFlight().getLiftOffTime().getYear() == getFlightsInput.getFlightDate().getYear()
                             ){
                         System.out.println("Last of up: " +  result.getFlight().getCarrier());
                     object.getFlightInformation().add(result);
                     }
                 
                 }
              
}
         
         return object;
    }
    
     
    public boolean bookFlight(BookFlightInput input) throws BookFlightFaultMessage, DatatypeConfigurationException {
        List<FlightInformation> list = flightsList();
        
        //Create an object of creditCardInfo
        
        CreditCardInfoType CDD = new CreditCardInfoType();
        CDD.setNumber(input.getCreditCardDetails().getCardNumber());
        CreditCardInfoType.ExpirationDate expirationDate = new CreditCardInfoType.ExpirationDate();
        expirationDate.setMonth(input.getCreditCardDetails().getExpirationDate().getMonth());
        expirationDate.setYear(input.getCreditCardDetails().getExpirationDate().getYear());
         
        CDD.setExpirationDate(expirationDate);
        CDD.setName(input.getCreditCardDetails().getHoldersName());
        
        for(FlightInformation result: list){
            
            if(input.getBookingNo().equals(result.getBookingNo())){
                System.out.println("Booking are equal: " + result.getBookingNo());
                try {
                    chargeCreditCard(16,CDD, result.getPrice(), accountInfo);
                    System.out.println("TRYING charge credit Card");
                    
                    return true;
                } catch (CreditCardFaultMessage ex) {
                    Logger.getLogger(LameDuck.class.getName()).log(Level.SEVERE, null, ex);
                    throw new BookFlightFaultMessage("Failed to charge CreditCard: " + ex.getMessage(), null);
                    
                }
            }
        
                }
        
        throw new BookFlightFaultMessage("Error Flight Booking no: " +input.getBookingNo() + " doesnt exists",null);
    }

    public boolean cancelFlight(CancelFlightInput cancelFlightInput) throws CancelFlightFaultMessage {
        
dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo = new dk.dtu.imm.fastmoney.types.CreditCardInfoType();
        dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate expirationDate =new dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate();
        expirationDate.setMonth(cancelFlightInput.getCreditCardDetails().getExpirationDate().getMonth());
        expirationDate.setYear(cancelFlightInput.getCreditCardDetails().getExpirationDate().getYear());
        creditCardInfo.setExpirationDate(expirationDate);
        creditCardInfo.setName(cancelFlightInput.getCreditCardDetails().getHoldersName());
        creditCardInfo.setNumber(cancelFlightInput.getCreditCardDetails().getCardNumber());
        
        try{
            refundCreditCard(16, creditCardInfo, cancelFlightInput.getPrice()/2, accountInfo);
        }
        catch (CreditCardFaultMessage ex) {
            throw new CancelFlightFaultMessage("Flight cancellation failed!", ex.getFaultInfo().getMessage());
        }
            return true;
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
