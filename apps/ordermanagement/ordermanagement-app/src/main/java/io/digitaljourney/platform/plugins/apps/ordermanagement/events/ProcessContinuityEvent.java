package io.digitaljourney.platform.plugins.apps.ordermanagement.events;

import java.time.LocalDateTime;

public class ProcessContinuityEvent {
	/**
	 * List of event types
	 *
	 */
	public enum EventType {
		/** Process was created */
		CREATED,
		/** Process was update */
		UPDATED,
		/** Process was read */
		RETRIEVED,
		/** Process was deleted */
		DELETED,
		/** Process has expired */
		EXPIRED,
		/** Process is about to expire */
		ABOUT_TO_EXPIRE,
		/** Process status changed */
		STATUS_CHANGED
	}

	/** Type of event triggered */
	public EventType eventType;

	/** The unique identifier of a process instance */
	public Long instanceId;

	/** The version of the process instance */
	public long instanceVersion;

	/** The process type of the instance */
	public String processType;

	/** The process type friendly name of the instance */
	public String processTypeFriendlyName;

	/** The version of the process instance */
	public long processVersion;

	/** The process instance creation date */
	public LocalDateTime createdOn;

	/** The user that created the process instance */
	public String createdBy;

	/** The channel of the last updated of the process instance */
	public String createdChannel;

	/** The process instance last update time */
	public LocalDateTime updatedOn;

	/** The user that last updated the process instance */
	public String updatedBy;

	/** The channel of the last updated of the process instance */
	public String updatedChannel;

	/** The location of where the process instance was originated */
	public String location;

	/** The current status of the process instance */
	public String status;

	/**
	 * The state of the process. Predefined subset of process states, common across
	 * all processes (for instance: OPEN, CLOSED, CANCELED)
	 */
	public String state;

	/** Determines if the instance is locked for the user requesting the process */
	public Boolean locked;
}

