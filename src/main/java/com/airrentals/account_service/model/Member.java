package com.airrentals.account_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@JsonRootName(value = "member")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {

    @JsonProperty("profile")
    private Profile profile;
}
