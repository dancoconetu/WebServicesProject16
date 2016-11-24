package code;

import HelpClasses.FlightInformationList;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import ws.lameduck.*;

@Path("/flights")
public class FlightsResource {

        
    @GET
    @Produces("application/xml")
    // Two ways to parse data over the URL: QueryParam are multivariable after "?" in ULR or PathParam variables betewen "/.../".
    public Response getFlights(@QueryParam("departureCity") String departureCity,
            @QueryParam("destinationCity") String destinationCity,
            @QueryParam("date") String date)
            throws DatatypeConfigurationException, ParserConfigurationException, IOException, SAXException, ParseException {

//        http://localhost:8080/TravelGoodsREST/flights/flightInformation?departureCity=Riga&destinationCity=Madrid&date=2016-11-1%2014:00
        //convert to XML gregorianCalendar
        
        
        XMLGregorianCalendar xmlDate = TimeFormat.getDateFromString(date);
          
        GetFlightsInput gfi = new GetFlightsInput();
        gfi.setStartAirport(departureCity);
        gfi.setDestination(destinationCity);
        gfi.setFlightDate(xmlDate);
            
        List<FlightInformation> list = getFlights(gfi).getFlightInformation();
        
        
        //If there is no flights available return response status to notFound
        if (list.size()<1) {
            System.out.println("getFlights from LameDuck: no FlightFound");
            
            return Response.status(Response.Status.NOT_FOUND).entity("list is empty").build();
            
        }
      
        
        FlightInformationList flightListInformation = new FlightInformationList();
        flightListInformation.setList(list);

        return  Response.status(Response.Status.OK).entity(flightListInformation).build();

    }
    
 

    private static GetFlightsOutput getFlights(ws.lameduck.GetFlightsInput getFlightsInput) {
        ws.lameduck.LameDuckService service = new ws.lameduck.LameDuckService();
        ws.lameduck.LameDuckPortType port = service.getLameDuckPortTypeBindingPort();
        return port.getFlights(getFlightsInput);
    }

}
