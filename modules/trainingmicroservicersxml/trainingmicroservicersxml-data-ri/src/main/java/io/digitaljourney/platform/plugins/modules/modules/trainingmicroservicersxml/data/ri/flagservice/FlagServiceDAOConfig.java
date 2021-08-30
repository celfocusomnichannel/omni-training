package io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.data.ri.flagservice;

import javax.ws.rs.core.MediaType;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Icon;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "%name", description = "%description",
    localization = "OSGI-INF/l10n/flagservice", icon = @Icon(resource = "OSGI-INF/icon/ws.png", size = 32))
public @interface FlagServiceDAOConfig {
  public static final String CPID = "io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.data.ri.flagservice";

  @AttributeDefinition(name = "%providerName.name", description = "%providerName.description")
  String providerName();

  @AttributeDefinition(name = "%contentType.name", description = "%contentType.description")
  String contentType() default MediaType.TEXT_XML;

  @AttributeDefinition(name = "%acceptType.name", description = "%acceptType.description")
  String acceptType() default MediaType.TEXT_XML;

  @AttributeDefinition(name = "%namespaceUri.name", description = "%namespaceUri.description")
  String namespaceUri() default "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService";

  @AttributeDefinition(name = "%serviceName.name", description = "%serviceName.description")
  String serviceName() default "FlagService";

  @AttributeDefinition(name = "%wsdlLocation.name", description = "%wsdlLocation.description")
  String wsdlLocation() default "wsdl/flagservice.xml";

  @AttributeDefinition(name = "%address.name", description = "%address.description")
  String address() default "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso";

  @AttributeDefinition(name = "%userName.name", description = "%userName.description")
  String userName() default "";

  @AttributeDefinition(name = "%password.name", type = AttributeType.PASSWORD,
      description = "%password.description")
  String password() default "";

  @AttributeDefinition(name = "%connectionTimeout.name", type = AttributeType.LONG, min = "0",
      description = "%connectionTimeout.description")
  long connectionTimeout() default 10000;

  @AttributeDefinition(name = "%receiveTimeout.name", type = AttributeType.LONG, min = "0",
      description = "%receiveTimeout.description")
  long receiveTimeout() default 180000;

  @AttributeDefinition(name = "%proxyEnabled.name", type = AttributeType.BOOLEAN,
      description = "%proxyEnabled.description")
  boolean proxyEnabled() default false;

  @AttributeDefinition(name = "%proxyHost.name", description = "%proxyHost.description")
  String proxyHost() default "localhost";

  @AttributeDefinition(name = "%proxyPort.name", description = "%proxyPort.description")
  int proxyPort() default 8888;

  @AttributeDefinition(name = "%xpathSuccessExpression.name",
      description = "%xpathSuccessExpression.description")
  String xpathSuccessExpression() default "";

  @AttributeDefinition(name = "%successStatusCodes.name", description = "%successStatusCodes.description")
  String[] successStatusCodes() default {"1"};
}

