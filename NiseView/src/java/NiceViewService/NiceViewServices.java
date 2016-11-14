/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NiceViewService;

import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;
import ws.niceview.*;

/**
 *
 * @author Lalli
 */
@WebService(serviceName = "NiceViewService", portName = "NiceViewPortBindingPort", endpointInterface = "ws.niceview.NiceViewPort", targetNamespace = "http://NiceView.WS", wsdlLocation = "WEB-INF/wsdl/NiceViewServices/NiceView.wsdl")
public class NiceViewServices {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    private BankService service;

    public boolean cancelHotel(java.lang.String input2) throws CancelHotelFault {
          
        boolean sucess = false;
        String bookingNR = input2;

        HotelInformationList hList = new HotelInformationList();
        hList = getAllHotels();
        List<HotelInformation> hotelInformation = hList.getHotelInformation();
        
        for (HotelInformation currHotel : hotelInformation) {
            
            if(currHotel.getBookingNR().equals(bookingNR)){
                sucess=true;
            }
            
        }

        return sucess;
    }

    public boolean bookHotel(ws.niceview.BookHotelInput input3) throws BookHotelFault, CreditCardFaultMessage {
 
        boolean sucess = false;
        String bookingNR = input3.getBookingNR();
        
        
        HotelInformationList hList = new HotelInformationList();
        hList = getAllHotels();
        List<HotelInformation> hotelInformation = hList.getHotelInformation();
        
        for (HotelInformation currHotel : hotelInformation) {
            
            if(currHotel.getBookingNR().equals(bookingNR)){
                //creditCard needed
               if(currHotel.isCreditCardReqiured()==true){
                   
                   //check if credit card is valid
                        CreditCardInfoType CDD = new CreditCardInfoType();
                        
                        CDD.setNumber(input3.getCreditCardInformation().getCardNumber());
                        
                        CreditCardInfoType.ExpirationDate expirationDate = new CreditCardInfoType.ExpirationDate();
                        expirationDate.setMonth(input3.getCreditCardInformation().getExpDate().getMonth());
                        expirationDate.setYear(input3.getCreditCardInformation().getExpDate().getYear());   
                   
                        CDD.setExpirationDate(expirationDate);
                        CDD.setName(input3.getCreditCardInformation().getHolderName());
                        
                        
                        sucess = validateCreditCard(16, CDD, (int) currHotel.getPricePerNight());                                 
               }
               else{
                   //hotel booked
                   sucess=true;
               }
                
                
            }

        }
        
        return sucess;
        
    }
    
     public GetHotelOutput getHotelsList(GetHotelInput input1) throws GetHotelListFault {

        GetHotelOutput ghout = new GetHotelOutput();
        
        HotelInformationList hList = new HotelInformationList();
        hList = getAllHotels();
       
        ghout = filter(hList, input1);

        return ghout;
    }

    public HotelInformation generateHotel(String name, String city, String bookingNR, double pricePerNight, String adress, boolean credidCardNeeded, String hotelReservationService) {

        HotelInformation h = new HotelInformation();
        h.setName(name);
        h.setCity(city);
        h.setBookingNR(bookingNR);
        h.setPricePerNight(pricePerNight);
        h.setAdress(adress);
        h.setCreditCardReqiured(credidCardNeeded);
        h.setHotelReservationService(hotelReservationService);
        
        return h;
    }

    public GetHotelOutput filter(HotelInformationList hilist, GetHotelInput filterParameter) {

        GetHotelOutput gho = new GetHotelOutput();
        List<HotelInformation> hotelInformation = hilist.getHotelInformation();
        Date dateFrom = filterParameter.getArrival().toGregorianCalendar().getTime();
        Date dateTo = filterParameter.getDeparture().toGregorianCalendar().getTime();

        for (HotelInformation bla : hotelInformation) {
            if (bla.getCity().equals(filterParameter.getCity())) {
                HotelType h = new HotelType();
                h.setAdress(bla.getAdress());
                h.setBookingNR(bla.getBookingNR());
                h.setCreditCardNeeded(bla.isCreditCardReqiured());
                h.setHotelName(bla.getName());
                h.setPrice(bla.getPricePerNight()*NRofDates(dateFrom, dateTo));
                h.setReservationService(bla.getHotelReservationService());

                gho.getHotelType().add(h);
            }
        }

        return gho;
    }
    
        public int NRofDates(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
    }

    private HotelInformationList getAllHotels(){
        
        HotelInformationList hList = new HotelInformationList();
        hList.getHotelInformation().add(generateHotel("plaza", "Copenhagen", "343H", 300.00, "Center street 1", true, "bookFriend"));
        hList.getHotelInformation().add(generateHotel("Radison", "Copenhagen", "323H", 150.00, "Main street 3", true, "bookAsleep"));
        hList.getHotelInformation().add(generateHotel("RedHotel", "Amsterdam", "333H", 100.00, "Sea Street 5", true, "bookFriend"));
        hList.getHotelInformation().add(generateHotel("centerCube", "Amsterdam", "119H", 130.00, "Dock street 1", false, "bookFriend"));
        hList.getHotelInformation().add(generateHotel("hilton", "Copenhagen", "669H", 200.00, "Main street 5", false, "bookAsleep"));
        hList.getHotelInformation().add(generateHotel("Sleepster", "Copenhagen", "433H", 80.00, "HotelStreet 8", true, "bookFriend"));
        hList.getHotelInformation().add(generateHotel("Norwayss", "Oslo", "998H", 500.00, "Luxury street 7", true, "bookAsleep"));
        hList.getHotelInformation().add(generateHotel("Luxury stay", "Los Angeles", "554H", 150.00, "Delux street 8", false, "bookFriend"));

        return hList;
    }

    private boolean validateCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }


    
    
   
    
    
    
    
}
