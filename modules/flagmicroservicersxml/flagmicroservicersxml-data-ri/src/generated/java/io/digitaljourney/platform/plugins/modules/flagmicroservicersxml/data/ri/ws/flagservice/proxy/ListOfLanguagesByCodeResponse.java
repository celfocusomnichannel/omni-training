
package io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ListOfLanguagesByCodeResult" type="{http://www.oorsprong.org/websamples.countryinfo}ArrayOftLanguage"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "listOfLanguagesByCodeResult"
})
@XmlRootElement(name = "ListOfLanguagesByCodeResponse")
public class ListOfLanguagesByCodeResponse {

    @XmlElement(name = "ListOfLanguagesByCodeResult", required = true)
    protected ArrayOftLanguage listOfLanguagesByCodeResult;

    /**
     * Gets the value of the listOfLanguagesByCodeResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOftLanguage }
     *     
     */
    public ArrayOftLanguage getListOfLanguagesByCodeResult() {
        return listOfLanguagesByCodeResult;
    }

    /**
     * Sets the value of the listOfLanguagesByCodeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOftLanguage }
     *     
     */
    public void setListOfLanguagesByCodeResult(ArrayOftLanguage value) {
        this.listOfLanguagesByCodeResult = value;
    }

}
