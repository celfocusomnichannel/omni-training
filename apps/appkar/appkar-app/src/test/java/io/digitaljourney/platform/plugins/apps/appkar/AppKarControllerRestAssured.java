package io.digitaljourney.platform.plugins.apps.appkar;

import io.restassured.RestAssured;

/**
 * Kar App Description version test.
 *
 */
public class AppKarControllerRestAssured {

	private static final String BASE_PATH = "/environment" + AppProperties.ADDRESS + "/path";//FIXME set up the environment (core, cms, bin/mvc.do) and path
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
