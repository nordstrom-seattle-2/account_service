package com.airrentals.account_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;

import javax.persistence.Entity;
import javax.persistence.Id;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProfileQueryObject {

    @Id
    private String id;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String emailAddress;
    @JsonProperty
    private String password;

    @CommandHandler
    public ProfileQueryObject(AddProfileCommand command) {
        this.id = command.getId();
        this.firstName = command.getFirstName();
        this.lastName = command.getLastName();
        this.emailAddress = command.getEmailAddress();
        this.password = command.getPassword();
        apply(new ProfileAddedEvent(this.id,
                this.firstName,
                this.lastName,
                this.emailAddress,
                this.password));
    }

    @EventSourcingHandler
    private void handleProfileCreatedEvent(ProfileAddedEvent event) {
        this.id = event.getId();
    }
}
