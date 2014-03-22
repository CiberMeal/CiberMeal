/*
 * Licensed Materials - Use restricted, please refer to the "Samples Gallery" terms
 * and conditions in the IBM International Program License Agreement.
 *
 * Copyright IBM Corporation 2006, 2007. All Rights Reserved. 
 */

package com.ibm.xtools.modeler.ui.pde.examples.properties;

import org.eclipse.osgi.util.NLS;


/** 
 * Define the string resources.
 */
public class PropertiesPluginResources extends NLS {
    
    private static final String BUNDLE_NAME = "com.ibm.xtools.modeler.ui.pde.examples.properties.PropertiesPluginResources";//$NON-NLS-1$

    private PropertiesPluginResources() {
        // Do not instantiate
    }
    
    static {
        NLS.initializeMessages(BUNDLE_NAME, PropertiesPluginResources.class);
    }

    public static String providerName;

    public static String category;
    public static String defaultValue;
    public static String dialogTitle;
    public static String message;


    public static String propertyName;
    public static String propertyDescription;

    public static String operationLabel;
    public static String invalidInputMessage;
}
