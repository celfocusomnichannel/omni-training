
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
 *         &lt;element name="ListOfContinentsByCodeResult" type="{http://www.oorsprong.org/websamples.countryinfo}ArrayOftContinent"/&gt;
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
    "listOfContinentsByCodeResult"
})
@XmlRootElement(name = "ListOfContinentsByCodeResponse")
public class ListOfContinentsByCodeResponse {

    @XmlElement(name = "ListOfContinentsByCodeResult", required = true)
    protected ArrayOftContinent listOfContinentsByCodeResult;

    /**
     * Gets the value of the listOfContinentsByCodeResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOftContinent }
     *     
     */
    public ArrayOftContinent getListOfContinentsByCodeResult() {
        return listOfContinentsByCodeResult;
    }

    /**
     * Sets the value of the listOfContinentsByCodeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOftContinent }
     *     
     */
    public void setListOfContinentsByCodeResult(ArrayOftContinent value) {
        this.listOfContinentsByCodeResult = value;
    }

}
