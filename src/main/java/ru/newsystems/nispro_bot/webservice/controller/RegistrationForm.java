package ru.newsystems.nispro_bot.webservice.controller;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;

public class RegistrationForm extends FormLayout {
    TextField company = new TextField("Company");
    Checkbox lightVersion = new Checkbox("Light Version");
    TextField idTelegram = new TextField("Telegram ID");
    TextField agentIdTelegram = new TextField("Agent Telegram ID");
    TextField chatMembers = new TextField("Chat members");
    TextField url = new TextField("Url");
    TextField login = new TextField("Login");
    PasswordField password = new PasswordField("Password");
    TextField queueName = new TextField("Queue Name");
    TextField customerUser = new TextField("Customer User");
    Binder<TelegramBotRegistration> binder = new BeanValidationBinder<>(TelegramBotRegistration.class);
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    private TelegramBotRegistration telegramBotRegistration;


    public RegistrationForm() {
        addClassName("registration-form");
        //Validation Rules
        binder.bindInstanceFields(this);
        HorizontalLayout buttonsLayout = createButtonsLayout();
//        add(company, lightVersion, idTelegram, agentIdTelegram, chatMembers, queueName, customerUser, login, password, url, createButtonsLayout());
        add(company, lightVersion, idTelegram, agentIdTelegram, chatMembers, queueName,customerUser, login, password, url, buttonsLayout);
//        add(company, lightVersion, idTelegram, agentIdTelegram, chatMembers);
        setResponsiveSteps(
                new ResponsiveStep("0", 1),
                new ResponsiveStep("250px", 2)
        );
        setColspan(chatMembers, 2);
        setColspan(url, 2);
        setColspan(buttonsLayout, 2);
    }

    public void setTelegramBotRegistration(TelegramBotRegistration telegramBotRegistration) {
        this.telegramBotRegistration = telegramBotRegistration;
        binder.readBean(telegramBotRegistration);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(e -> validateAndSave());
        delete.addClickListener(e -> {
            fireEvent(new DeleteEvent(this, telegramBotRegistration));
            notification();
        });
        close.addClickListener(e -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void notification() {
        Notification notification = Notification.show("Готово!", 1000, Notification.Position.TOP_CENTER);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(telegramBotRegistration);
            fireEvent(new SaveEvent(this, telegramBotRegistration));
            notification();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

    public static abstract class ContactFormEvent extends ComponentEvent<RegistrationForm> {
        private final TelegramBotRegistration telegramEntity;

        protected ContactFormEvent(RegistrationForm source, TelegramBotRegistration telegramEntity) {
            super(source, false);
            this.telegramEntity = telegramEntity;
        }

        public TelegramBotRegistration getTelegramEntity() {
            return telegramEntity;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(RegistrationForm source, TelegramBotRegistration telegramEntity) {
            super(source, telegramEntity);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(RegistrationForm source, TelegramBotRegistration telegramEntity) {
            super(source, telegramEntity);
        }

    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(RegistrationForm source) {
            super(source, null);
        }
    }

}


