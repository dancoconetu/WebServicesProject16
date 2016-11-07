
package ws.lameduck;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for creditCardDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="creditCardDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="holdersName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expirityDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="cardNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditCardDetails", propOrder = {
    "holdersName",
    "expirityDate",
    "cardNumber"
})
public class CreditCardDetails {

    @XmlElement(required = true)
    protected String holdersName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expirityDate;
    @XmlElement(required = true)
    protected String cardNumber;

    /**
     * Gets the value of the holdersName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoldersName() {
        return holdersName;
    }

    /**
     * Sets the value of the holdersName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoldersName(String value) {
        this.holdersName = value;
    }

    /**
     * Gets the value of the expirityDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirityDate() {
        return expirityDate;
    }

    /**
     * Sets the value of the expirityDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirityDate(XMLGregorianCalendar value) {
        this.expirityDate = value;
    }

    /**
     * Gets the value of the cardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the value of the cardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardNumber(String value) {
        this.cardNumber = value;
    }

}
