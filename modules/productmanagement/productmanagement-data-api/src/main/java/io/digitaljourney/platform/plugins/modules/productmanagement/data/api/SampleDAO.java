package io.digitaljourney.platform.plugins.modules.productmanagement.data.api;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

import io.digitaljourney.platform.plugins.modules.productmanagement.entity.Sample;

@ProviderType
public interface SampleDAO {

  Sample getSample(Integer id);

  List<Sample> searchSamples(String expression);

  Sample addSample(Sample sample);

  Sample updateSample(Sample sample);

  void deleteSample(Integer id);
}
