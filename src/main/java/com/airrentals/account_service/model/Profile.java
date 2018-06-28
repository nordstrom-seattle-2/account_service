package com.airrentals.account_service.model;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Log4j2
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
        log.info("Set id on event");
        this.profileId = profileAddedEvent.getId();
    }
}
