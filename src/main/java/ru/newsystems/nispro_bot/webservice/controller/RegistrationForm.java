package ru.newsystems.nispro_bot.webservice.controller;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import ru.newsystems.nispro_bot.base.model.db.TelegramBot;

public class RegistrationForm extends FormLayout {
    private TelegramBot telegramEntity;

    TextField company = new TextField("Company");
    TextField url = new TextField("Url");
    TextField login = new TextField("Login");
    TextField password = new TextField("Password");
    TextField queueID = new TextField("QueueID");

    Binder<TelegramBot> binder = new BeanValidationBinder<>(TelegramBot.class);

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");


    public RegistrationForm() {
        addClassName("registration-form");
        //Validation Rules
        binder.bindInstanceFields(this);
        add(company, url, login, password, queueID, createButtonsLayout());
    }

    public void setTelegramEntity(TelegramBot telegramEntity) {
        this.telegramEntity = telegramEntity;
        binder.readBean(telegramEntity);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(e -> validateAndSave());
        delete.addClickListener(e -> fireEvent(new DeleteEvent(this, telegramEntity)));
        close.addClickListener(e -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(telegramEntity);
            fireEvent(new SaveEvent(this, telegramEntity));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }


    public static abstract class ContactFormEvent extends ComponentEvent<RegistrationForm> {
        private TelegramBot telegramEntity;

        protected ContactFormEvent(RegistrationForm source, TelegramBot telegramEntity) {
            super(source, false);
            this.telegramEntity = telegramEntity;
        }

        public TelegramBot getTelegramEntity() {
            return telegramEntity;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(RegistrationForm source, TelegramBot telegramEntity) {
            super(source, telegramEntity);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(RegistrationForm source, TelegramBot telegramEntity) {
            super(source, telegramEntity);
        }

    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(RegistrationForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}


