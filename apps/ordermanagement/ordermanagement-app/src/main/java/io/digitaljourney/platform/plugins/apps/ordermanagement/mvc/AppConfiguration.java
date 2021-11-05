package io.digitaljourney.platform.plugins.apps.ordermanagement.mvc;

import org.osgi.service.metatype.annotations.Icon;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "%name", description = "%description", localization = "OSGI-INF/l10n/ordermanagementmvc", icon = @Icon(resource = "OSGI-INF/icon/mvc.png", size = 32))
public @interface AppConfiguration {
    /** Component identifier */
    static final String CPID = "io.digitaljourney.platform.plugins.apps.ordermanagement.mvc";
}
