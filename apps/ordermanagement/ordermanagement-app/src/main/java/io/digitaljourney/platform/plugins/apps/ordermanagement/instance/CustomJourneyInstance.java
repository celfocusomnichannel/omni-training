package io.digitaljourney.platform.plugins.apps.ordermanagement.instance;

import java.util.Date;

import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.session.JourneyInstance;

public class CustomJourneyInstance extends JourneyInstance {
	public String customField;
	public Date createDate;
}
