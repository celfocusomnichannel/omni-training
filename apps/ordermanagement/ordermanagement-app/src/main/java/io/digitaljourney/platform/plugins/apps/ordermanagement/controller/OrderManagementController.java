package io.digitaljourney.platform.plugins.apps.ordermanagement.controller;

import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.osgi.service.component.annotations.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.digitaljourney.platform.plugins.apps.ordermanagement.AppProperties;
import io.digitaljourney.platform.plugins.apps.ordermanagement.api.JourneyControllerResource;
import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomJourneyDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.instance.CustomJourneyInstance;
import io.digitaljourney.platform.plugins.apps.ordermanagement.mapper.JourneyControllerMapper;

import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.api.trigger.ActionMode;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.JourneyProcess;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.annotation.JourneyMethod;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.annotation.JourneyReference;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.session.JourneySession;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CmsRsProvider;

/**
 * Simple Journey with 3 APIs:
 * - Create
 * - Read
 * - Update
 * - Finish
 * 
 * The matching journey map is included in this project and can be imported into Journey Designer (custom_journey.zip)
 */
@Controller
@Component
@CmsRsProvider(value = AppProperties.ADDRESS)
@RequestMapping("/" + AppProperties.ADDRESS)
public class OrderManagementController extends AbstractAppController implements JourneyControllerResource, JourneyProcess<CustomJourneyInstance> {

	/**
	 * Create action mode creates a new empty instance
	 */
	@JourneyMethod(value = "CreateProcess", mode = ActionMode.CREATE)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@RequiresPermissions(AppProperties.PERMISSION_CREATE)
	@Override
	public @ResponseBody CustomJourneyDTO create() {

		// Retrieves the instance from JWE (will be empty because it's a CREATE action)
		CustomJourneyInstance instance = JourneySession.getInstance(this);
		instance.createDate = new Date();

		return JourneyControllerMapper.INSTANCE.toProcess(instance);
	}

	/**
	 * Retrieves existing instance with a given ID
	 */
	@JourneyMethod(value = "ReadProcess")
	@RequestMapping(value = "/{instanceId}", method = RequestMethod.GET)
	@RequiresPermissions(AppProperties.PERMISSION_READ)
	@Override
	public @ResponseBody CustomJourneyDTO read(@PathVariable @JourneyReference Long instanceId) {

		// retrieves the existing instance from JWE and transforms into DTO
		CustomJourneyInstance instance = JourneySession.getInstance(this);
		return JourneyControllerMapper.INSTANCE.toProcess(instance);
	}

	/**
	 * Updates the instance field
	 */
	@JourneyMethod(value = "UpdateField")
	@RequestMapping(value = "/{instanceId}/custom", method = RequestMethod.PUT)
	@RequiresPermissions(AppProperties.PERMISSION_UPDATE)
	@Override
	public @ResponseBody CustomJourneyDTO changeField(@PathVariable @JourneyReference Long instanceId, @RequestBody String value) {

		// retrieves the existing instance from JWE
		CustomJourneyInstance instance = JourneySession.getInstance(this);
		
		// updates instance
		instance.customField = value;

		// transforms into DTO
		return JourneyControllerMapper.INSTANCE.toProcess(instance);
	}

	/**
	 * Moves the journey instance to the final state (closed state) 
	 */
	@JourneyMethod(value = "Finish")
	@RequestMapping(value = "/{instanceId}/finish", method = RequestMethod.PUT)
	@RequiresPermissions(AppProperties.PERMISSION_UPDATE)
	@Override
	public @ResponseBody CustomJourneyDTO finish(@PathVariable @JourneyReference Long instanceId) {
		// Does nothing, just return the current instance as a DTO 
		// JWE will close this journey instance after the execution, according to the journey map
		CustomJourneyInstance instance = JourneySession.getInstance(this);
		return JourneyControllerMapper.INSTANCE.toProcess(instance);
	}

	@Override
	public String journeyName() {
		return AppProperties.JOURNEY_NAME;
	}

	@Override
	public int majorVersion() {
		return AppProperties.JOURNEY_VERSION;
	}

	@Override
	public Class<CustomJourneyInstance> getInstanceClass() {
		return CustomJourneyInstance.class;
	}
}
