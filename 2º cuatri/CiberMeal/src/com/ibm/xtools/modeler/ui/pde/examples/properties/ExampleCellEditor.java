/*
 * Licensed Materials - Use restricted, please refer to the "Samples Gallery" terms
 * and conditions in the IBM International Program License Agreement.
 *
 * Copyright IBM Corporation 2003, 2007. All Rights Reserved. 
 */
package com.ibm.xtools.modeler.ui.pde.examples.properties;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.widgets.Control;

/**
 * Example dialog property editor - allows user to set a property value using an
 * <code>InputDialog</code>.
 */
class ExampleCellEditor
	extends DialogCellEditor {

	/**
	 * Create an istance of the cell editor
	 */
	public ExampleCellEditor(ICellEditorValidator cellValidator) {
		super();
		setValidator(cellValidator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
	 */
	protected Object openDialogBox(Control cellEditorWindow) {

		InputDialog dialog = new InputDialog(cellEditorWindow.getShell(),
            PropertiesPluginResources.dialogTitle, PropertiesPluginResources.message, 
            (String) doGetValue(),
			new IInputValidator() {

				public String isValid(String newText) {
					return getValidator() == null ? null
						: getValidator().isValid(newText);
				}
			});

		dialog.open();
		//	there seems to be a bug in the DialogCellEditor for the editors which
		// have a non-null validator. Whenever the property is being changed first it fires
		// property changed event to the registered listeners such as property
		// sheet cells and only after the event is processed by the listeners , it sets the validity of the new
		// value. By default the validity is false - so even if the value assesed by the validator to be correct
		// the listeners do not know it. This is why we calculate and set the validity prior to returning the new value.
		// All other cell editors (not DialogCellEditor subclasses) should not require this.
		setValueValid(isCorrect(dialog.getValue()));
		return dialog.getValue();

	}

}