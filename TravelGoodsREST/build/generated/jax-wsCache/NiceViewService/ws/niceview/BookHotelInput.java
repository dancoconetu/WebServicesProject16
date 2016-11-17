
package ws.niceview;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bookHotelInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bookHotelInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookingNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creditCardInformation" type="{http://NiceView.WS}creditCardInformation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bookHotelInput", propOrder = {
    "bookingNR",
    "creditCardInformation"
})
public class BookHotelInput {

    @XmlElement(required = true)
    protected String bookingNR;
    @XmlElement(required = true)
    protected CreditCardInformation creditCardInformation;

    /**
     * Gets the value of the bookingNR property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingNR() {
        return bookingNR;
    }

    /**
     * Sets the value of the bookingNR property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingNR(String value) {
        this.bookingNR = value;
    }

    /**
     * Gets the value of the creditCardInformation property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCardInformation }
     *     
     */
    public CreditCardInformation getCreditCardInformation() {
        return creditCardInformation;
    }

    /**
     * Sets the value of the creditCardInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCardInformation }
     *     
     */
    public void setCreditCardInformation(CreditCardInformation value) {
        this.creditCardInformation = value;
    }

}
