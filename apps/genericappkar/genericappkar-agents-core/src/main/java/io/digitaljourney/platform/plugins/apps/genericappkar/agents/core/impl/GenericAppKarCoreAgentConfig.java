package io.digitaljourney.platform.plugins.apps.genericappkar.agents.core.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Icon;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "%name", description = "%description", localization = "OSGI-INF/l10n/agent/core", icon = @Icon(resource = "OSGI-INF/icon/agent.png", size = 32))
public @interface GenericAppKarCoreAgentConfig {
	/** Component Persistent Identifier */
	static final String CPID = "platform.plugins.apps.genericappkar.agent.core";

	/**
	 * Gets the name of the user used to access core resources.
	 *
	 * @return System user name
	 */
	@AttributeDefinition(name = "%systemUserName.name", description = "%systemUserName.description")
	String systemUserName();

	/**
	 * Gets the password of the user to access core resources.
	 *
	 * @return System user password
	 */
	@AttributeDefinition(name = "%systemPassword.name", type = AttributeType.PASSWORD, description = "%systemPassword.description")
	String systemPassword();
}
