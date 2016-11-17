/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HotelTry;

import hotelclient.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.datatype.DatatypeConfigurationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.niceview.*;
/**
 *
 * @author Lalli
 */
public class HotelTry {

    public HotelTry() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    @Test
    public void testHotelListType() throws DatatypeConfigurationException, GetHotelListFault, ParseException {

        GetHotelInput hinp = new GetHotelInput();  
        hinp = new GetHotelInput();
        hinp.setCity("Copenhagen");
        
        GregorianCalendar x = new GregorianCalendar(2016, 11, 1, 14, 0, 0);
        XMLGregorianCalendar date1 = null;
        date1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(x);
        hinp.setArrival(date1);

        GregorianCalendar x2 = new GregorianCalendar(2016, 11, 5, 14, 0, 0);
        XMLGregorianCalendar date2 = null;
        date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(x2);
        hinp.setDeparture(date2);

        GetHotelOutput h = getHotelsList(hinp);
//               
        List<HotelType> hot = h.getHotelType();

        if (hot.size() > 0) {
            System.out.println("List of hotels in : " + hinp.getCity());

            for (HotelType bla : hot) {
                String creditCardNeeded = "Not needed";
                if (bla.isCreditCardNeeded() == true) {
                    creditCardNeeded = "Needed";
                }
                System.out.println("Name : " + bla.getHotelName() + " Adress: " + bla.getAdress() + " BookingNR: " + bla.getBookingNR() + " TotalPrice: " + bla.getPrice() + " CreditCard is : " + creditCardNeeded + " Reservation Service : " + bla.getReservationService());
            }
        } else {
            System.out.println("No hotel found in : " + hinp.getCity());
        }

    }
    
      @Test
      public void bookAssert() throws BookHotelFault{
          
          boolean sucess = false;
          
          BookHotelInput inp = new BookHotelInput();
          CreditCardInformation creditCard = new CreditCardInformation();
          ExpDate expdate = new ExpDate();
          expdate.setMonth(7);
          expdate.setYear(9);
          creditCard.setCardNumber("50408822");
          creditCard.setExpDate(expdate);
          creditCard.setHolderName("Bech Camilla");
          inp.setBookingNR("333H");

         inp.setCreditCardInformation(creditCard);
        
          sucess = bookHotel(inp);
          
          System.out.println("it was a "+sucess);
}
    @Test 
    public void CancelHotelTry() throws CancelHotelFault{
        
        System.out.println("Cancelation was : " + cancelHotel("333H"));
        
    }

    private static boolean bookHotel(ws.niceview.BookHotelInput input2) throws BookHotelFault {
        ws.niceview.NiceViewService service = new ws.niceview.NiceViewService();
        ws.niceview.NiceViewPort port = service.getNiceViewPortBindingPort();
        return port.bookHotel(input2);
    }

    private static boolean cancelHotel(java.lang.String input2) throws CancelHotelFault {
        ws.niceview.NiceViewService service = new ws.niceview.NiceViewService();
        ws.niceview.NiceViewPort port = service.getNiceViewPortBindingPort();
        return port.cancelHotel(input2);
    }

    private static GetHotelOutput getHotelsList(ws.niceview.GetHotelInput input1) throws GetHotelListFault {
        ws.niceview.NiceViewService service = new ws.niceview.NiceViewService();
        ws.niceview.NiceViewPort port = service.getNiceViewPortBindingPort();
        return port.getHotelsList(input1);
    }










}
