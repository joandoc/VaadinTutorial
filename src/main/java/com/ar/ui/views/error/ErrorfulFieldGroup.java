package com.ar.ui.views.error;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;

/**
 * Created by joan on 13/10/15.
 */
class ErrorfulFieldGroup extends FieldGroup {
    private static final long serialVersionUID = -6293510593661094366L;
    ErrorDisplay errorDisplay;

    public ErrorfulFieldGroup(Item item) {
        super(item);
    }

    public void setErrorDisplay(ErrorDisplay errorDisplay) {
        this.errorDisplay = errorDisplay;
    }

    @Override
    public void commit() throws CommitException {
        try {
            super.commit();
            if (errorDisplay != null)
                errorDisplay.clearError();
        } catch (CommitException e) {
            if (errorDisplay != null)
                errorDisplay.setError(e.getCause().getMessage());
            throw e;
        }
    }
}