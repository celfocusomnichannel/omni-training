package io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.client;

import javax.ws.rs.core.MediaType;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Icon;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

/**
 * TemperatureConverter Client Configuration.
 *
 * @since 1.0.0
 *
 */
@ObjectClassDefinition(name = "%name", description = "%description", localization = "OSGI-INF/l10n/client", icon = @Icon(resource = "OSGI-INF/icon/client.png", size = 32))
public @interface TemperatureConverterClientConfiguration {
	/** Component Persistent Identifier */
	public static final String CPID = "io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.client";

	/**
	 * Gets client name.
	 *
	 * @return Client name
	 */
	@AttributeDefinition(name = "%name.name", description = "%name.description")
	String name() default "temperatureconverter-client";

	/**
	 * Gets the cluster API address.
	 *
	 * @return Cluster API address null or empty to use RSA
	 */
	@AttributeDefinition(name = "%address.name", description = "%address.description")
	String address();

	/**
	 * Gets the content type for requests.
	 *
	 * @return Requests content type, default: application/json
	 */
	@AttributeDefinition(name = "%contentType.name", description = "%contentType.description")
	String contentType() default MediaType.APPLICATION_JSON;

	/**
	 * Gets the accept type for requests.
	 *
	 * @return Requests accept type, default: application/json
	 */
	@AttributeDefinition(name = "%acceptType.name", description = "%acceptType.description")
	String acceptType() default MediaType.APPLICATION_JSON;

	/**
	 * Gets the cluster user name to use.
	 *
	 * @return Cluster user name
	 */
	@AttributeDefinition(name = "%userName.name", description = "%userName.description")
	String userName() default "";

	/**
	 * Gets the cluster user password to use.
	 *
	 * @return Cluster user password
	 */
	@AttributeDefinition(name = "%password.name", type = AttributeType.PASSWORD, description = "%password.description")
	String password() default "";

	/**
	 * Gets the connection timeout value.
	 *
	 * @return Connection timeout value in milliseconds, default: 10000
	 */
	@AttributeDefinition(name = "%connectionTimeout.name", type = AttributeType.LONG, min = "0", description = "%connectionTimeout.description")
	long connectionTimeout() default 10000;

	/**
	 * Gets the connection receive timeout value.
	 *
	 * @return Connection receive timeout value in milliseconds, default: 10000
	 */
	@AttributeDefinition(name = "%receiveTimeout.name", type = AttributeType.LONG, min = "0", description = "%receiveTimeout.description")
	long receiveTimeout() default 180000;

	/**
	 * Whether or not a proxy support is enabled.
	 *
	 * @return True if a proxy support is enabled, false otherwise, default: false
	 */
	@AttributeDefinition(name = "%proxyEnabled.name", type = AttributeType.BOOLEAN, description = "%proxyEnabled.description")
	boolean proxyEnabled() default false;

	/**
	 * Gets the proxy host name. Only used if proxy support is enabled.
	 *
	 * @return Proxy host name, default: localhost
	 */
	@AttributeDefinition(name = "%proxyHost.name", description = "%proxyHost.description")
	String proxyHost() default "localhost";

	/**
	 * Gets the proxy port number. Only used if proxy support is enabled.
	 *
	 * @return Proxy port number, default: 8080
	 */
	@AttributeDefinition(name = "%proxyPort.name", description = "%proxyPort.description")
	int proxyPort() default 8080;

	/**
	 * Gets the proxy type. Only used if proxy support is enabled.
	 *
	 * @return Proxy type, default: HTTP
	 */
	@AttributeDefinition(name = "%proxyType.name", description = "%proxyType.description", options = {
			@Option(label = "HTTP", value = "HTTP"), @Option(label = "SOCKS", value = "SOCKS"), })
	String proxyType() default "HTTP";

	/**
	 * Gets the list of serialization features to use.
	 *
	 * @return Serialization features to use.
	 */
	@AttributeDefinition(name = "%serializationFeatures.name", description = "%serializationFeatures.description")
	String[] serializationFeatures() default {};

	/**
	 * Gets the list of deserialization features to use.
	 *
	 * @return Deserialization features to use.
	 */
	@AttributeDefinition(name = "%deserializationFeatures.name", description = "%deserializationFeatures.description")
	String[] deserializationFeatures() default {};
}
