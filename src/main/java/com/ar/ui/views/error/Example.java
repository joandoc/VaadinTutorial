package com.ar.ui.views.error;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

/**
 * Created by joan on 13/10/15.
 */
public class Example {





    public void errorhandler(final VerticalLayout root) {
        // BEGIN-EXAMPLE: application.errors.error-indicator.errorhandler
        // Here's some code that produces an uncaught exception
        final VerticalLayout layout = new VerticalLayout();
        final Button button = new Button("Click Me!",
                new Button.ClickListener() {
                    private static final long serialVersionUID = -3138442059473438237L;

                    public void buttonClick(Button.ClickEvent event) {
                        ((String)null).length(); // Null-pointer exception
                    }
                });
        layout.addComponent(button);

        // Configure the error handler for the entire UI
        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            private static final long serialVersionUID = 3526469128352984806L;

            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                String cause = "<b>The click ";

                // Find the component causing the error
                AbstractComponent c = findAbstractComponent(event);
                if (c != null)
                    cause += "on a " + c.getClass().getSimpleName();

                // Find the final cause
                cause += " failed because:</b><br/>";
                for (Throwable t = event.getThrowable(); t != null;
                     t = t.getCause())
                    if (t.getCause() == null) // We're at final cause
                        cause += t.getClass().getName() + "<br/>";

                // Display the error message in a custom fashion
                layout.addComponent(new Label(cause, ContentMode.HTML));

                // Do the default error handling (optional)
                doDefault(event);
            }
        });
        // END-EXAMPLE: application.errors.error-indicator.errorhandler

        root.addComponent(layout);
    }



    public void form(VerticalLayout layout) {
        class MyForm extends CustomComponent {
            private static final long serialVersionUID = 8893447767363695369L;

            TextField name = new TextField("Name");

            public MyForm(Item item) {
                setCaption("My Form");

                VerticalLayout content = new VerticalLayout();

                // Configure fields
                name.setRequired(true);
                name.setRequiredError("Must be given");
                name.addValidator(new StringLengthValidator("Too long", 0, 5, true));
                name.setImmediate(true);
                name.setValidationVisible(true);

                // Build the form
                FormLayout form = new FormLayout();
                form.addComponent(name);
                content.addComponent(form);

                // Bind the form
                final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(item);
                binder.setBuffered(true);
                binder.bindMemberFields(this);

                // Have an error display
                final ErrorLabel formError = new ErrorLabel();
                formError.setWidth(null);
                content.addComponent(formError);
                binder.setErrorDisplay(formError);

                // Commit button
                Button ok = new Button("OK");
                ok.addClickListener(new Button.ClickListener() {
                    private static final long serialVersionUID = -5285421385946833355L;

                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        try {
                            binder.commit();
                        } catch (FieldGroup.CommitException e) {
                        }
                    }
                });
                content.addComponent(ok);

                setCompositionRoot(content);
            }
        }

        PropertysetItem item = new PropertysetItem();
        item.addItemProperty("name", new ObjectProperty<String>(""));

        MyForm form = new MyForm(item);
        layout.addComponent(form);

        ///////////////////////////
        // The old Vaadin 6 way

        final Form oldForm = new Form();
        oldForm.setBuffered(true);
        oldForm.setCaption("Vaadin 6 Form");

        TextField name = new TextField("Name");
        name.setRequired(true);
        name.setRequiredError("Must not be empty");
        name.addValidator(new StringLengthValidator("Too long", 0, 5, true));
        name.setImmediate(true);
        oldForm.addField("name", name);

        // Have a component that fires click events
        final Button button = new Button("Click Me!");
        oldForm.getFooter().addComponent(button);

        // When clicked, set the form error
        button.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = -3138442059473438237L;

            public void buttonClick(Button.ClickEvent event) {
                oldForm.setComponentError(new UserError("Bad click"));
            }
        });

        layout.addComponent(oldForm);
        // END-EXAMPLE: application.errors.error-indicator.form

        layout.setSpacing(true);
    }

}
