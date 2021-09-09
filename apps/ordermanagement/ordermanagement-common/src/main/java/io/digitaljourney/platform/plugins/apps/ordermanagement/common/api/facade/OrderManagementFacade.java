/*-
 * #%L
 * Apps :: Training JWE Order Management App :: Common
 * %%
 * Copyright (C) 2016 - 2021 Digital Journey
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
package io.digitaljourney.platform.plugins.apps.ordermanagement.common.api.facade;

public interface OrderManagementFacade {

	/**
	 * Print the given message.
	 *
	 * @param msg the message
	 * @return The given message
	 */
	public String echo(String msg);
	
	/**
	 * Print the given message and channel over security context.
	 *
	 * @param channel 	the channel message
	 * @param msg 		the message
	 * @return The given message
	 */
	public String secureEcho(String channel, String msg);
}
