/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NiceView;

import javax.jws.WebService;
import ws.niceview.*;
/**
 *
 * @author Lalli
 */
@WebService(serviceName = "NiceViewService", portName = "getHotelPortBindingPort", endpointInterface = "ws.niceview.GetHotelPort", targetNamespace = "http://NiceView.WS", wsdlLocation = "WEB-INF/wsdl/niceViewService/NiceView.wsdl")
public class niceViewService {

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
    
}
