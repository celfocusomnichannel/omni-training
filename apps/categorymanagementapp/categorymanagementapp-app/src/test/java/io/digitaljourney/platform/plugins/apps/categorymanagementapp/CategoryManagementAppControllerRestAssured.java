/*-
 * #%L
 * Apps :: App to manage categories App
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
package io.digitaljourney.platform.plugins.apps.categorymanagementapp;

import io.restassured.RestAssured;

/**
 * App to manage categories version test.
 *
 */
public class CategoryManagementAppControllerRestAssured {

	private static final String BASE_PATH = "/environment" + CategoryManagementAppProperties.ADDRESS + "/path";//FIXME set up the environment (core, cms, bin/mvc.do) and path
	private static final String USER = "user"; //FIXME set up the user
	private static final String PASS = "password";//FIXME set up the password
	private static final String BASE_URI = "http://127.0.0.1:%s";
	private static final int DISTRIBUTED_MODE_CMS_PORT = 4501;
	private static final int GENERIC_MODE_PORT = 4500;
	
	private static String bearerToken; //FIXME set up the token

	/**
	 * Obtains the correct URL for given path
	 * @param path The resource path
	 * @return Resource URI
	 */
	private static String getUrl(String path) {
		int port = getPort();
		return String.format(BASE_URI, port).concat(BASE_PATH).concat(null != path ? path : "");
	}

	/**
	 * Set up the server port
	 * */
	private static int getPort() {
		int status = -1;
		String uri;

		try {
			uri = String.format(BASE_URI, GENERIC_MODE_PORT);
			status = RestAssured.post(uri).statusCode();
			if (status != -1)
				return GENERIC_MODE_PORT;
		} catch (Exception e) {
			// ignore
		}

		try {
			uri = String.format(BASE_URI, DISTRIBUTED_MODE_CMS_PORT);
			status = RestAssured.post(uri).statusCode();
			if (status != -1)
				return DISTRIBUTED_MODE_CMS_PORT;
		} catch (Exception e) {
			// ignore
		}

		throw new IllegalStateException("Unidentified server port!");
	}
}
