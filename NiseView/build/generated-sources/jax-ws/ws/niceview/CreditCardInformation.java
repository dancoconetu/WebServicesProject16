
package ws.niceview;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for creditCardInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="creditCardInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="holderName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="8digitNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expDate" type="{http://NiceView.WS}expDate"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditCardInformation", propOrder = {
    "holderName",
    "_8DigitNumber",
    "expDate"
})
public class CreditCardInformation {

    @XmlElement(required = true)
    protected String holderName;
    @XmlElement(name = "8digitNumber", required = true)
    protected String _8DigitNumber;
    @XmlElement(required = true)
    protected ExpDate expDate;

    /**
     * Gets the value of the holderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * Sets the value of the holderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHolderName(String value) {
        this.holderName = value;
    }

    /**
     * Gets the value of the 8DigitNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String get8DigitNumber() {
        return _8DigitNumber;
    }

    /**
     * Sets the value of the 8DigitNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void set8DigitNumber(String value) {
        this._8DigitNumber = value;
    }

    /**
     * Gets the value of the expDate property.
     * 
     * @return
     *     possible object is
     *     {@link ExpDate }
     *     
     */
    public ExpDate getExpDate() {
        return expDate;
    }

    /**
     * Sets the value of the expDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpDate }
     *     
     */
    public void setExpDate(ExpDate value) {
        this.expDate = value;
    }

}
