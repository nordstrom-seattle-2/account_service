package com.airrentals.account_service.repository;

import com.airrentals.account_service.model.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
