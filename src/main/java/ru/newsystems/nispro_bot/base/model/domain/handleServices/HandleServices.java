
package ru.newsystems.nispro_bot.base.model.domain.handleServices;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Event",
    "Ticket"
})
@Generated("jsonschema2pojo")
public class HandleServices {

    @JsonProperty("Event")
    private Event event;
    @JsonProperty("Ticket")
    private Ticket ticket;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Event")
    public Event getEvent() {
        return event;
    }

    @JsonProperty("Event")
    public void setEvent(Event event) {
        this.event = event;
    }

    @JsonProperty("Ticket")
    public Ticket getTicket() {
        return ticket;
    }

    @JsonProperty("Ticket")
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
