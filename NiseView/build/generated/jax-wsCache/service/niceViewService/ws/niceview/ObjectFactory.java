
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

    private final static QName _CancelHotelFault_QNAME = new QName("http://NiceView.WS", "cancelHotelFault");
    private final static QName _GetHotelInputs_QNAME = new QName("http://NiceView.WS", "getHotelInputs");
    private final static QName _BookingNr_QNAME = new QName("http://NiceView.WS", "bookingNr");
    private final static QName _CancelHotelOutput_QNAME = new QName("http://NiceView.WS", "cancelHotelOutput");
    private final static QName _GetHotelOutput_QNAME = new QName("http://NiceView.WS", "getHotelOutput");
    private final static QName _BookHotelOutput_QNAME = new QName("http://NiceView.WS", "bookHotelOutput");
    private final static QName _BookHotelFault_QNAME = new QName("http://NiceView.WS", "bookHotelFault");

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
     * Create an instance of {@link BookHotelInput }
     * 
     */
    public BookHotelInput createBookHotelInput() {
        return new BookHotelInput();
    }

    /**
     * Create an instance of {@link CreditCardInformation }
     * 
     */
    public CreditCardInformation createCreditCardInformation() {
        return new CreditCardInformation();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://NiceView.WS", name = "cancelHotelFault")
    public JAXBElement<String> createCancelHotelFault(String value) {
        return new JAXBElement<String>(_CancelHotelFault_QNAME, String.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://NiceView.WS", name = "bookingNr")
    public JAXBElement<String> createBookingNr(String value) {
        return new JAXBElement<String>(_BookingNr_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://NiceView.WS", name = "cancelHotelOutput")
    public JAXBElement<Boolean> createCancelHotelOutput(Boolean value) {
        return new JAXBElement<Boolean>(_CancelHotelOutput_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HotelListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://NiceView.WS", name = "getHotelOutput")
    public JAXBElement<HotelListType> createGetHotelOutput(HotelListType value) {
        return new JAXBElement<HotelListType>(_GetHotelOutput_QNAME, HotelListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://NiceView.WS", name = "bookHotelOutput")
    public JAXBElement<Boolean> createBookHotelOutput(Boolean value) {
        return new JAXBElement<Boolean>(_BookHotelOutput_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://NiceView.WS", name = "bookHotelFault")
    public JAXBElement<String> createBookHotelFault(String value) {
        return new JAXBElement<String>(_BookHotelFault_QNAME, String.class, null, value);
    }

}
