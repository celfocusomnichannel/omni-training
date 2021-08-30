package io.digitaljourney.platform.plugins.modules.modules.productmanagement.data.ri.rdb.sample;

import java.util.List;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.transaction.control.TransactionControl;
import org.osgi.service.transaction.control.jpa.JPAEntityManagerProvider;

import io.digitaljourney.platform.modules.rdb.jpa.api.JPAProperties;
import io.digitaljourney.platform.modules.rdb.jpa.api.dao.AbstractJPADAO;
import io.digitaljourney.platform.plugins.modules.modules.productmanagement.data.api.SampleDAO;
import io.digitaljourney.platform.plugins.modules.modules.productmanagement.data.ri.RDBContext;
import io.digitaljourney.platform.plugins.modules.modules.productmanagement.entity.Sample;

// @formatter:off
@Component(
	configurationPid = SampleDAOConfig.CPID,
	configurationPolicy = ConfigurationPolicy.REQUIRE,
	reference = {
		@Reference(
			name = JPAProperties.REF_CONTEXT,
			service = RDBContext.class, 
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = JPAProperties.REF_TX_CONTROL,
			service = TransactionControl.class,
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = JPAProperties.REF_JPA_ENTITY_MANAGER,
			service = JPAEntityManagerProvider.class,
			cardinality = ReferenceCardinality.MANDATORY)
		})
@Designate(ocd = SampleDAOConfig.class)
// @formatter:on
public final class SampleDAOImpl extends AbstractJPADAO<SampleDAOConfig> implements SampleDAO {
  @Activate
  public void activate(ComponentContext ctx, SampleDAOConfig config) {
    prepare(ctx, config);
  }

  @Modified
  public void modified(SampleDAOConfig config) {
    prepare(config);
  }

  @Override
  public Sample getSample(Integer id) {
    return findOne(Sample.class, id);
  }

  @Override
  public List<Sample> searchSamples(String expression) {
    return findByExpression(Sample.class, expression);
  }

  @Override
  public Sample addSample(Sample sample) {
    return save(sample);
  }

  @Override
  public Sample updateSample(Sample sample) {
    return merge(sample);
  }

  @Override
  public void deleteSample(Integer id) {
    deleteOne(Sample.class, id);
  }
}
