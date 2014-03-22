/*
 * Licensed Materials - Use restricted, please refer to the "Samples Gallery" terms
 * and conditions in the IBM International Program License Agreement.
 *
 * Copyright IBM Corporation 2003, 2007. All Rights Reserved. 
 */
package com.ibm.xtools.modeler.ui.pde.examples.properties;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.properties.GetPropertySourceOperation;
import org.eclipse.gmf.runtime.common.ui.services.properties.ICompositePropertySource;
import org.eclipse.gmf.runtime.common.ui.services.properties.IPropertiesProvider;
import org.eclipse.gmf.runtime.common.ui.services.properties.descriptors.CompositePropertySource;
import org.eclipse.gmf.runtime.common.ui.services.properties.descriptors.CompositeSourcePropertyDescriptor;

/**
 * Example property provider - provides a annotation property for any
 * <code>EModelElement</code> object
 *  
 */
public class ExamplePropertiesProvider
	extends AbstractProvider
	implements IPropertiesProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetPropertySourceOperation) {

			Object object = ((GetPropertySourceOperation) operation)
				.getObject();

			if (object instanceof EModelElement)
				return true;

			return object instanceof IAdaptable
				&& ((IAdaptable) object).getAdapter(EModelElement.class) != null;

		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.common.ui.services.internal.properties.IPropertiesProvider#getPropertySource(java.lang.Object)
	 */
	public ICompositePropertySource getPropertySource(Object object) {

		CompositePropertySource source = new CompositePropertySource(object,
            PropertiesPluginResources.category);

		EModelElement target = (EModelElement) (object instanceof EModelElement ? object
			: ((IAdaptable) object).getAdapter(EModelElement.class));

		CompositeSourcePropertyDescriptor descriptor = new ExamplePropertyDescriptor(
			target, ExamplePropertiesTable.EXAMPLE_PROPERTY);

		source.addPropertyDescriptor(descriptor);
		return source;
	}
}