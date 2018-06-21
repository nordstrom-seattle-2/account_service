package com.airrentals.account_service.web;

import com.airrentals.account_service.domain.DomainService;
import com.airrentals.account_service.model.Member;
import com.airrentals.account_service.model.Profile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@Log4j2
@RequestMapping("/accounts")
@Api(value = "accounts")
public class AccountController {

    private final DomainService domainService;
    private final ObjectMapper objectMapper;

    public AccountController(DomainService domainService, ObjectMapper objectMapper) {
        this.domainService = domainService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/addMember")
    @ApiOperation(value = "Add a new member", response = Profile.class)
    public ResponseEntity<?> addMember(@RequestBody String member) throws IOException {
        try {
            Profile mapped = objectMapper.readValue(member, Profile.class);
            // probably want a factory/protoype?
            domainService.addMember(mapped);
            return ResponseEntity.ok("Profile saved");
        } catch (IOException e) {
            log.error("Unable to parse member input={}", member);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getMember/{id}")
    @ApiOperation(value = "Get a member by id", response = Member.class)
    public ResponseEntity<?> getMember(@PathVariable String id) {
        Optional<Profile> result = domainService.getMember(id);

        if(result.isPresent()) {
            Profile profile = result.get();
            Map<String, String> map = new HashMap<>();
            map.put("firstName", profile.getFirstName());
            map.put("lastName", profile.getLastName());
            map.put("emailAddress", profile.getEmailAddress());
            try {
                return ResponseEntity.ok(objectMapper.writeValueAsString(map));
            } catch (JsonProcessingException e) {
                log.error("Unable to convert profile to json, profile={}", profile);
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
