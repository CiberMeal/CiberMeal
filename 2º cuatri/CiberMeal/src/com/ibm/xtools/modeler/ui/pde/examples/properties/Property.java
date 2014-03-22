/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2004.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package com.ibm.xtools.modeler.ui.pde.examples.properties;

/**
 * An example utility class used to tag properties and populate properties table for ease of
 * navigation.
 * 
 * @see <code>ExamplePropertiesTable</code>
 */
class Property {

	// property display name
	private String displayName;

	//property description
	private String description;

	private String defaultValue;

	/*
	 * Creates a new property object
	 */
	public Property(String displayName, String description, String defaultValue) {
		this.displayName = displayName;
		this.description = description;
		this.defaultValue = defaultValue;
	}

	/**
	 * @return - Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return - Returns the displayName.
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return Returns the defaultValue.
	 */
	public String getDefaultValue() {
		return defaultValue;
	}
}