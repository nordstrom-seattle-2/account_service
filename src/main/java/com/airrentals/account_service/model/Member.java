package com.airrentals.account_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@Entity
@JsonRootName(value = "member")
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String emailAddress;
}
