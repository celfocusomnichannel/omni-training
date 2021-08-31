package io.digitaljourney.platform.plugins.modules.productmanagement.data.ri.rdb.sample;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Icon;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "%name", description = "%description", localization = "OSGI-INF/l10n/sample",
    icon = @Icon(resource = "OSGI-INF/icon/rdb.png", size = 32))
public @interface SampleDAOConfig {

  public static final String CPID = "io.digitaljourney.platform.plugins.modules.productmanagement.data.ri.rdb.sample";

  @AttributeDefinition(name = "%provider_target.name", description = "%provider_target.description",
      required = true)
  String provider_target();
}
