package io.digitaljourney.platform.plugins.apps.genericappkar;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import shaded.org.apache.maven.model.Model;
import shaded.org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import shaded.org.codehaus.plexus.util.xml.pull.XmlPullParserException;

/**
 * Generic Kar App Description version test.
 *
 */
public class AppVersionTestUT {

	/**
	 * Validates that he currently set major version is equal to the pom major
	 * version.
	 * 
	 * @throws IOException            If there is an exception reading from the pom
	 *                                file
	 * @throws XmlPullParserException If the the pom has issues
	 */
	@Test
	public void apiVersionTest() throws IOException, XmlPullParserException {
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = reader.read(new FileReader(System.getProperty("user.dir") + "/pom.xml"));
		String version = model.getParent().getVersion();
		String majorVersion = version.split("\\.")[0];
		assertThat(majorVersion).isEqualTo(AppProperties.CURRENT_VERSION);
	}

	/**
	 * Tests that the base APP address ends with the current App version
	 */
	@Test
	public void addressShouldEndWithVersionTest() {
		assertThat(AppProperties.ADDRESS).endsWith("/v" + AppProperties.CURRENT_VERSION);
	}
}
