/*-
 * #%L
 * Apps :: Training JWE Order Management App App
 * %%
 * Copyright (C) 2016 - 2019 Digital Journey
 * %%
 * All rights reserved. This software is protected under several
 * Laws in various countries. All content, layout, design of this document are the
 * intellectual property of Digital Journey, Novabase Business Solutions S.A. 
 * and its licensors. The disclosure,copying, adaptation, citation, transcription, 
 * translation, modification, decompilation, reverse engineering, derivatives, 
 * integration, development and/or any other form of total or partial use of the 
 * content of this document and/or accessible through or via the contents, by any 
 * possible means without the respective authorization or licensing by the owner of 
 * the intellectual property rights is prohibited, the offenders being subject to civil 
 * and/or criminal prosecution and liability. The user or licensee of all or part of this 
 * document by any means may only use the document under the terms and conditions agreed
 * upon with the owner of the intellectual property rights, and for the purposes
 * justifying the granting of the license or authorization, without which the
 * unauthorized use may subject the offenders to civil or criminal prosecution
 * under applicable Laws.
 * #L%
 */
package io.digitaljourney.platform.plugins.apps.ordermanagement;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileReader;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.Test;

/**
 * Training JWE Order Management App version test.
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
