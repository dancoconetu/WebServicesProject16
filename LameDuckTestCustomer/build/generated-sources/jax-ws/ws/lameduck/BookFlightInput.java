
package ws.lameduck;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bookFlightInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bookFlightInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookingNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creditCardDetails" type="{http://LameDuck.ws}creditCardDetails"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bookFlightInput", propOrder = {
    "bookingNo",
    "creditCardDetails"
})
public class BookFlightInput {

    @XmlElement(required = true)
    protected String bookingNo;
    @XmlElement(required = true)
    protected CreditCardDetails creditCardDetails;

    /**
     * Gets the value of the bookingNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingNo() {
        return bookingNo;
    }

    /**
     * Sets the value of the bookingNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingNo(String value) {
        this.bookingNo = value;
    }

    /**
     * Gets the value of the creditCardDetails property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCardDetails }
     *     
     */
    public CreditCardDetails getCreditCardDetails() {
        return creditCardDetails;
    }

    /**
     * Sets the value of the creditCardDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCardDetails }
     *     
     */
    public void setCreditCardDetails(CreditCardDetails value) {
        this.creditCardDetails = value;
    }

}
