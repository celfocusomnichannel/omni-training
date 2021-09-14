package io.digitaljourney.platform.plugins.apps.appkar.common.provider;

import org.osgi.service.metatype.annotations.Icon;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "%name", description = "%description", localization = "OSGI-INF/l10n/appkarfacade", icon = @Icon(resource = "OSGI-INF/icon/appkarfacade.png", size = 32))
public @interface AppKarFacadeConfig {
    static final String CPID = "platform.plugins.apps.appkar.facade";
}
