package io.digitaljourney.platform.plugins.modules.productmanagement.data.ri;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import io.digitaljourney.platform.modules.rdb.api.RDBProperties;
import io.digitaljourney.platform.modules.rdb.jpa.api.context.AbstractJPAClientContext;
import io.digitaljourney.platform.modules.uriql.api.UriqlProvider;

// @formatter:off
@Component(
	service = RDBContext.class,
	reference = {
		@Reference(
			name = RDBProperties.REF_URIQL,
			service = UriqlProvider.class,
			cardinality = ReferenceCardinality.MANDATORY)
	})
// @formatter:on
public final class RDBContext extends AbstractJPAClientContext {
  @Activate
  public void activate(ComponentContext ctx) {
    prepare(ctx);
  }
}
