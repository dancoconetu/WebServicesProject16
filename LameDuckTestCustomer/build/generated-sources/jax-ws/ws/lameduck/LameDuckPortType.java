
package ws.lameduck;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "LameDuckPortType", targetNamespace = "http://LameDuck.ws")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface LameDuckPortType {


    /**
     * 
     * @param getFlightsInput
     * @return
     *     returns ws.lameduck.GetFlightsOutput
     */
    @WebMethod
    @WebResult(name = "getFlightsOutput", targetNamespace = "http://LameDuck.ws", partName = "getFlightsOutput")
    public GetFlightsOutput getFlights(
        @WebParam(name = "getFlighsInput", targetNamespace = "http://LameDuck.ws", partName = "getFlightsInput")
        GetFlightsInput getFlightsInput);

}
