/*
 * Licensed Materials - Use restricted, please refer to the "Samples Gallery" terms
 * and conditions in the IBM International Program License Agreement.
 *
 * Copyright IBM Corporation 2003, 2007. All Rights Reserved. 
 */
package com.ibm.xtools.modeler.ui.pde.examples.properties;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.ui.services.properties.descriptors.CompositeSourcePropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.widgets.Composite;

import com.ibm.xtools.modeler.ui.UMLModeler;

/**
 * An example property descriptor, which describes property of a
 * <code>EModelElement</code> instance. The descriptor uses instance of
 * <code>Property</code> class as an id object for the property at hand. The
 * instances of <code>Property</code> are defined in the
 * <code>ExamplePropetiesTable</code> for quick reference.
 * 
 * The example descriptor allows to display and edit example annotation object,
 * which is attached to the model element object. By default the example
 * annotation does not exist until user attempts to modify the property. After
 * such an attempt the annotation object is created and it's details map is
 * populated with the new property value.
 * 
 * In order to demonstrate value validation, this example creates a validator
 * object which will accept only integer values as being appropriate for this
 * property.
 * 
 * @see <code>createPropertyEditor(Composite)</code>.
 */
public class ExamplePropertyDescriptor
    extends CompositeSourcePropertyDescriptor {

    private static final String EXAMPLE_ANNOTATION_URI = "EXAMPLE_ANNOTATION_URI"; //$NON-NLS-1$

    private static final String EXAMPLE_PROPERTY_TAG = "EXAMPLE_PROPERTY_TAG"; //$NON-NLS-1$

    /**
     * Create a new desriptor, which describes property of a
     * <code>EModelElement</code> instance
     * 
     * @param object -
     *            property owner object
     * @param id -
     *            property id object
     */
    public ExamplePropertyDescriptor(EModelElement object, Property id) {
        super(object, id, id.getDisplayName());

        setDescription(id.getDescription());
        setDefaultValue(id.getDefaultValue());
    }

    /**
     * A convinience accessor to avoid type casts. This descriptor only
     * describes properties of <code>EModelElement</code> instances.
     * 
     * @return - a model elenment - target of the property
     */
    private EModelElement getModelElement() {
        return (EModelElement) getObject();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.IPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
     */
    public CellEditor createPropertyEditor(Composite parent) {

        if (isReadOnly())
            return null;

        CellEditor editor = new ExampleCellEditor(new ICellEditorValidator() {

            public String isValid(Object value) {
                if (value instanceof String) {
                    try {
                        new Integer((String) value); // make sure that the
                        // string value can be
                        // evaluated to an integer
                        return null;
                    } catch (NumberFormatException e) {
                        return PropertiesPluginResources.invalidInputMessage;
                    }
                }
                return PropertiesPluginResources.invalidInputMessage;
            }
        });

        editor.create(parent);
        return editor;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gmf.runtime.common.ui.services.properties.descriptors.ICompositeSourcePropertyDescriptor#getPropertyValue()
     */
    public Object getPropertyValue() {

        EAnnotation ann = getModelElement().getEAnnotation(
            EXAMPLE_ANNOTATION_URI);

        return (null == ann) ? getDefaultValue()
            : (String) ann.getDetails().get(EXAMPLE_PROPERTY_TAG);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gmf.runtime.common.ui.services.properties.descriptors.CompositeSourcePropertyDescriptor#setValue(java.lang.Object)
     */
    public void setValue(final Object propertyValue) {

        /* Perform remaining work within a RecordingCommand */
        TransactionalEditingDomain ted = UMLModeler.getEditingDomain();

        ted.getCommandStack().execute(
            new RecordingCommand(ted, PropertiesPluginResources.operationLabel) {

                /*
                 * (non-Javadoc)
                 * 
                 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
                 */
                protected void doExecute() {

                    EAnnotation ann = getModelElement().getEAnnotation(
                        EXAMPLE_ANNOTATION_URI);

                    if (null == ann) {// create annotation object if there
                                        // isn't one already
                        ann = EcoreFactory.eINSTANCE.createEAnnotation();
                        ann.setSource(EXAMPLE_ANNOTATION_URI);
                        getModelElement().getEAnnotations().add(ann);
                    }

                    // save value in EMap
                    ann.getDetails().put(EXAMPLE_PROPERTY_TAG, (String)propertyValue);
                }

            });

    }

}