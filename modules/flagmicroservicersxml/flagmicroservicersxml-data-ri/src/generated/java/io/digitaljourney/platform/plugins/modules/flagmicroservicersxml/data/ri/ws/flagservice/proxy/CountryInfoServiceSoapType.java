package io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.14
 * 2021-09-08T17:00:16.391+01:00
 * Generated source version: 3.2.14
 *
 */
@WebService(targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", name = "CountryInfoServiceSoapType")
@XmlSeeAlso({ObjectFactory.class})
public interface CountryInfoServiceSoapType {

    /**
     * Returns a struct with all the stored country information. Pass the ISO country code
     */
    @WebMethod(operationName = "FullCountryInfo")
    @RequestWrapper(localName = "FullCountryInfo", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.FullCountryInfo")
    @ResponseWrapper(localName = "FullCountryInfoResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.FullCountryInfoResponse")
    @WebResult(name = "FullCountryInfoResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.TCountryInfo fullCountryInfo(
        @WebParam(name = "sCountryISOCode", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
        java.lang.String sCountryISOCode
    );

    /**
     * Returns a link to a picture of the country flag
     */
    @WebMethod(operationName = "CountryFlag")
    @RequestWrapper(localName = "CountryFlag", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.CountryFlag")
    @ResponseWrapper(localName = "CountryFlagResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.CountryFlagResponse")
    @WebResult(name = "CountryFlagResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public java.lang.String countryFlag(
        @WebParam(name = "sCountryISOCode", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
        java.lang.String sCountryISOCode
    );

    /**
     * Searches the database for a country by the passed ISO country code
     */
    @WebMethod(operationName = "CountryName")
    @RequestWrapper(localName = "CountryName", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.CountryName")
    @ResponseWrapper(localName = "CountryNameResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.CountryNameResponse")
    @WebResult(name = "CountryNameResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public java.lang.String countryName(
        @WebParam(name = "sCountryISOCode", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
        java.lang.String sCountryISOCode
    );

    /**
     * Returns the currency ISO code and name for the passed country ISO code
     */
    @WebMethod(operationName = "CountryCurrency")
    @RequestWrapper(localName = "CountryCurrency", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.CountryCurrency")
    @ResponseWrapper(localName = "CountryCurrencyResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.CountryCurrencyResponse")
    @WebResult(name = "CountryCurrencyResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.TCurrency countryCurrency(
        @WebParam(name = "sCountryISOCode", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
        java.lang.String sCountryISOCode
    );

    /**
     * Returns a list of all stored counties ordered by ISO code
     */
    @WebMethod(operationName = "ListOfCountryNamesByCode")
    @RequestWrapper(localName = "ListOfCountryNamesByCode", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfCountryNamesByCode")
    @ResponseWrapper(localName = "ListOfCountryNamesByCodeResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfCountryNamesByCodeResponse")
    @WebResult(name = "ListOfCountryNamesByCodeResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ArrayOftCountryCodeAndName listOfCountryNamesByCode();

    /**
     * Returns a list of currencies ordered by name.
     */
    @WebMethod(operationName = "ListOfCurrenciesByName")
    @RequestWrapper(localName = "ListOfCurrenciesByName", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfCurrenciesByName")
    @ResponseWrapper(localName = "ListOfCurrenciesByNameResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfCurrenciesByNameResponse")
    @WebResult(name = "ListOfCurrenciesByNameResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ArrayOftCurrency listOfCurrenciesByName();

    /**
     * Returns the internation phone code for the passed ISO country code
     */
    @WebMethod(operationName = "CountryIntPhoneCode")
    @RequestWrapper(localName = "CountryIntPhoneCode", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.CountryIntPhoneCode")
    @ResponseWrapper(localName = "CountryIntPhoneCodeResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.CountryIntPhoneCodeResponse")
    @WebResult(name = "CountryIntPhoneCodeResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public java.lang.String countryIntPhoneCode(
        @WebParam(name = "sCountryISOCode", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
        java.lang.String sCountryISOCode
    );

    /**
     * Returns the name of the currency (if found)
     */
    @WebMethod(operationName = "CurrencyName")
    @RequestWrapper(localName = "CurrencyName", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.CurrencyName")
    @ResponseWrapper(localName = "CurrencyNameResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.CurrencyNameResponse")
    @WebResult(name = "CurrencyNameResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public java.lang.String currencyName(
        @WebParam(name = "sCurrencyISOCode", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
        java.lang.String sCurrencyISOCode
    );

    /**
     * Returns a list of currencies ordered by code.
     */
    @WebMethod(operationName = "ListOfCurrenciesByCode")
    @RequestWrapper(localName = "ListOfCurrenciesByCode", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfCurrenciesByCode")
    @ResponseWrapper(localName = "ListOfCurrenciesByCodeResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfCurrenciesByCodeResponse")
    @WebResult(name = "ListOfCurrenciesByCodeResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ArrayOftCurrency listOfCurrenciesByCode();

    /**
     * Returns an array of languages ordered by code
     */
    @WebMethod(operationName = "ListOfLanguagesByCode")
    @RequestWrapper(localName = "ListOfLanguagesByCode", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfLanguagesByCode")
    @ResponseWrapper(localName = "ListOfLanguagesByCodeResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfLanguagesByCodeResponse")
    @WebResult(name = "ListOfLanguagesByCodeResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ArrayOftLanguage listOfLanguagesByCode();

    /**
     * Find a language ISO code based on the passed language name
     */
    @WebMethod(operationName = "LanguageISOCode")
    @RequestWrapper(localName = "LanguageISOCode", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.LanguageISOCode")
    @ResponseWrapper(localName = "LanguageISOCodeResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.LanguageISOCodeResponse")
    @WebResult(name = "LanguageISOCodeResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public java.lang.String languageISOCode(
        @WebParam(name = "sLanguageName", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
        java.lang.String sLanguageName
    );

    /**
     * Returns the name of the captial city for the passed country code
     */
    @WebMethod(operationName = "CapitalCity")
    @RequestWrapper(localName = "CapitalCity", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.CapitalCity")
    @ResponseWrapper(localName = "CapitalCityResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.CapitalCityResponse")
    @WebResult(name = "CapitalCityResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public java.lang.String capitalCity(
        @WebParam(name = "sCountryISOCode", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
        java.lang.String sCountryISOCode
    );

    /**
     * Returns an array of languages ordered by name
     */
    @WebMethod(operationName = "ListOfLanguagesByName")
    @RequestWrapper(localName = "ListOfLanguagesByName", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfLanguagesByName")
    @ResponseWrapper(localName = "ListOfLanguagesByNameResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfLanguagesByNameResponse")
    @WebResult(name = "ListOfLanguagesByNameResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ArrayOftLanguage listOfLanguagesByName();

    /**
     * Returns a list of all stored counties grouped per continent
     */
    @WebMethod(operationName = "ListOfCountryNamesGroupedByContinent")
    @RequestWrapper(localName = "ListOfCountryNamesGroupedByContinent", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfCountryNamesGroupedByContinent")
    @ResponseWrapper(localName = "ListOfCountryNamesGroupedByContinentResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfCountryNamesGroupedByContinentResponse")
    @WebResult(name = "ListOfCountryNamesGroupedByContinentResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ArrayOftCountryCodeAndNameGroupedByContinent listOfCountryNamesGroupedByContinent();

    /**
     * Returns a list of all stored counties ordered by country name
     */
    @WebMethod(operationName = "ListOfCountryNamesByName")
    @RequestWrapper(localName = "ListOfCountryNamesByName", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfCountryNamesByName")
    @ResponseWrapper(localName = "ListOfCountryNamesByNameResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfCountryNamesByNameResponse")
    @WebResult(name = "ListOfCountryNamesByNameResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ArrayOftCountryCodeAndName listOfCountryNamesByName();

    /**
     * Returns a list of continents ordered by name.
     */
    @WebMethod(operationName = "ListOfContinentsByName")
    @RequestWrapper(localName = "ListOfContinentsByName", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfContinentsByName")
    @ResponseWrapper(localName = "ListOfContinentsByNameResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfContinentsByNameResponse")
    @WebResult(name = "ListOfContinentsByNameResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ArrayOftContinent listOfContinentsByName();

    /**
     * Find a language name based on the passed ISO language code
     */
    @WebMethod(operationName = "LanguageName")
    @RequestWrapper(localName = "LanguageName", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.LanguageName")
    @ResponseWrapper(localName = "LanguageNameResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.LanguageNameResponse")
    @WebResult(name = "LanguageNameResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public java.lang.String languageName(
        @WebParam(name = "sISOCode", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
        java.lang.String sISOCode
    );

    /**
     * Returns a list of continents ordered by code.
     */
    @WebMethod(operationName = "ListOfContinentsByCode")
    @RequestWrapper(localName = "ListOfContinentsByCode", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfContinentsByCode")
    @ResponseWrapper(localName = "ListOfContinentsByCodeResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ListOfContinentsByCodeResponse")
    @WebResult(name = "ListOfContinentsByCodeResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ArrayOftContinent listOfContinentsByCode();

    /**
     * Returns a list of all countries that use the same currency code. Pass a ISO currency code
     */
    @WebMethod(operationName = "CountriesUsingCurrency")
    @RequestWrapper(localName = "CountriesUsingCurrency", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.CountriesUsingCurrency")
    @ResponseWrapper(localName = "CountriesUsingCurrencyResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.CountriesUsingCurrencyResponse")
    @WebResult(name = "CountriesUsingCurrencyResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ArrayOftCountryCodeAndName countriesUsingCurrency(
        @WebParam(name = "sISOCurrencyCode", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
        java.lang.String sISOCurrencyCode
    );

    /**
     * This function tries to found a country based on the passed country name.
     */
    @WebMethod(operationName = "CountryISOCode")
    @RequestWrapper(localName = "CountryISOCode", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.CountryISOCode")
    @ResponseWrapper(localName = "CountryISOCodeResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.CountryISOCodeResponse")
    @WebResult(name = "CountryISOCodeResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public java.lang.String countryISOCode(
        @WebParam(name = "sCountryName", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
        java.lang.String sCountryName
    );

    /**
     * Returns an array with all countries and all the language information stored
     */
    @WebMethod(operationName = "FullCountryInfoAllCountries")
    @RequestWrapper(localName = "FullCountryInfoAllCountries", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.FullCountryInfoAllCountries")
    @ResponseWrapper(localName = "FullCountryInfoAllCountriesResponse", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo", className = "io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.FullCountryInfoAllCountriesResponse")
    @WebResult(name = "FullCountryInfoAllCountriesResult", targetNamespace = "http://www.oorsprong.org/websamples.countryinfo")
    public io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.data.ri.ws.flagservice.proxy.ArrayOftCountryInfo fullCountryInfoAllCountries();
}