/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NiceViewService;

import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;
import ws.niceview.BookHotelFault;
import ws.niceview.CancelHotelFault;
import ws.niceview.*;
/**
 *
 * @author Dan
 */
@WebService(serviceName = "NiceViewService", portName = "NiceViewPortBindingPort", endpointInterface = "ws.niceview.NiceViewPort", targetNamespace = "http://NiceView.WS", wsdlLocation = "WEB-INF/wsdl/NiceViewService/NiceView.wsdl")
public class NiceViewService {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    private BankService service;

     public HotelListType getHotelList(GetHotelInput getHotelInputs) {
  
        HotelListType h = new HotelListType();
        
        HotelType hotelA = new HotelType();
        
            hotelA.setAdress("mainRoad 1");
            hotelA.setBookingNR("A44");
            hotelA.setCreditCardNeeded(true);
            hotelA.setHotelName("plaza hotel");
            hotelA.setReservationService("hotelsRes");
            hotelA.setTotalPrice(3000);
            
        h.getNewElement().add(hotelA);
        
        return h;
    }   

    public boolean bookHotel(ws.niceview.BookHotelInput bookHotelInput) throws BookHotelFault {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean cancelHotel(java.lang.String bookingNr) throws CancelHotelFault {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
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
