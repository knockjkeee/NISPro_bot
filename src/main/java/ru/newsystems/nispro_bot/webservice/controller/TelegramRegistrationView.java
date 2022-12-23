package ru.newsystems.nispro_bot.webservice.controller;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.security.SecurityService;
import ru.newsystems.nispro_bot.webservice.services.TelegramBotRegistrationService;

@Route("")
@PageTitle("Telegram registration")
public class TelegramRegistrationView extends VerticalLayout {

    private final SecurityService securityService;

    Grid<TelegramBotRegistration> grid = new Grid<>(TelegramBotRegistration.class);
    TextField filterText = new TextField();
    RegistrationForm registrationForm;
    TelegramBotRegistrationService service;

    public TelegramRegistrationView(TelegramBotRegistrationService service, SecurityService securityService) {
        this.securityService = securityService;
        this.service = service;
        addClassName("telegram_registration");
        setSizeFull();
        configureGrid();
        configureForm();
        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("company", "lightVersion", "idTelegram", "agentIdTelegram", "chatMembers", "queueName", "url");
//        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event ->
                editContact(event.getValue()));
    }

    private void configureForm() {
        registrationForm = new RegistrationForm();
        registrationForm.setWidth("35em");
        registrationForm.addListener(RegistrationForm.SaveEvent.class, this::saveContact);
        registrationForm.addListener(RegistrationForm.DeleteEvent.class, this::deleteContact);
        registrationForm.addListener(RegistrationForm.CloseEvent.class, e -> closeEditor());
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, registrationForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, registrationForm);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void updateList() {
        grid.setItems(service.findFilter(filterText.getValue()));
    }

    private void saveContact(RegistrationForm.SaveEvent event) {
        service.save(event.getTelegramEntity());
        updateList();
        closeEditor();
    }

    private void deleteContact(RegistrationForm.DeleteEvent event) {
        service.remove(event.getTelegramEntity());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolbar() {

        filterText.setPlaceholder("Filter by company name...");
        filterText.setMinWidth("420px");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add registration");

        Button logout = new Button("Log out", e -> securityService.logout());
        logout.addThemeVariants(ButtonVariant.LUMO_ERROR);
        addContactButton.addClickListener(click -> addContact());


        HorizontalLayout toolbar = new HorizontalLayout(logout, filterText, addContactButton );
        toolbar.setSizeFull();
        toolbar.setHeight("50");
        logout.getElement().getStyle().set("margin-right", "auto");

        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editContact(TelegramBotRegistration telegramBotRegistration) {
        if (telegramBotRegistration == null) {
            closeEditor();
        } else {
            registrationForm.setTelegramBotRegistration(telegramBotRegistration);
            registrationForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        registrationForm.setTelegramBotRegistration(null);
        registrationForm.setVisible(false);
        removeClassName("editing");
    }

    private void addContact() {
        grid.asSingleSelect().clear();
        editContact(new TelegramBotRegistration());
    }
}
