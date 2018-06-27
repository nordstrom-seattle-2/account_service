package com.airrentals.account_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ProfileAddedEvent {
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
}
