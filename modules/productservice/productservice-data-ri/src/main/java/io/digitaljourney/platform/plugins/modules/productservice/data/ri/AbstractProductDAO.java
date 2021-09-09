package io.digitaljourney.platform.plugins.modules.productservice.data.ri;

import java.lang.annotation.Annotation;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import io.digitaljourney.platform.modules.ws.rs.api.dao.AbstractRSDAO;

public abstract class AbstractProductDAO<A extends Annotation> extends AbstractRSDAO<A> {

	protected WebClient prepare(WebClient client) {
		return client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
	}
}
