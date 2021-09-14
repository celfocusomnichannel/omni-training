package io.digitaljourney.platform.plugins.apps.appkar.mvc;

import org.osgi.service.metatype.annotations.Icon;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "%name", description = "%description", localization = "OSGI-INF/l10n/appkarmvc", icon = @Icon(resource = "OSGI-INF/icon/mvc.png", size = 32))
public @interface AppKarConfiguration {
    /** Component identifier */
    static final String CPID = "platform.plugins.apps.appkar.mvc";
}
