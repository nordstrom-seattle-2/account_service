package com.airrentals.account_service.domain;

import com.airrentals.account_service.model.Profile;
import com.airrentals.account_service.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class DomainService {

    private final MemberRepository memberRepository;

    @Autowired
    public DomainService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void addMember(Profile member) {
        memberRepository.save(member);
    }

    public Optional<Profile> getMember(String id) {
        return memberRepository.findById(Long.parseLong(id));
    }
}
