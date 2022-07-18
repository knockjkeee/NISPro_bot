package ru.newsystems.nispro_bot.webservice.controller;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Route("")
public class SomePathComponent extends VerticalLayout {

    public record Person (String name, LocalDate time) {}

    private final List<Person> personList = List.of(
            new Person("Donald Duck", LocalDate.of(1952, 06, 25)),
            new Person("Micky Mouse", LocalDate.of(1654, 12, 5)),
            new Person("Goofy", LocalDate.of(2020, 1, 14))
    );

    public SomePathComponent() {
        add(new Button("Click me", e -> Notification.show("Hello Spring+Vaadin user!")));
        Grid<Person> personGrid = new Grid<>();
        personGrid.addColumn(Person::name).setHeader("Name");
        personGrid.addColumn(person -> person.time).setHeader("Birthday");
//        personGrid.addColumn(Pe
//                        LitRenderer
//                                .<Person>of("<span>${item.value}</span>")
//                                .withProperty("value", person -> Period.between(person.birthday, LocalDate.now()).getYears())
//                )
//                .setHeader("Age");

        personGrid.setItems(personList);
        add(personGrid);
    }


}
