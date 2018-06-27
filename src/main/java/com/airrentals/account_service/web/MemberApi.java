package com.airrentals.account_service.web;

import com.airrentals.account_service.model.AddProfileCommand;
import com.airrentals.account_service.model.ProfileQueryObject;
import com.airrentals.account_service.repository.ProfileQueryObjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@Log4j2
@RequestMapping("/accounts")
public class MemberApi {

    private final ProfileQueryObjectRepository profileQueryObjectRepository;
    private final ObjectMapper objectMapper;
    private final CommandGateway commandGateway;

    public MemberApi(CommandGateway commandGateway,
                     ProfileQueryObjectRepository profileQueryObjectRepository,
                     ObjectMapper objectMapper) {
        this.commandGateway = commandGateway;
        this.profileQueryObjectRepository = profileQueryObjectRepository;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public CompletableFuture<Object> addProfile(@RequestBody Map<String, String> request) {
        String id = UUID.randomUUID().toString();
        return commandGateway.send(new AddProfileCommand(id,
                request.get("firstName"),
                request.get("lastName"),
                request.get("emailAddress"),
                request.get("password")));
    }

    @GetMapping("/{id}")
    public Optional<ProfileQueryObject> getProfile(@PathVariable String id) {
        return profileQueryObjectRepository.findById(id);
    }

    @GetMapping()
    public List<ProfileQueryObject> getProfiles() {
        return profileQueryObjectRepository.findAll();

    }
}
