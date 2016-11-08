
package ws.lameduck;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.lameduck package. 
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

    private final static QName _CancelFlightInput_QNAME = new QName("http://LameDuck.ws", "cancelFlightInput");
    private final static QName _BookFlightFault_QNAME = new QName("http://LameDuck.ws", "bookFlightFault");
    private final static QName _CancelFlightFault_QNAME = new QName("http://LameDuck.ws", "cancelFlightFault");
    private final static QName _GetFlighsInput_QNAME = new QName("http://LameDuck.ws", "getFlighsInput");
    private final static QName _BookFlightInput_QNAME = new QName("http://LameDuck.ws", "bookFlightInput");
    private final static QName _BookFlightOutput_QNAME = new QName("http://LameDuck.ws", "bookFlightOutput");
    private final static QName _CancelFlightOutput_QNAME = new QName("http://LameDuck.ws", "cancelFlightOutput");
    private final static QName _GetFlightsOutput_QNAME = new QName("http://LameDuck.ws", "getFlightsOutput");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.lameduck
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CancelFlightInput }
     * 
     */
    public CancelFlightInput createCancelFlightInput() {
        return new CancelFlightInput();
    }

    /**
     * Create an instance of {@link GetFlightsOutput }
     * 
     */
    public GetFlightsOutput createGetFlightsOutput() {
        return new GetFlightsOutput();
    }

    /**
     * Create an instance of {@link BookFlightInput }
     * 
     */
    public BookFlightInput createBookFlightInput() {
        return new BookFlightInput();
    }

    /**
     * Create an instance of {@link GetFlightsInput }
     * 
     */
    public GetFlightsInput createGetFlightsInput() {
        return new GetFlightsInput();
    }

    /**
     * Create an instance of {@link FlightInformation }
     * 
     */
    public FlightInformation createFlightInformation() {
        return new FlightInformation();
    }

    /**
     * Create an instance of {@link Flight }
     * 
     */
    public Flight createFlight() {
        return new Flight();
    }

    /**
     * Create an instance of {@link CreditCardDetails }
     * 
     */
    public CreditCardDetails createCreditCardDetails() {
        return new CreditCardDetails();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelFlightInput }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LameDuck.ws", name = "cancelFlightInput")
    public JAXBElement<CancelFlightInput> createCancelFlightInput(CancelFlightInput value) {
        return new JAXBElement<CancelFlightInput>(_CancelFlightInput_QNAME, CancelFlightInput.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LameDuck.ws", name = "bookFlightFault")
    public JAXBElement<String> createBookFlightFault(String value) {
        return new JAXBElement<String>(_BookFlightFault_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LameDuck.ws", name = "cancelFlightFault")
    public JAXBElement<String> createCancelFlightFault(String value) {
        return new JAXBElement<String>(_CancelFlightFault_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlightsInput }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LameDuck.ws", name = "getFlighsInput")
    public JAXBElement<GetFlightsInput> createGetFlighsInput(GetFlightsInput value) {
        return new JAXBElement<GetFlightsInput>(_GetFlighsInput_QNAME, GetFlightsInput.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookFlightInput }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LameDuck.ws", name = "bookFlightInput")
    public JAXBElement<BookFlightInput> createBookFlightInput(BookFlightInput value) {
        return new JAXBElement<BookFlightInput>(_BookFlightInput_QNAME, BookFlightInput.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LameDuck.ws", name = "bookFlightOutput")
    public JAXBElement<Boolean> createBookFlightOutput(Boolean value) {
        return new JAXBElement<Boolean>(_BookFlightOutput_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LameDuck.ws", name = "cancelFlightOutput")
    public JAXBElement<Boolean> createCancelFlightOutput(Boolean value) {
        return new JAXBElement<Boolean>(_CancelFlightOutput_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlightsOutput }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LameDuck.ws", name = "getFlightsOutput")
    public JAXBElement<GetFlightsOutput> createGetFlightsOutput(GetFlightsOutput value) {
        return new JAXBElement<GetFlightsOutput>(_GetFlightsOutput_QNAME, GetFlightsOutput.class, null, value);
    }

}
