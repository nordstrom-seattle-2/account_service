package com.airrentals.account_service.domain;

import com.airrentals.account_service.model.Member;
import com.airrentals.account_service.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomainService {

    private final MemberRepository memberRepository;

    @Autowired
    public DomainService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member) {
        // probably want a factory/protoype?
        return memberRepository.save(member);
    }
}
