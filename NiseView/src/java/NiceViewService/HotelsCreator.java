/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NiceViewService;

import java.util.ArrayList;
import ws.niceview.HotelType;

/**
 *
 * @author Dan
 *     @XmlElement(required = true)
    protected String hotelName;
    @XmlElement(required = true)
    protected String adress;
    @XmlElement(required = true)
    protected String bookingNR;
    protected double totalPrice;
    protected boolean creditCardNeeded;
    @XmlElement(required = true)
    protected String reservationService;
 */
public class HotelsCreator {
    public  static ArrayList<HotelType> createHotels()
    {
        ArrayList<HotelType> hotels = new ArrayList<HotelType>();
        HotelType hotel = new HotelType("Casa Nova", "Rebaek Sopark", "12345", 650, true, "Hero");
        hotels.add(hotel);
        return hotels;
    
    }
    
}
