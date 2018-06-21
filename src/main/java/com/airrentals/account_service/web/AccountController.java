package com.airrentals.account_service.web;

import com.airrentals.account_service.domain.DomainService;
import com.airrentals.account_service.model.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    @ApiOperation(value = "Add a new member", response = Member.class)
    public Member addMember(@RequestBody String member) throws IOException {
        log.info("Request body: {}", member);
        Member mapped = objectMapper.readValue(member, Member.class);
        log.info("Member: {}", mapped);
        return domainService.addMember(mapped);
    }
}
