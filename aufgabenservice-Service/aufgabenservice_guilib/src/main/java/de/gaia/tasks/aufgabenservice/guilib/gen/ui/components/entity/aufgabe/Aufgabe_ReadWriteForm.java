package de.gaia.tasks.aufgabenservice.guilib.gen.ui.components.entity.aufgabe;

import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;

import de.muenchen.vaadin.demo.i18nservice.buttons.SimpleAction;
import de.muenchen.vaadin.guilib.components.BaseComponent;
import de.muenchen.vaadin.guilib.components.buttons.ActionButton;

import de.gaia.tasks.aufgabenservice.api.local.Aufgabe_;
import de.gaia.tasks.aufgabenservice.guilib.gen.ui.components.buttons.listener.aufgabe.Aufgabe_SingleActions;

/**
 * Provides a full-featured Form for a Aufgabe_. It allows to view the current selected aufgabe and edit it.
 *
 * @author p.mueller
 * @version 1.0
 */
public class Aufgabe_ReadWriteForm extends BaseComponent {

    /** The default (readOnly/write) mode the Form initially starts. */
    public static final boolean DEFAULT_MODE = false;
    
    /** The button for the save action. */
	private final ActionButton saveButton = new ActionButton(Aufgabe_.class, SimpleAction.save);
	
	/** The button for the cancel action. */
	private final ActionButton cancelButton = new ActionButton(Aufgabe_.class, SimpleAction.cancel);
	
	/** The button for the edit action. */
	private final ActionButton editButton = new ActionButton(Aufgabe_.class, SimpleAction.update);

    /** The Form displaying the current selected Aufgabe_. */
    private final Aufgabe_SelectedForm aufgabeForm;
    
    /** The layout for all buttons that are shown in readonly mode. */
    private final HorizontalLayout buttons = new HorizontalLayout();
    
    /** The layout for all buttons that are shown in the update mode. */
    private final HorizontalLayout editButtons = new HorizontalLayout();
    
    /** The layout for all the buttons that are added aside from the standard buttons. */
	private HorizontalLayout extraButtons = new HorizontalLayout();
    
    /** The current mode the RWForm is in. */
    private boolean edit;

    /**
     * Create a new Aufgabe_ReadWriteForm with the internationalization of the Controller.
     * It will navigate to the navigateBack value on the back button click.
     */
    public Aufgabe_ReadWriteForm() {
        aufgabeForm = new Aufgabe_SelectedForm();
        aufgabeForm.reLoad();

        init();
        setIds();
    }

    /**
     * Initialize the Layout and all the Buttons in it.
     */
    private void init() {
    	configureButtons();
		configureEditButtons();
    	
        getButtons().setSpacing(true);
        getButtons().addComponents(editButton);
        getEditButtons().setSpacing(true);
        getEditButtons().addComponents(cancelButton, saveButton);

        getForm().getFormLayout().addComponents(getButtons(), getEditButtons(), getExtraButtons());

        setEditable(DEFAULT_MODE);
        setCompositionRoot(getForm());
    }
    
    /**
	 * Set the IDs for important components.
	 */
	private void setIds() {
		setId(getClass().getSimpleName());
		getForm().getFields().forEach(f -> f.setId(getId() + "#" + f.getId()));
		getForm().setId(getId() + "#form");
		getEditButton().setId(getId() + "#edit-button");
		getCancelButton().setId(getId() + "#cancel-button");
		getSaveButton().setId(getId() + "#save-button");
	}

    /**
     * Configures the edit Buttons for this form.
     * @return An array of all buttons.
     */
    private void configureEditButtons() {
        final Aufgabe_SingleActions singleActions = new Aufgabe_SingleActions(getForm()::getAufgabe);
        saveButton.addActionPerformer(singleActions::update);
        saveButton.addActionPerformer(clickEvent -> {
            setEditable(false);
            return true;
        });
        saveButton.useNotification(true);
        getSaveButton().setNotifyAction(SimpleAction.update);

        cancelButton.addActionPerformer(singleActions::reRead);
        cancelButton.addActionPerformer(clickEvent -> {
            setEditable(false);
            return true;
        });
    }

    /**
     * Configures the Buttons for the readOnly mode.
     * @return An array of all buttons.
     */
    private void configureButtons() {
        editButton.addClickListener(clickEvent -> setEditable(true));
    }

    /**
     * Set the mode of this form. True means edit and false means readOnly.
     * @param edit true means edit mode.
     */
    public void setEditable(boolean edit) {
        this.edit = edit;

        getButtons().setVisible(!isEditable());
        getEditButtons().setVisible(isEditable());
        getForm().setReadOnly(!isEditable());

        if (isEditable())
            getForm().getFields().stream().findFirst().ifPresent(Field::focus);
    }

	// Getters
    /**
     * Get the Layout for Buttons of the readOnly mode.
     * @return The Layout for the readOnly mode Buttons.
     */
    public HorizontalLayout getButtons() {
        return buttons;
    }

    public HorizontalLayout getExtraButtons() {
        return extraButtons;
    }

    /**
     * Check if this Form is in Edit mode.
     * @return true, if it is in edit mode.
     */
    public boolean isEditable() {
        return edit;
    }

    /**
     * Get the Layout for the Buttons that is shown in edit mode.
     * @return The layout for edit mode.
     */
    public HorizontalLayout getEditButtons() {
        return editButtons;
    }

    /**
     * Get the Aufgabe_SelectedForm used by this RWForm.
     * @return The Aufgabe_SelectedForm.
     */
    public Aufgabe_SelectedForm getForm() {
        return aufgabeForm;
    }
    
    public ActionButton getEditButton() {
		return editButton;
	}
	
	public ActionButton getCancelButton() {
		return cancelButton;
	}
	
	public ActionButton getSaveButton() {
		return saveButton;
	}
	
	public Component addButton(ActionButton button){
    	extraButtons.addComponent(button);
    	return this;
	}
}

