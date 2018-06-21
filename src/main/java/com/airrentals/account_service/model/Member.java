package com.airrentals.account_service.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonValue
    private Long id;
    @JsonValue
    private String firstName;
    @JsonValue
    private String lastName;
    @JsonValue
    private String emailAddress;
}
