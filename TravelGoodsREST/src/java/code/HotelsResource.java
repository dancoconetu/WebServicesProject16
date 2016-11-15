package code;

import HelpClasses.HotelInformationList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.lameduck.FlightInformation;
import ws.niceview.*;
import ws.niceview.*;



@Path("/hotels")
public class HotelsResource {

    @Path("/HotelInfomration")
    public Response getHotels(@QueryParam("city") String city,
            //This is stupid, why we need specify start date and end date for displaying only list of hotels...
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate)
            throws DatatypeConfigurationException, ParseException, GetHotelListFault {

        //http://localhost:8080/TravelGoodsREST/hotels/HotelInfomration?city=Copenhagen&startDate=2016-11-1 14:00&endDate=2016-11-5%2014:00
        XMLGregorianCalendar startDateGregorianFormat = TimeFormat.getDateFromString(startDate);
        XMLGregorianCalendar endDateGregorianFormat = TimeFormat.getDateFromString(endDate);

        
        GetHotelInput hinp = new GetHotelInput();  
        hinp.setCity(city);
        hinp.setArrival(startDateGregorianFormat);
        hinp.setDeparture(endDateGregorianFormat);
        
        List<HotelType> list = new ArrayList<HotelType>();
        list = getHotelsList(hinp).getHotelType();
        
        System.out.println(list.get(2).getAdress());  //It works but return below shows 404
        
        if (list.isEmpty()) {
            System.out.println("No hotels found");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        HotelInformationList hotelInformationList = new HotelInformationList();
        hotelInformationList.setList(list);

        return Response.status(Response.Status.OK).entity(hotelInformationList).build();
    }

    private static GetHotelOutput getHotelsList(GetHotelInput input1) throws GetHotelListFault {
        NiceViewService service = new ws.niceview.NiceViewService();
        NiceViewPort port = service.getNiceViewPort();
        return port.getHotelsList(input1);
    }

}
