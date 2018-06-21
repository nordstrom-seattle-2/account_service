package com.airrentals.account_service.repository;

import com.airrentals.account_service.model.Profile;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Profile, Long> {
}
