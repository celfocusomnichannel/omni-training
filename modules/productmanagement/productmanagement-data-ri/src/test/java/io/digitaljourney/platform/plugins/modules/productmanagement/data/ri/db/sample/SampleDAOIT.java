package io.digitaljourney.platform.plugins.modules.productmanagement.data.ri.db.sample;

import static io.digitaljourney.platform.modules.paxexam.BundleOptions.jpaClient;
import static io.digitaljourney.platform.modules.paxexam.BundleOptions.mavenBundles;
import static io.digitaljourney.platform.modules.paxexam.ConfigOptions.jpaClientConfiguration;
import static io.digitaljourney.platform.modules.paxexam.ConfigOptions.newConsumerConfiguration;
import static org.assertj.core.api.Assertions.assertThat;
import static org.ops4j.pax.exam.CoreOptions.composite;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;

import io.digitaljourney.platform.modules.paxexam.base.BaseTestSupport;
import io.digitaljourney.platform.plugins.modules.productmanagement.data.api.SampleDAO;
import io.digitaljourney.platform.plugins.modules.productmanagement.data.ri.rdb.sample.SampleDAOConfig;
import io.digitaljourney.platform.plugins.modules.productmanagement.entity.Sample;
import io.digitaljourney.platform.plugins.modules.productmanagement.entity.SampleBuilder;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class SampleDAOIT extends BaseTestSupport {
  private static final String TITLE_1 = "Title 1";

  private static final String TITLE_2 = "Title 2";

  @Inject
  private SampleDAO sampleDAO;

  @Override
  protected Option bundles() {
    return composite(super.bundles(), jpaClient(),
        mavenBundles("io.digitaljourney.platform.plugins.modules", "productmanagement-entity"),
        testBundle("io.digitaljourney.platform.plugins.modules", "productmanagement-data-ri"));
  }

  @Override
  protected Option configurations() {
    return composite(super.configurations(),
        jpaClientConfiguration("productmanagement", newConsumerConfiguration(SampleDAOConfig.CPID, "productmanagement")));
  }

  @Test
  public void testBundle() {
    assertBundleActive("io.digitaljourney.platform.plugins.modules.productmanagement-data-ri");
  }

  private Sample buildSample1() {
    return new SampleBuilder().withTitle(TITLE_1).build();
  }

  private Sample buildSample2() {
    return new SampleBuilder().withTitle(TITLE_2).build();
  }

  @Test
  public void addSample1() {
    Sample sample1 = buildSample1();
    sample1 = sampleDAO.addSample(sample1);

    assertThat(sample1).isNotNull();
    assertThat(sample1.getId()).isNotNull();
  }

  @Test
  public void getSample1() {
    Sample sample1 = buildSample1();
    sample1 = sampleDAO.addSample(sample1);

    assertThat(sample1).isNotNull();
    sample1 = sampleDAO.getSample(sample1.getId());
    assertThat(sample1).isNotNull();
    assertThat(sample1.getTitle().equals(TITLE_1));
  }

  @Test
  public void getSamples1() {
    Sample sample1 = buildSample1();
    Sample sample2 = buildSample2();
    sample1 = sampleDAO.addSample(sample1);
    assertThat(sample1).isNotNull();
    sample2 = sampleDAO.addSample(sample2);
    assertThat(sample2).isNotNull();

    assertThat(sampleDAO.searchSamples(null)).size().isGreaterThan(0);
  }

  @Test
  public void removeSample1() {
    Sample sample1 = buildSample1();
    sample1 = sampleDAO.addSample(sample1);

    assertThat(sample1).isNotNull();
    assertThat(sample1.getId()).isNotNull();

    sampleDAO.deleteSample(sample1.getId());
  }
}
