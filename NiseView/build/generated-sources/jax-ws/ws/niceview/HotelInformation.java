
package ws.niceview;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HotelInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HotelInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bookingNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pricePerNight" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="adress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreditCardReqiured" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="HotelReservationService" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HotelInformation", propOrder = {
    "name",
    "city",
    "bookingNR",
    "pricePerNight",
    "adress",
    "creditCardReqiured",
    "hotelReservationService"
})
public class HotelInformation {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String city;
    @XmlElement(required = true)
    protected String bookingNR;
    protected double pricePerNight;
    @XmlElement(required = true)
    protected String adress;
    @XmlElement(name = "CreditCardReqiured")
    protected boolean creditCardReqiured;
    @XmlElement(name = "HotelReservationService", required = true)
    protected String hotelReservationService;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

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
     * Gets the value of the pricePerNight property.
     * 
     */
    public double getPricePerNight() {
        return pricePerNight;
    }

    /**
     * Sets the value of the pricePerNight property.
     * 
     */
    public void setPricePerNight(double value) {
        this.pricePerNight = value;
    }

    /**
     * Gets the value of the adress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Sets the value of the adress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdress(String value) {
        this.adress = value;
    }

    /**
     * Gets the value of the creditCardReqiured property.
     * 
     */
    public boolean isCreditCardReqiured() {
        return creditCardReqiured;
    }

    /**
     * Sets the value of the creditCardReqiured property.
     * 
     */
    public void setCreditCardReqiured(boolean value) {
        this.creditCardReqiured = value;
    }

    /**
     * Gets the value of the hotelReservationService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHotelReservationService() {
        return hotelReservationService;
    }

    /**
     * Sets the value of the hotelReservationService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHotelReservationService(String value) {
        this.hotelReservationService = value;
    }

}
