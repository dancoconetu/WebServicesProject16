
package ws.niceview;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebService(name = "NiceViewPort", targetNamespace = "http://NiceView.WS")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface NiceViewPort {


    /**
     * 
     * @param input2
     * @return
     *     returns boolean
     * @throws CancelHotelFault
     */
    @WebMethod(action = "cancelHotel_action")
    @WebResult(name = "cancelHotelOutput", targetNamespace = "http://NiceView.WS", partName = "output")
    public boolean cancelHotel(
        @WebParam(name = "cancelHotelInput", targetNamespace = "http://NiceView.WS", partName = "input2")
        String input2)
        throws CancelHotelFault
    ;

    /**
     * 
     * @param input3
     * @return
     *     returns boolean
     * @throws BookHotelFault
     */
    @WebMethod(action = "bookHotel_action")
    @WebResult(name = "bookHotelOutput", targetNamespace = "http://NiceView.WS", partName = "part1")
    public boolean bookHotel(
        @WebParam(name = "bookHotelInput", targetNamespace = "http://NiceView.WS", partName = "input3")
        BookHotelInput input3)
        throws BookHotelFault
    ;

    /**
     * 
     * @param input1
     * @return
     *     returns ws.niceview.GetHotelOutput
     * @throws GetHotelListFault
     */
    @WebMethod(action = "getHotelsList_action")
    @WebResult(name = "getHotelsOutput", targetNamespace = "http://NiceView.WS", partName = "output1")
    public GetHotelOutput getHotelsList(
        @WebParam(name = "getHotelInputs", targetNamespace = "http://NiceView.WS", partName = "input1")
        GetHotelInput input1)
        throws GetHotelListFault
    ;

}
