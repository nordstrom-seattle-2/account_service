package com.airrentals.account_service.model;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class Profile {

    @AggregateIdentifier
    public String profileId;

    @CommandHandler
    public Profile(AddProfileCommand addProfileCommand) {
        apply(new ProfileAddedEvent(addProfileCommand.getId(),
                addProfileCommand.getFirstName(),
                addProfileCommand.getLastName(),
                addProfileCommand.getEmailAddress(),
                addProfileCommand.getPassword()));
    }

    @EventSourcingHandler
    public void on(ProfileAddedEvent profileAddedEvent) {
        this.profileId = profileAddedEvent.getId();
    }
}
