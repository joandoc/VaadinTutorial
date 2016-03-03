package com.ar.ui.views.error;

import com.vaadin.server.UserError;
import com.vaadin.ui.Label;

/**
 * Created by joan on 13/10/15.
 */
class ErrorLabel extends Label implements ErrorDisplay {
    public ErrorLabel() {
        setVisible(false);
    }

    public void setError(String error) {
        setValue(error);
        setComponentError(null);
        setVisible(true);
    }

    public void clearError() {
        setValue(null);
        setComponentError(null);
        setVisible(false);
    }
}