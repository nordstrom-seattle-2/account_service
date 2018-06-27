package com.airrentals.account_service.web;

import com.airrentals.account_service.model.ProfileAddedEvent;
import com.airrentals.account_service.model.ProfileQueryObject;
import com.airrentals.account_service.repository.ProfileQueryObjectRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ProfileQueryObjectUpdater {

    private final ProfileQueryObjectRepository profileQueryObjectRepository;

    public ProfileQueryObjectUpdater(ProfileQueryObjectRepository profileQueryObjectRepository) {
        this.profileQueryObjectRepository = profileQueryObjectRepository;
    }

    @EventHandler
    public void on(ProfileAddedEvent profileAddedEvent) {
        profileQueryObjectRepository.save(new ProfileQueryObject(
                profileAddedEvent.getId(),
                profileAddedEvent.getFirstName(),
                profileAddedEvent.getLastName(),
                profileAddedEvent.getEmailAddress(),
                profileAddedEvent.getPassword()));
    }
}
