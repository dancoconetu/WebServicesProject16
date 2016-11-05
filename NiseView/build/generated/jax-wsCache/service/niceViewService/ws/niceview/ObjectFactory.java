
package ws.niceview;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.niceview package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetHotelInputs_QNAME = new QName("http://NiceView.WS", "getHotelInputs");
    private final static QName _GetHotelOutput_QNAME = new QName("http://NiceView.WS", "getHotelOutput");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.niceview
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetHotelInput }
     * 
     */
    public GetHotelInput createGetHotelInput() {
        return new GetHotelInput();
    }

    /**
     * Create an instance of {@link HotelListType }
     * 
     */
    public HotelListType createHotelListType() {
        return new HotelListType();
    }

    /**
     * Create an instance of {@link HotelType }
     * 
     */
    public HotelType createHotelType() {
        return new HotelType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHotelInput }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://NiceView.WS", name = "getHotelInputs")
    public JAXBElement<GetHotelInput> createGetHotelInputs(GetHotelInput value) {
        return new JAXBElement<GetHotelInput>(_GetHotelInputs_QNAME, GetHotelInput.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HotelListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://NiceView.WS", name = "getHotelOutput")
    public JAXBElement<HotelListType> createGetHotelOutput(HotelListType value) {
        return new JAXBElement<HotelListType>(_GetHotelOutput_QNAME, HotelListType.class, null, value);
    }

}
