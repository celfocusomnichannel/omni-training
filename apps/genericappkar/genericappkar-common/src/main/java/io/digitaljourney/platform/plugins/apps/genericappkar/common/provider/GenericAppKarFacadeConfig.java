package io.digitaljourney.platform.plugins.apps.genericappkar.common.provider;

import org.osgi.service.metatype.annotations.Icon;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "%name", description = "%description", localization = "OSGI-INF/l10n/genericappkarfacade", icon = @Icon(resource = "OSGI-INF/icon/genericappkarfacade.png", size = 32))
public @interface GenericAppKarFacadeConfig {
    static final String CPID = "platform.plugins.apps.genericappkar.facade";
}
