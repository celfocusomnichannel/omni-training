package io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This DataFlex Web Service opens up country information. 2 letter ISO codes are used for Country code. There are functions to retrieve the used Currency, Language, Capital City, Continent and Telephone code.
 *
 * This class was generated by Apache CXF 3.2.14
 * 2021-09-08T17:00:16.426+01:00
 * Generated source version: 3.2.14
 *
 */
@WebServiceClient(name = "CountryInfoService",
                  wsdlLocation = "file:/D:/wizzio/dev/repos/omni-training/modules/flagmicroservicersxml/flagmicroservicersxml-data-ri/src/main/resources/wsdl/flagservice.xml",
                  targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
public class CountryInfoService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.oorsprong.org/websamples.countryinfo", "CountryInfoService");
    public final static QName CountryInfoServiceSoap12 = new QName("http://www.oorsprong.org/websamples.countryinfo", "CountryInfoServiceSoap12");
    public final static QName CountryInfoServiceSoap = new QName("http://www.oorsprong.org/websamples.countryinfo", "CountryInfoServiceSoap");
    static {
        URL url = null;
        try {
            url = new URL("file:/D:/wizzio/dev/repos/omni-training/modules/flagmicroservicersxml/flagmicroservicersxml-data-ri/src/main/resources/wsdl/flagservice.xml");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CountryInfoService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:/D:/wizzio/dev/repos/omni-training/modules/flagmicroservicersxml/flagmicroservicersxml-data-ri/src/main/resources/wsdl/flagservice.xml");
        }
        WSDL_LOCATION = url;
    }

    public CountryInfoService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CountryInfoService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CountryInfoService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public CountryInfoService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public CountryInfoService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public CountryInfoService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns CountryInfoServiceSoapType
     */
    @WebEndpoint(name = "CountryInfoServiceSoap12")
    public CountryInfoServiceSoapType getCountryInfoServiceSoap12() {
        return super.getPort(CountryInfoServiceSoap12, CountryInfoServiceSoapType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CountryInfoServiceSoapType
     */
    @WebEndpoint(name = "CountryInfoServiceSoap12")
    public CountryInfoServiceSoapType getCountryInfoServiceSoap12(WebServiceFeature... features) {
        return super.getPort(CountryInfoServiceSoap12, CountryInfoServiceSoapType.class, features);
    }


    /**
     *
     * @return
     *     returns CountryInfoServiceSoapType
     */
    @WebEndpoint(name = "CountryInfoServiceSoap")
    public CountryInfoServiceSoapType getCountryInfoServiceSoap() {
        return super.getPort(CountryInfoServiceSoap, CountryInfoServiceSoapType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CountryInfoServiceSoapType
     */
    @WebEndpoint(name = "CountryInfoServiceSoap")
    public CountryInfoServiceSoapType getCountryInfoServiceSoap(WebServiceFeature... features) {
        return super.getPort(CountryInfoServiceSoap, CountryInfoServiceSoapType.class, features);
    }

}
