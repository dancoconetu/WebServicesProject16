
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

    private final static QName _GetFlighsInputElement_QNAME = new QName("http://LameDuck.ws", "getFlighsInputElement");
    private final static QName _BookFlightFaultElement_QNAME = new QName("http://LameDuck.ws", "bookFlightFaultElement");
    private final static QName _CancelFlightInputElement_QNAME = new QName("http://LameDuck.ws", "cancelFlightInputElement");
    private final static QName _BookFlightOutputElement_QNAME = new QName("http://LameDuck.ws", "bookFlightOutputElement");
    private final static QName _GetFlightsOutputElement_QNAME = new QName("http://LameDuck.ws", "getFlightsOutputElement");
    private final static QName _CancelFlightOutputElement_QNAME = new QName("http://LameDuck.ws", "cancelFlightOutputElement");
    private final static QName _BookFlightInputElement_QNAME = new QName("http://LameDuck.ws", "bookFlightInputElement");
    private final static QName _CancelFlightFaultElement_QNAME = new QName("http://LameDuck.ws", "cancelFlightFaultElement");

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
     * Create an instance of {@link GetFlightsInput }
     * 
     */
    public GetFlightsInput createGetFlightsInput() {
        return new GetFlightsInput();
    }

    /**
     * Create an instance of {@link BookFlightInput }
     * 
     */
    public BookFlightInput createBookFlightInput() {
        return new BookFlightInput();
    }

    /**
     * Create an instance of {@link GetFlightsOutput }
     * 
     */
    public GetFlightsOutput createGetFlightsOutput() {
        return new GetFlightsOutput();
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
     * Create an instance of {@link ExpirationDate }
     * 
     */
    public ExpirationDate createExpirationDate() {
        return new ExpirationDate();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlightsInput }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LameDuck.ws", name = "getFlighsInputElement")
    public JAXBElement<GetFlightsInput> createGetFlighsInputElement(GetFlightsInput value) {
        return new JAXBElement<GetFlightsInput>(_GetFlighsInputElement_QNAME, GetFlightsInput.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LameDuck.ws", name = "bookFlightFaultElement")
    public JAXBElement<String> createBookFlightFaultElement(String value) {
        return new JAXBElement<String>(_BookFlightFaultElement_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelFlightInput }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LameDuck.ws", name = "cancelFlightInputElement")
    public JAXBElement<CancelFlightInput> createCancelFlightInputElement(CancelFlightInput value) {
        return new JAXBElement<CancelFlightInput>(_CancelFlightInputElement_QNAME, CancelFlightInput.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LameDuck.ws", name = "bookFlightOutputElement")
    public JAXBElement<Boolean> createBookFlightOutputElement(Boolean value) {
        return new JAXBElement<Boolean>(_BookFlightOutputElement_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlightsOutput }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LameDuck.ws", name = "getFlightsOutputElement")
    public JAXBElement<GetFlightsOutput> createGetFlightsOutputElement(GetFlightsOutput value) {
        return new JAXBElement<GetFlightsOutput>(_GetFlightsOutputElement_QNAME, GetFlightsOutput.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LameDuck.ws", name = "cancelFlightOutputElement")
    public JAXBElement<Boolean> createCancelFlightOutputElement(Boolean value) {
        return new JAXBElement<Boolean>(_CancelFlightOutputElement_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookFlightInput }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LameDuck.ws", name = "bookFlightInputElement")
    public JAXBElement<BookFlightInput> createBookFlightInputElement(BookFlightInput value) {
        return new JAXBElement<BookFlightInput>(_BookFlightInputElement_QNAME, BookFlightInput.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://LameDuck.ws", name = "cancelFlightFaultElement")
    public JAXBElement<String> createCancelFlightFaultElement(String value) {
        return new JAXBElement<String>(_CancelFlightFaultElement_QNAME, String.class, null, value);
    }

}
