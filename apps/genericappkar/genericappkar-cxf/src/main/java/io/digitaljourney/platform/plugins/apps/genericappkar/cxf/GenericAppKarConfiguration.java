package io.digitaljourney.platform.plugins.apps.genericappkar.cxf;

import org.osgi.service.metatype.annotations.Icon;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "%name", description = "%description", localization = "OSGI-INF/l10n/genericappkarcxf", icon = @Icon(resource = "OSGI-INF/icon/genericappkarcxf.png", size = 32))
public @interface GenericAppKarConfiguration {
    /** Component identifier */
    static final String CPID = "platform.plugins.apps.genericappkar.cxf";
}
