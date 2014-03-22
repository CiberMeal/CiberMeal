/*
 * Licensed Materials - Use restricted, please refer to the "Samples Gallery" terms
 * and conditions in the IBM International Program License Agreement.
 *
 * Copyright IBM Corporation 2003, 2007. All Rights Reserved. 
 */
package com.ibm.xtools.modeler.ui.pde.examples.properties;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The main plugin class to be used in the desktop.
 */
public class PropertiesPlugin
	extends AbstractUIPlugin {

	/**
	 * The shared plug-in instance
	 */
	private static PropertiesPlugin plugin;

	/**
	 * Constructor
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#AbstractUIPlugin()
	 */
	public PropertiesPlugin() {
		super();
		plugin = this;

	}

	/**
	 * Returns the shared instance.
	 */
	public static PropertiesPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}
}